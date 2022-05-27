package com.faojen.exoterra.blocks.simple.machinebody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.api.capabilities.energy.ExoTerraBasicEnergyStorage;
import com.faojen.exoterra.api.capabilities.fluid.ExoTerraBasicFluidStorage;
import com.faojen.exoterra.blocks.machine.crystalcatalyst.CrystalCatalystItemHandler;
import com.faojen.exoterra.items.basic.SentientCore;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

// Todo: completely rewrite this class from the ground up
public class MachineBodyBE extends BlockEntity implements MenuProvider {
    public enum Slots {
        CORE(0);

        int id;

        Slots(int number) {
            id = number;
        }

        public int getId() {
            return id;
        }
    }
    /**
     * Plug in numbers here for BE configuration
     */
    private static int energyCapacity = 20000000;
    private static int energyMaxInOut = 200000;
    private static int fluidCapacity = 20000;
    private static int fluidMaxInOut = 1000;
    /**
     * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
     */
    public static final int MACHINE_BODY_MAXENERGY = energyCapacity;
    public static final int MACHINE_BODY_MAXINOUT = energyMaxInOut;

    public static final int MACHINE_BODY_MAXFLUID = fluidCapacity;
    public static final int MACHINE_BODY_FLUIDMAXINOUT = fluidMaxInOut;

    private int coreStability;
    private int coreIntelligence;

    /**
     * stabilizeEfficiencyFluid is how effecient the machine will be with stellar consumption while trying to stabilize a core.
     * This number refers to the amount in MB of stellar the machine will consume to increase core stability by 1
     */
    private static int stabilizeEfficiencyFluid = 2;

    /**
     * stabilizeEfficiencyEnergy is how effecient the machine will be with energy consumption while trying to stabilize a core.
     * This number refers to the amount in FE of energy the machine will consume to increase core stability by 1
     */
    private static int stabilizeEfficiencyEnergy = 10;

    private int isStabilizing;
    private boolean justStabilized;

    public ExoTerraBasicEnergyStorage energyStorage;
    public ExoTerraBasicFluidStorage fluidStorage;
    private LazyOptional<ExoTerraBasicEnergyStorage> energy;
    private LazyOptional<ExoTerraBasicFluidStorage> fluidh;

    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new MachineBodyItemHandler(this));

    // Handles tracking changes, kinda messy but apparently this is how the cool
    // kids do it these days
    public final ContainerData machineBodyData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> MachineBodyBE.this.energyStorage.getEnergyStored() / 32;
                case 1 -> MachineBodyBE.this.energyStorage.getMaxEnergyStored() / 32;
                case 2 -> MachineBodyBE.this.fluidStorage.getFluidAmount();
                case 3 -> MachineBodyBE.this.fluidStorage.getCapacity();

                case 4 -> MachineBodyBE.this.coreStability;
                case 5 -> MachineBodyBE.this.coreIntelligence;
                case 6 -> MachineBodyBE.this.isStabilizing;

                default -> throw new IllegalArgumentException("Invalid index: " + index);
            };
        }

        @Override
        public void set(int index, int value) {
            throw new IllegalStateException("Cannot set values through IIntArray");
        }

        @Override
        public int getCount() {
            return 7;
        }
    };

    public MachineBodyBE(BlockPos pos, BlockState state) {
        super(Registration.MACHINE_BODY_BE.get(), pos, state);

        this.energyStorage = new ExoTerraBasicEnergyStorage(this, 0, energyCapacity, energyMaxInOut);
        this.fluidStorage = new ExoTerraBasicFluidStorage(this, fluidCapacity);

        this.energy = LazyOptional.of(() -> this.energyStorage);
        this.fluidh = LazyOptional.of(() -> this.fluidStorage);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        assert level != null;
        return new MachineBodyContainer(this, this.machineBodyData, i, playerInventory, this.inventory.orElse(new ItemStackHandler(1)));
    }

    public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
        if (t instanceof MachineBodyBE entity) {
            entity.inventory.ifPresent(handler -> {

                    entity.tickStabilize(entity);

            });
        }
    }

    public boolean checkForCoreThenDoValues(MachineBodyBE entity){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        boolean hasCore = handler.getStackInSlot(0).is(Registration.SENTIENT_CORE.get());

        if(hasCore) {
            entity.getValues();
            entity.setValues();
        }

        return hasCore;
    }

    public void getValues(){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack core = handler.getStackInSlot(0);

        coreStability = core.getTag().getInt("stability");
        coreIntelligence = core.getTag().getInt("level");
    }

    public void setValues(){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack core = handler.getStackInSlot(0);

        core.getTag().putInt("stability", this.coreStability);
        core.getTag().putInt("level", this.coreIntelligence);
    }

    public void tickStabilize(MachineBodyBE entity) {
        if (level == null) { return; }

            boolean canFluid = fluidStorage.getCapacity() > 0;
            boolean canEnergy = energyStorage.getMaxEnergyStored() > 0;

            if (canFluid && canEnergy) {
                stabilizeCore(fluidStorage, energyStorage, entity);
            }

            if (justStabilized){
                isStabilizing = 1;
            }

            if (justStabilized == false) {
                isStabilizing = 0;
            }

    }

    public void stabilizeCore(IFluidTank fluidStorage, IEnergyStorage energyStorage, MachineBodyBE entity) {

        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);

        if(handler.getStackInSlot(0).is(Registration.SENTIENT_CORE.get())) {
            getValues();
        }
        if (handler.getStackInSlot(0).is(Registration.SENTIENT_CORE.get()) && coreStability < SentientCore.getMaxStability() && fluidStorage.getFluidAmount() > stabilizeEfficiencyFluid && energyStorage.getEnergyStored() > stabilizeEfficiencyEnergy) {
            System.out.println("Stability BEFORE stabilize: " + coreStability);
            fluidStorage.drain(stabilizeEfficiencyFluid, IFluidHandler.FluidAction.EXECUTE);
            energyStorage.extractEnergy(stabilizeEfficiencyEnergy, false);

            justStabilized = true;
            coreStability++;
            setValues();
        } else {justStabilized = false;}

        System.out.println("Stability AFTER stabilize: " + coreStability);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        energy.ifPresent(h -> h.deserializeNBT(compound.getCompound("energy")));
        fluidh.ifPresent(h -> h.deserializeNBT(compound.getCompound("fluid")));

    }

    @Override
    public void saveAdditional(CompoundTag compound) {

        energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));
        fluidh.ifPresent(h -> compound.put("fluid", h.serializeNBT()));

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side) {

        if (cap == CapabilityEnergy.ENERGY)
            return energy.cast();

        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
            return fluidh.cast();

        return super.getCapability(cap, side);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        // Vanilla uses the type parameter to indicate which type of tile entity
        // (command block, skull, or beacon?) is receiving the packet, but it seems like
        // Forge has overridden this behavior
        return ClientboundBlockEntityDataPacket.create(this, entity -> this.getUpdateTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        saveAdditional(compoundTag);
        return compoundTag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    @Override
    public void setRemoved() {
        energy.invalidate();
        fluidh.invalidate();
        super.setRemoved();
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Machine Body BE");
    }
}