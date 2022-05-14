package com.faojen.exoterra.blocks.crystalcatalyst;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.Config;
import com.faojen.exoterra.capabilities.crystalcatalyst.CrystalCatalystFluid;
import com.faojen.exoterra.capabilities.crystalcatalyst.CrystalCatalystItemHandler;
import com.faojen.exoterra.capabilities.purificationbestower.PurificationBestowerEnergy;
import com.faojen.exoterra.capabilities.purificationbestower.PurificationBestowerFluid;
import com.faojen.exoterra.capabilities.purificationbestower.PurificationBestowerItemHandler;
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
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.items.ItemStackHandler;

// Todo: completely rewrite this class from the ground up
public class CrystalCatalystBE extends BlockEntity implements MenuProvider {
	public enum Slots {
		INPUTFUEL(0), INPUTGHAST(2), OUTPUT(3);

		int id; 

		Slots(int number) {
			id = number;
		}

		public int getId() {
			return id;
		}
	}
	
	private static final int FLUID_CAPACITY = 25000;
	public static final int FLUID_CAP_PUB = FLUID_CAPACITY;
	
	private final int crystallizeTime = 50000;
	public final int crystallizeTimePub = crystallizeTime;
	
	private int scounter = 0;
	private int maxSBurn = 0;

	public CrystalCatalystFluid fluidStorage;
	private LazyOptional<CrystalCatalystFluid> fluidh;
	private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new CrystalCatalystItemHandler(this));

	// Handles tracking changes, kinda messy but apparently this is how the cool
	// kids do it these days
	public final ContainerData crystalCatalystData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
			
			case 0 -> CrystalCatalystBE.this.fluidStorage.getFluidAmount();
			case 1 -> CrystalCatalystBE.this.fluidStorage.getCapacity();
			case 2 -> CrystalCatalystBE.this.scounter;
			case 3 -> CrystalCatalystBE.this.maxSBurn;
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

	public CrystalCatalystBE(BlockPos pos, BlockState state) {
		super(Registration.CRYSTAL_CATALYST_BE.get(), pos, state);
		this.fluidStorage = new CrystalCatalystFluid(this, FLUID_CAPACITY);
		this.fluidh = LazyOptional.of(() -> this.fluidStorage);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new CrystalCatalystContainer(this, this.crystalCatalystData, i, playerInventory,
				this.inventory.orElse(new ItemStackHandler(4)));
	}

	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof CrystalCatalystBE entity) {
			entity.inventory.ifPresent(handler -> {
				// entity.tryCrystallize();
			});
		}
	}
	
	
	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		inventory.ifPresent(h -> h.deserializeNBT(compound.getCompound("inv")));
		fluidh.ifPresent(h -> h.deserializeNBT(compound.getCompound("fluid")));
		
		scounter = compound.getInt("scounter");
		maxSBurn = compound.getInt("maxsburn");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
		fluidh.ifPresent(h -> compound.put("fluid", h.serializeNBT()));
		
		compound.putInt("scounter", scounter);
		compound.putInt("maxsburn", maxSBurn);

	}
	
	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, final @Nullable Direction side) {

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
		inventory.invalidate();
		fluidh.invalidate();
		super.setRemoved();
	}

	@Override
	public Component getDisplayName() {
		return new TextComponent("Crystal Catalyst BE");
	}
}