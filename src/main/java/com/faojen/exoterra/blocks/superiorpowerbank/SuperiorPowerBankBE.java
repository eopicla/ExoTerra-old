package com.faojen.exoterra.blocks.superiorpowerbank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.api.capabilities.energy.ExoTerraBasicEnergyStorage;
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

// Todo: completely rewrite this class from the ground up
public class SuperiorPowerBankBE extends BlockEntity implements MenuProvider {
	public enum Slots {
		FUEL(0);

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
	private static int energyCapacity = 50000000;
	private static int energyMaxInOut = 200000;
	/**
	 * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	public static final int SUP_BANK_CAPACITY_PUB = energyCapacity;
	public static final int SUP_BANK_MAXINOUT_PUB = energyMaxInOut;

	public ExoTerraBasicEnergyStorage energyStorage;
	private LazyOptional<ExoTerraBasicEnergyStorage> energy;

	// Handles tracking changes, kinda messy but apparently this is how the cool
	// kids do it these days
	public final ContainerData superiorPowerBankData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
			case 0 -> SuperiorPowerBankBE.this.energyStorage.getEnergyStored() / 32;
			case 1 -> SuperiorPowerBankBE.this.energyStorage.getMaxEnergyStored() / 32;

			default -> throw new IllegalArgumentException("Invalid index: " + index);
			};
		}

		@Override
		public void set(int index, int value) {
			throw new IllegalStateException("Cannot set values through IIntArray");
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	public SuperiorPowerBankBE(BlockPos pos, BlockState state) {
		super(Registration.SUPERIOR_POWER_BANK_BE.get(), pos, state);
		this.energyStorage = new ExoTerraBasicEnergyStorage(this, 0, energyCapacity, energyMaxInOut);
		this.energy = LazyOptional.of(() -> this.energyStorage);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new SuperiorPowerBankContainer(this, this.superiorPowerBankData, i, playerInventory, null);
	}

	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof SuperiorPowerBankBE entity) {
		
		}
	}
	
	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		energy.ifPresent(h -> h.deserializeNBT(compound.getCompound("energy")));

	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));

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
		super.setRemoved();
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Superior Power Bank BE");
	}
}