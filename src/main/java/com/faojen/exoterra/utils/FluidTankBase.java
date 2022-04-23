package com.faojen.exoterra.utils;

import net.minecraftforge.fluids.capability.templates.FluidTank;

public class FluidTankBase extends FluidTank {

	public FluidTankBase(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}
/*
	 private TileBlockEntityCyclic tile;

	  public FluidTankBase(TileBlockEntityCyclic tile, int capacity, Predicate<FluidStack> validator) {
	    super(capacity, validator);
	    this.tile = tile;
	  }

	  @Override
	  public void onContentsChanged() {
	    //send to client
	    IFluidHandler handler = tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null).orElse(null);
	    if (handler == null || handler.getFluidInTank(0) == null) {
	      return;
	    }
	    FluidStack f = handler.getFluidInTank(0);
	    if (tile.getLevel().isClientSide == false) { //if serverside then 
	      PacketRegistry.sendToAllClients(tile.getLevel(), new PacketFluidSync(tile.getBlockPos(), f));
	    }
	  }
	*/
}
