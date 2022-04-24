package com.faojen.exoterra.blocks.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.Config;
import com.faojen.exoterra.blocks.container.StellarConverterContainer;
import com.faojen.exoterra.capabilities.ChargerEnergyStorage;
import com.faojen.exoterra.capabilities.ChargerFluidStorage;
import com.faojen.exoterra.capabilities.ChargerItemHandler;
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
public class StellarConverterBE extends BlockEntity implements MenuProvider {
	public enum Slots {
		FUEL(0), STELLAR(1);

		int id;

		Slots(int number) {
			id = number;
		}

		public int getId() {
			return id;
		}
	}

	private static final int FLUID_CAPACITY = 5000;
	public static final int FLUID_CAP_PUB = 5000;
	private int counter = 0;
	private int scounter = 0;
	private int maxBurn = 0;
	private int maxSBurn = 0;

	public ChargerEnergyStorage energyStorage;
	public ChargerFluidStorage fluidStorage;
	private LazyOptional<ChargerEnergyStorage> energy;
	private LazyOptional<ChargerFluidStorage> fluidh;
	private LazyOptional<ItemStackHandler> inventory = LazyOptional.of(() -> new ChargerItemHandler(this));

	// Handles tracking changes, kinda messy but apparently this is how the cool
	// kids do it these days
	public final ContainerData chargingStationData = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
			case 0 -> StellarConverterBE.this.energyStorage.getEnergyStored() / 32;
			case 1 -> StellarConverterBE.this.energyStorage.getMaxEnergyStored() / 32;
			case 2 -> StellarConverterBE.this.counter;
			case 3 -> StellarConverterBE.this.maxBurn;
			
			case 4 -> StellarConverterBE.this.fluidStorage.getFluidAmount();
			case 5 -> StellarConverterBE.this.fluidStorage.getCapacity();
			case 6 -> StellarConverterBE.this.scounter;
			case 7 -> StellarConverterBE.this.maxSBurn;
			default -> throw new IllegalArgumentException("Invalid index: " + index);
			};
		}

		@Override
		public void set(int index, int value) {
			throw new IllegalStateException("Cannot set values through IIntArray");
		}

		@Override
		public int getCount() {
			return 8;
		}
	};

	public StellarConverterBE(BlockPos pos, BlockState state) {
		super(Registration.STELLAR_CONVERTER_BE.get(), pos, state);
		this.energyStorage = new ChargerEnergyStorage(this, 0, Config.GENERAL.chargerMaxPower.get());
		this.fluidStorage = new ChargerFluidStorage(this, FLUID_CAPACITY);
		this.energy = LazyOptional.of(() -> this.energyStorage);
		this.fluidh = LazyOptional.of(() -> this.fluidStorage);
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new StellarConverterContainer(this, this.chargingStationData, i, playerInventory,
				this.inventory.orElse(new ItemStackHandler(2)));
	}

	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof StellarConverterBE entity) {
			entity.inventory.ifPresent(handler -> {
				entity.tryBurn();
				entity.tryPurify();
			});
		}
	}

	private void tryPurify() {
		if (level == null)
			return;
		FluidStack fluidpoke = new FluidStack(Registration.AQUEOUS_STELLAR.get(), 1);
		
		this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(fluidStorage -> {
			boolean canPurifyStellar = fluidStorage.fill(fluidpoke, FluidAction.SIMULATE) >0;
			
			if (scounter > 0 && canPurifyStellar) {
				System.out.println( " trypurify canpurifystellar");
				purify(fluidStorage);
			} else if (canPurifyStellar) {
				if (initPurify())
					purify(fluidStorage);
			}
		});
		
	}
	
	private void purify(IFluidHandler fluidStorage) {
		FluidStack stellar1 = new FluidStack(Registration.AQUEOUS_STELLAR.get(), 1);
		
		System.out.println( " purify should have filled tank, it was called");
		fluidStorage.fill(stellar1, FluidAction.EXECUTE);
		
		scounter--;
		
		if(scounter == 0) {
			maxSBurn = 0;
			initPurify();
		}
	}
	
	private boolean initPurify() {
		
		 ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
		 Item item = handler.getStackInSlot(Slots.STELLAR.id).getItem();
//		 EXAMPLE ITEMS
		 Item infrefinedstellar = Registration.INF_REFINED_STELLAR.get();
//		 -
		 int purifyTime = 0;
		 
		 if(item == infrefinedstellar) {purifyTime = 5000;} // Sets burn times for stellar items. 5000 purifyTime is equal to 100 MB of Aqueous Stellar.
//		 if(item == exampleitem) {purifyTime = (50 * expected MB per item) ;}
		 
		 if(purifyTime > 0 && energyStorage.getEnergyStored() >= 20000 && this.fluidStorage.getFluidAmount() <= 4900) {
			 
			 handler.extractItem(1, 1, false);
			 energyStorage.consumeEnergy(20000, false);
			 
			 setChanged();
			 scounter = (int) Math.floor(purifyTime) / 50;
			 maxSBurn = scounter;
			 return true;
		 }
		 return false;
	}
	
	private void tryBurn() {
		if (level == null)
			return;

		this.getCapability(CapabilityEnergy.ENERGY).ifPresent(energyStorage -> {
			boolean canInsertEnergy = energyStorage.receiveEnergy(625, true) > 0;
			if (counter > 0 && canInsertEnergy) {
				burn(energyStorage);
			} else if (canInsertEnergy) {
				if (initBurn())
					burn(energyStorage);
			}
		});
	}
	
    private void burn(IEnergyStorage energyStorage) {
        energyStorage.receiveEnergy(625, false);

        counter--;
        if (counter == 0) {
            maxBurn = 0;
            initBurn();
        }
    }
	
	private boolean initBurn() {
        ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
        ItemStack stack = handler.getStackInSlot(Slots.FUEL.id);

        int burnTime = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
        if (burnTime > 0) {
            Item fuelStack = handler.getStackInSlot(Slots.FUEL.id).getItem();
            handler.extractItem(0, 1, false);
            if( fuelStack instanceof BucketItem && fuelStack != Items.BUCKET )
                handler.insertItem(0, new ItemStack(Items.BUCKET, 1), false);

            setChanged();
            counter = (int) Math.floor(burnTime) / 50;
            maxBurn = counter;
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
		
		counter = compound.getInt("counter");
		maxBurn = compound.getInt("maxburn");
		scounter = compound.getInt("scounter");
		maxSBurn = compound.getInt("maxsburn");
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
		energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));
		fluidh.ifPresent(h -> compound.put("fluid", h.serializeNBT()));
		
		compound.putInt("counter", counter);
		compound.putInt("maxburn", maxBurn);
		compound.putInt("scounter", scounter);
		compound.putInt("maxsburn", maxSBurn);

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