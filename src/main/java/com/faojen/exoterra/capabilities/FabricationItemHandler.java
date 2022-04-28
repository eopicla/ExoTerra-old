package com.faojen.exoterra.capabilities;

import javax.annotation.Nonnull;

import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchBE;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class FabricationItemHandler extends ItemStackHandler {
    private final FabricationBenchBE fabricationBenchBE;

    public FabricationItemHandler(FabricationBenchBE fabricationBenchBE) {
        super(2);
        this.fabricationBenchBE = fabricationBenchBE;
    }

    @Override
    protected void onContentsChanged(int slot) {
    	fabricationBenchBE.setChanged();
    }

    @Nonnull
    @Override
    
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == FabricationBenchBE.Slots.OUTPUT.getId() && stack.getItem() == Registration.BLOCKER.get())
            return super.insertItem(slot, stack, simulate);

        return super.insertItem(slot, stack, simulate);
    }
}