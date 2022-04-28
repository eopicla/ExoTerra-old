package com.faojen.exoterra.blocks.fabricationbench;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.Config;
import com.faojen.exoterra.capabilities.FabricationEnergyStorage;
import com.faojen.exoterra.capabilities.FabricationFluidStorage;
import com.faojen.exoterra.capabilities.FabricationItemHandler;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.ItemStackHandler;

// Todo: completely rewrite this class from the ground up
public class FabricationBenchBE extends BlockEntity implements MenuProvider {
	public enum Slots {
		INPUT(0), OUTPUT(1);

		int id;

		Slots(int number) {
			id = number;
		}

		public int getId() {
			return id;
		}
	}

	private static final int FLUID_CAPACITY = 10000;
	public static final int FLUID_CAP_PUB = 10000;
	private int counter = 0;
	private int maxBurn = 0;

	public FabricationEnergyStorage energyStorage;
	public FabricationFluidStorage fluidStorage;
	private LazyOptional<FabricationEnergyStorage> energy;
	private LazyOptional<FabricationFluidStorage> fluidh;
	private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new FabricationItemHandler(this));

	// Handles tracking changes, kinda messy but apparently this is how the cool
	// kids do it these days
	public final ContainerData fabricationBenchData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
			case 0 -> FabricationBenchBE.this.energyStorage.getEnergyStored() / 32;
			case 1 -> FabricationBenchBE.this.energyStorage.getMaxEnergyStored() / 32;
			case 2 -> FabricationBenchBE.this.fluidStorage.getFluidAmount();
			case 3 -> FabricationBenchBE.this.fluidStorage.getCapacity();
			default -> throw new IllegalArgumentException("Invalid index: " + index);
			};
		}

		@Override
		public void set(int index, int value) {
			throw new IllegalStateException("Cannot set values through IIntArray");
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	public FabricationBenchBE(BlockPos pos, BlockState state) {
		
		super(Registration.FABRICATION_BENCH_BE.get(), pos, state);
		FluidStack validator = new FluidStack(Registration.AQUEOUS_STELLAR.get(), 1);
		this.energyStorage = new FabricationEnergyStorage(this, 0, Config.GENERAL.chargerMaxPower.get());
		this.fluidStorage = new FabricationFluidStorage(FLUID_CAPACITY);
		this.energy = LazyOptional.of(() -> this.energyStorage);
		this.fluidh = LazyOptional.of(() -> this.fluidStorage);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new FabricationBenchContainer(this, this.fabricationBenchData, i, playerInventory,
				this.inventory.orElse(new ItemStackHandler(1)));
	}

	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof FabricationBenchBE entity) {
			entity.inventory.ifPresent(handler -> {

			});
		}
	}
	
	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		inventory.ifPresent(h -> h.deserializeNBT(compound.getCompound("inv")));
		energy.ifPresent(h -> h.deserializeNBT(compound.getCompound("energy")));
		
		counter = compound.getInt("counter");
		maxBurn = compound.getInt("maxburn");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
		energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));
		
		compound.putInt("counter", counter);
		compound.putInt("maxburn", maxBurn);

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
		inventory.invalidate();
		fluidh.invalidate();
		super.setRemoved();
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Stellar Converter BE");
	}
}