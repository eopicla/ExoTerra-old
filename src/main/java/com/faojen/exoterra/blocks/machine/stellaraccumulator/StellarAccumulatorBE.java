package com.faojen.exoterra.blocks.machine.stellaraccumulator;

import com.faojen.exoterra.api.capabilities.energy.ExoTerraBasicEnergyStorage;
import com.faojen.exoterra.api.capabilities.fluid.ExoTerraBasicFluidStorage;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// Todo: completely rewrite this class from the ground up
public class StellarAccumulatorBE extends BlockEntity implements MenuProvider {
    public enum Slots {
        FILTER(0), OUTPUT(1);

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
    private static int energyCapacity = 1000000; // (energyCapacity is in FE)
    private static int energyMaxInOut = 1000000; // (energyMaxInOut is in FE/tick)
    private static final int FLUID_CAPACITY = 15000;
    /**
     * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
     */
    public static final int FLUID_CAP_PUB = FLUID_CAPACITY;
    public static final int ENERGY_CAPACITY_PUB = energyCapacity;
    public static final int ENERGY_MAXINOUT_PUB = energyMaxInOut;
    private int maxABurn = 0;
    private int accumulateCounter = 0;
    private int filterCounter;
    private int filterCounterMax;
    private int filterProgress;
    private boolean skip = false;
    private boolean isFilterInit;

    // screen access stuff
    private int isFilterInstalled;
    private int filterDurability;
    //

    public ExoTerraBasicEnergyStorage energyStorage;
    public ExoTerraBasicFluidStorage fluidStorage;
    private LazyOptional<ExoTerraBasicEnergyStorage> energy;
    private LazyOptional<ExoTerraBasicFluidStorage> fluidh;
    private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new StellarAccumulatorItemHandler(this));

    // Handles tracking changes, kinda messy but apparently this is how the cool
    // kids do it these days
    public final ContainerData chargingStationData = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> StellarAccumulatorBE.this.energyStorage.getEnergyStored() / 32;
                case 1 -> StellarAccumulatorBE.this.energyStorage.getMaxEnergyStored() / 32;
                case 2 -> StellarAccumulatorBE.this.fluidStorage.getFluidAmount();
                case 3 -> StellarAccumulatorBE.this.fluidStorage.getCapacity();
                case 4 -> StellarAccumulatorBE.this.accumulateCounter;
                case 5 -> StellarAccumulatorBE.this.maxABurn;
                case 6 -> StellarAccumulatorBE.this.filterProgress;
                case 7 -> StellarAccumulatorBE.this.filterCounter;
                case 8 -> StellarAccumulatorBE.this.filterCounterMax;
                case 9 -> StellarAccumulatorBE.this.isFilterInstalled;
                case 10 -> StellarAccumulatorBE.this.filterDurability;
                default -> throw new IllegalArgumentException("Invalid index: " + index);
            };
        }

        @Override
        public void set(int index, int value) {
            throw new IllegalStateException("Cannot set values through IIntArray");
        }

        @Override
        public int getCount() {
            return 11;
        }
    };

    public StellarAccumulatorBE(BlockPos pos, BlockState state) {
        super(Registration.STELLAR_ACCUMULATOR_BE.get(), pos, state);
        this.energyStorage = new ExoTerraBasicEnergyStorage(this, 0, energyCapacity, energyMaxInOut);
        this.fluidStorage = new ExoTerraBasicFluidStorage(this, FLUID_CAPACITY);
        this.energy = LazyOptional.of(() -> this.energyStorage);
        this.fluidh = LazyOptional.of(() -> this.fluidStorage);
    }

    int numx;

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
        assert level != null;
        return new StellarAccumulatorContainer(this, this.chargingStationData, i, playerInventory,
                this.inventory.orElse(new ItemStackHandler(2)));
    }

    public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
        if (t instanceof StellarAccumulatorBE entity) {
            entity.inventory.ifPresent(handler -> {
                entity.tryAccumulate();
                entity.tryFilter();
                entity.checkForFilter(handler);
            });
        }
    }

    private void checkForFilter(ItemStackHandler handler){
        ItemStack stack = handler.getStackInSlot(0);
        if(stack.getItem() == Registration.ALUMINUM_FILTER.get()){
            isFilterInstalled = 1;
            filterDurability = stack.getDamageValue();
        } else {
            isFilterInstalled = 0;
            filterDurability = 0;
        }
    }

    private void tryFilter() {
        if (level == null){
            return;}

        this.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorage ->  {
            ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
            boolean installed = handler.getStackInSlot(0).getItem() == Registration.ALUMINUM_FILTER.get();

            if (filterCounter > 0 && installed && filterProgress < 512 && fluidStorage.getFluidAmount() > 20) {
                filter();
            } else if (installed && filterProgress < 512) {
                if (initFilter() && fluidStorage.getFluidAmount() > 20)
                    filter();
            }else if(filterProgress >= 512 && installed){
                completeFilter();
            }
        });
    }

    private void completeFilter(){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack stellar = new ItemStack(Registration.INF_RAW_STELLAR.get());

        filterProgress = 0;

        handler.extractItem(0, 1, false);
        handler.insertItem(1,stellar, false);
    }

    private void filter(){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack filterStack = handler.getStackInSlot(0);

        fluidStorage.drain(20, FluidAction.EXECUTE);

        System.out.println("setting damage value to 16.. Current: " + filterStack.getDamageValue());
        filterStack.setDamageValue(filterStack.getDamageValue() - 16);
        System.out.println("New damagevalue: " + filterStack.getDamageValue());

        filterProgress++;
    }

    private boolean initFilter(){
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack filterStack = handler.getStackInSlot(0);
        Item filterItem = handler.getStackInSlot(0).getItem();
        filterCounter = 0;
        filterCounterMax = 0;

        if(filterItem == Registration.ALUMINUM_FILTER.get()){
            filterCounter = filterItem.getMaxDamage();
            filterCounterMax = filterCounter;
            isFilterInit = true;
            return true;
        }
        return false;
    }

    private void tryAccumulate() {
        if (level == null)
            return;

        this.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorage ->  {
            boolean canAccumulate = energyStorage.receiveEnergy(1, true) > 0;

            if (accumulateCounter > 0 && canAccumulate) {
                accumulate(energyStorage);
            } else if (canAccumulate) {
                if (initAccumulate())
                    accumulate(energyStorage);
            }
        });
    }


    private void accumulate(IEnergyStorage energyStorage) {
        FluidStack sludge;
        if(!skip) {
            sludge = new FluidStack(Registration.INTERESTING_SLUDGE.get(), 10);
            fluidStorage.fill(sludge, FluidAction.EXECUTE);
        }
        skip = !skip;

        energyStorage.extractEnergy(8192, false);
        accumulateCounter = energyStorage.getEnergyStored();

        if(accumulateCounter == 0) {
            maxABurn = 0;
            initAccumulate();
        }
    }

    private boolean initAccumulate() {

        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);

        if(energyStorage.getEnergyStored() > 4999 && this.fluidStorage.getFluidAmount() <= 14990) {

            setChanged();
            accumulateCounter = energyStorage.getEnergyStored();
            maxABurn = accumulateCounter;
            return true;
        }
        return false;
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);

        inventory.ifPresent(h -> h.deserializeNBT(compound.getCompound("inv")));
        energy.ifPresent(h -> h.deserializeNBT(compound.getCompound("energy")));
        fluidh.ifPresent(h -> h.deserializeNBT(compound.getCompound("fluid")));

    }

    @Override
    public void saveAdditional(CompoundTag compound) {

        inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
        energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));
        fluidh.ifPresent(h -> compound.put("fluid", h.serializeNBT()));

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side) {

        if (cap == CapabilityEnergy.ENERGY)
            return energy.cast();

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
        inventory.invalidate();
        fluidh.invalidate();
        super.setRemoved();
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Stellar Accumulator BE");
    }
}