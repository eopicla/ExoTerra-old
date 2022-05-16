package com.faojen.exoterra.blocks.crystalcatalyst;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.capabilities.fluid.ExoTerraBasicFluidStorage;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.items.ItemStackHandler;

// Todo: completely rewrite this class from the ground up
public class CrystalCatalystBE extends BlockEntity implements MenuProvider {
	public enum Slots {
		INPUTFUEL(0), INPUTGHAST(1), OUTPUT(2);

		int id; 

		Slots(int number) {
			id = number;
		}

		public int getId() {
			return id;
		}
	}
	// This is the key for the compoundtag that gets saved for the crystallization percent.
	private static final String CRY_KEY = "cry";
	/**
	 * The below integers can be changed to affect the block.
	 * -
	 * cryTime is the time in ticks that it takes to crystallize 1 pure stellar material.
	 */
	private static final int FLUID_CAPACITY = 6000;
	private int cryTime = 2400;
	/**
	 * -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	 */
	public static final int FLUID_CAP_PUB = FLUID_CAPACITY;
	private int crystallizing;
	
	private int scounter = 0;
	private int maxSBurn = 0;
	
	private boolean isReady;
	private boolean isFueled;
	private boolean isSeeded;
	
	public ExoTerraBasicFluidStorage fluidStorage;
	private LazyOptional<ExoTerraBasicFluidStorage> fluidh;
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
			case 4 -> CrystalCatalystBE.this.crystallizing;
			case 5 -> CrystalCatalystBE.this.cryTime;
			default -> throw new IllegalArgumentException("Invalid index: " + index);
			};
		}

		@Override
		public void set(int index, int value) {
			throw new IllegalStateException("Cannot set values through IIntArray");
		}

		@Override
		public int getCount() {
			return 6;
		}
	};

	public CrystalCatalystBE(BlockPos pos, BlockState state) {
		super(Registration.CRYSTAL_CATALYST_BE.get(), pos, state);
		this.fluidStorage = new ExoTerraBasicFluidStorage(this, FLUID_CAPACITY);
		this.fluidh = LazyOptional.of(() -> this.fluidStorage);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new CrystalCatalystContainer(this, this.crystalCatalystData, i, playerInventory,
				this.inventory.orElse(new ItemStackHandler(3)));
	}

	public int getCrystallizing() {
		return crystallizing;
	}
	
	public void setCrystallizing(Integer num) {
		crystallizing = num;
	}
	
	public CompoundTag crystallizingToNBT() {
		CompoundTag tag = new CompoundTag();
        tag.putInt(CRY_KEY, this.crystallizing);
        return tag;
	}
	
	public void crystallizingFromNBT(CompoundTag nbt) {
		this.crystallizing = nbt.getInt(CRY_KEY);
	}
	
	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof CrystalCatalystBE entity) {
			entity.inventory.ifPresent(handler -> {
				entity.tickCrystallize();
			});
		}
	}
	
	private void tickCrystallize() {
		if (level == null)
			return;
		FluidStack fluidtest = new FluidStack(Registration.AQUEOUS_STELLAR.get(), 1);
		
		boolean canCrystallize = fluidStorage.fill(fluidtest, FluidAction.SIMULATE) > 0;
		System.out.println("crystallizing: " + crystallizing);
		if(crystallizing > 0 && canCrystallize && crystallizing <= 2399) {
			crystallize(fluidStorage);
		} else if(canCrystallize) {
			if (initCrystallize())
				crystallize(fluidStorage);
		} 
			
	}
	// fluid drain should happen in initcrystallize, as the first action of the ticker.
	// item extraction and insertion should exist in the crystallize funcion
	private void crystallize(IFluidHandler fluidStorage) {
		
		crystallizing++;
		scounter--;
		
		if(scounter == 0 && crystallizing == 0 ) {
			maxSBurn = 0;
			initCrystallize();
		} else if(crystallizing >= cryTime) {
			insertPure();
			}
		
	}
	
	private void insertPure() {
		ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
		ItemStack pure = new ItemStack(Registration.PURE_STELLAR.get());
		
		handler.insertItem(2, pure, false);
		System.out.println("inserted pure ???");
		System.out.println("current scounter: " + scounter + "curent crystallizing: " + crystallizing);
		crystallizing = 0;
		scounter = 0;
		System.out.println("set crystallizing to 0... -> " + crystallizing);
	}
	
	private boolean initCrystallize() {
		
		ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
		Item fuelitem = handler.getStackInSlot(Slots.INPUTFUEL.id).getItem();
		Item seeditem = handler.getStackInSlot(Slots.INPUTGHAST.id).getItem();
		
		isFueled = fuelitem == Items.DIAMOND;
		isSeeded = seeditem == Items.GHAST_TEAR;
		
		isReady = isFueled && isSeeded;
		int purifyTime = 0;
		
		if(isReady) {
			purifyTime = cryTime;
		}
		
		if(purifyTime > 0 && fluidStorage.getFluidAmount() > 3000) {
			 FluidStack stellar = new FluidStack(Registration.AQUEOUS_STELLAR.get(), purifyTime);
			
			 handler.extractItem(0, 1, false);
			 handler.extractItem(1, 1, false);
			 fluidStorage.drain(stellar, FluidAction.EXECUTE);
			 
			 setChanged();
			 scounter = (int) Math.floor(purifyTime) / 1;
			 maxSBurn = scounter;
			 return true;
		 }
		 return false;
		
	}
	
	@Override
	public void load(CompoundTag compound) {
		super.load(compound);

		inventory.ifPresent(h -> h.deserializeNBT(compound.getCompound("inv")));
		fluidh.ifPresent(h -> h.deserializeNBT(compound.getCompound("fluid")));
		crystallizingFromNBT(compound.getCompound("cry"));
		
		scounter = compound.getInt("scounter");
		maxSBurn = compound.getInt("maxsburn");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
		fluidh.ifPresent(h -> compound.put("fluid", h.serializeNBT()));
		compound.put("cry", crystallizingToNBT());
		
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