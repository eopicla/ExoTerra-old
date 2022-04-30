package com.faojen.exoterra.blocks.fabricationbench;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
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
	private static final int ENERGY_CAPACITY = 10000000;
	public static final int ENERGY_CAPACITY_PUB = 10000000;
	public static final int FLUID_CAP_PUB = 10000;
	private int counter = 0;
	private int maxBurn = 0;
	int powerload;

	// Shitty ass item key system :
	Item facpanelitem = Registration.FACETED_ALLUMINUM_PANEL.get();
	Item facpartitem = Registration.FACETED_ALLUMINUM_PART.get();
	Item fluidoutitem = Registration.FLUID_OUTLET.get();
	Item fractitem = Registration.FRACTURIZER.get();
	Item infcoreitem = Registration.INF_STELLAR_CORE.get();
	Item infpartitem = Registration.INF_STELLAR_PART.get();
	Item intpanelitem = Registration.INTERFACE_PANEL.get();
	Item macbodyitem = Registration.MACHINE_BODY_ITEM.get();
	Item infstelitem = Registration.INF_REFINED_STELLAR.get();
	
	int facpanel;
	 int facpart;
	 int fluidout;
	 int fract;
	 int infcore;
	 int infpart;
	 int intpanel;
	 int macbody;
	 int infstel;
// sucks
	
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
			case 4 -> FabricationBenchBE.this.powerload;
			
			case 5 ->	FabricationBenchBE.this.facpanel;
			case 6 ->	FabricationBenchBE.this.facpart;
			case 7 ->	FabricationBenchBE.this.fluidout;
			case 8 ->	FabricationBenchBE.this.fract;
			case 9 ->	FabricationBenchBE.this.infcore;
			case 10 -> FabricationBenchBE.this.infpart;
			case 11 -> FabricationBenchBE.this.intpanel;
			case 12 -> FabricationBenchBE.this.macbody;
			case 13 -> FabricationBenchBE.this.infstel;
			
			default -> throw new IllegalArgumentException("Invalid index: " + index);
			};
		}

		@Override
		public void set(int index, int value) {
			throw new IllegalStateException("Cannot set values through IIntArray");
		}

		@Override
		public int getCount() {
			return 14;
		}
	};

	public FabricationBenchBE(BlockPos pos, BlockState state) {
		
		super(Registration.FABRICATION_BENCH_BE.get(), pos, state);
		this.energyStorage = new FabricationEnergyStorage(this, 0, ENERGY_CAPACITY);
		this.fluidStorage = new FabricationFluidStorage(FLUID_CAPACITY);
		this.energy = LazyOptional.of(() -> this.energyStorage);
		this.fluidh = LazyOptional.of(() -> this.fluidStorage);
	}

	private int x = 0;
	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
		assert level != null;
		return new FabricationBenchContainer(this, this.fabricationBenchData, i, playerInventory,
				this.inventory.orElse(new ItemStackHandler(2)));
	}

	boolean count() {
		x = x+1;
		return(x % 100 == 0);
	}
	
	private void printStr(String string) {
		System.out.println(string);
	}
	@SuppressWarnings("unused")
	private void printInt(Integer string) {
		System.out.println(string);
	}
	private void printCombo(Integer num, String string) {
		System.out.println(num + " x " + string);
	}
	private void printCombo(String string, Integer num) {
		System.out.println(" | " + num + " x " + string + " | ");
	}
	
	private void printInv() {
		if(count()) {printInventory();}
	}
	
	public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
		if (t instanceof FabricationBenchBE entity) {
			entity.inventory.ifPresent(handler -> {
				entity.count();
				entity.tryStore();
				entity.printInv();
			});
		}
	}
	
	
	public void printInventory() {
		printStr("||-------------------------------------------------------------------------------||");
		printCombo(facpanelitem.toString(), facpanel);
		printCombo(facpartitem.toString(), facpart);
		printCombo(fluidoutitem.toString(), fluidout);
		printCombo(fractitem.toString(), fract);
		printCombo(infcoreitem.toString(), infcore);
		printCombo(infpartitem.toString(), infpart);
		printCombo(intpanelitem.toString(), intpanel);
		printCombo(macbodyitem.toString(), macbody);
		printCombo(infstelitem.toString(), infstel);
		printStr("||-------------------------------------------------------------------------------||");
	}
	
	private void tryStore(){
		if (level == null)
			return;
			
			if (energyStorage.getEnergyStored() > 5000) {
				store(energyStorage);
			} 
		
	}
	
	private void store(IEnergyStorage energyStorage) {

		powerload = (facpanel + facpart + fluidout + fract + infcore + infpart + intpanel + macbody + infstel) * 4;
		ItemStackHandler handler = inventory.orElseThrow(RuntimeException::new);
		Item item = handler.getStackInSlot(0).getItem();
		int num = handler.getStackInSlot(0).getCount();
		this.energyStorage.consumeEnergy(powerload, false);
		
		if (item == facpanelitem && facpanel <=1000) {
			facpanel = facpanel + num;
			printCombo(facpanel, facpanelitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == facpartitem && facpart <=1000) {
			facpart = facpart + num;
			printCombo(facpart, facpartitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == fluidoutitem && fluidout <=1000) {
			fluidout = fluidout + num;
			printCombo(fluidout, fluidoutitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == fractitem && fract <=1000) {
			fract = fract + num;
			printCombo(fract, fractitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == infcoreitem && infcore <=1000) {
			infcore = infcore + num;
			printCombo(infcore, infcoreitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == infpartitem && infpart <=1000) {
			infpart = infpart + num;
			printCombo(infpart, infpartitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == intpanelitem && intpanel <=1000) {
			intpanel = intpanel + num;
			printCombo(intpanel, intpanelitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == macbodyitem && macbody <=1000) {
			macbody = macbody + num;
			printCombo(macbody, macbodyitem.toString());
			handler.extractItem(0, num, false);
		} else if (item == infstelitem && infstel <=1000) {
			infstel = infstel + num;
			printCombo(infstel, infstelitem.toString());
			handler.extractItem(0, num, false);
		}
		
		
	}
	
	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		inventory.ifPresent(h -> h.deserializeNBT(compound.getCompound("inv")));
		energy.ifPresent(h -> h.deserializeNBT(compound.getCompound("energy")));
		
		
		counter = compound.getInt("counter");
		maxBurn = compound.getInt("maxburn");
		maxBurn = compound.getInt("powerload");
		
		printCombo(compound.getCompound("Inventory").getInt("1"), "inventory tag thing in BE load");
		
		facpanel = compound.getCompound("Inventory").getInt("1");
		facpart = compound.getCompound("Inventory").getInt("2");
		fluidout = compound.getCompound("Inventory").getInt("3");
		fract = compound.getCompound("Inventory").getInt("4");
		infcore = compound.getCompound("Inventory").getInt("5");
		infpart = compound.getCompound("Inventory").getInt("6");
		intpanel = compound.getCompound("Inventory").getInt("7");
		macbody = compound.getCompound("Inventory").getInt("8");
		infstel = compound.getCompound("Inventory").getInt("9");
		
		printCombo(facpanel, "facpanel in load");
		
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		
		inventory.ifPresent(h -> compound.put("inv", h.serializeNBT()));
		energy.ifPresent(h -> compound.put("energy", h.serializeNBT()));
		
		compound.putInt("counter", counter);
		compound.putInt("maxburn", maxBurn);
		
		CompoundTag invTag = new CompoundTag();
		invTag.putInt("1", facpanel);
		invTag.putInt("2", facpart);
		invTag.putInt("3", fluidout);
		invTag.putInt("4", fract);
		invTag.putInt("5", infcore);
		invTag.putInt("6", infpart);
		invTag.putInt("7", intpanel);
		invTag.putInt("8", macbody);
		invTag.putInt("9", infstel);
		
		compound.put("Inventory", invTag);
		
		printCombo(compound.getCompound("Inventory").getInt("1"), "inventory tag thing in BE save");

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