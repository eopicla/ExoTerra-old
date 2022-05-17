package com.faojen.exoterra.blocks.machine.stellaraccumulator;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class StellarAccumulatorItemHandler extends ItemStackHandler {
    private final StellarAccumulatorBE stellarAccumulatorBE;

    public StellarAccumulatorItemHandler(StellarAccumulatorBE stellarAccumulatorBE) {
        super(2);
        this.stellarAccumulatorBE = stellarAccumulatorBE;
    }

    @Override
    protected void onContentsChanged(int slot) {
        stellarAccumulatorBE.setChanged();
    }

    @Nonnull
    @Override

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

        if (slot == StellarAccumulatorBE.Slots.FILTER.getId() && (! stack.is(Registration.INF_REFINED_STELLAR.get()) && getStackInSlot(slot).getCount() == 1))
            return stack;

        return super.insertItem(slot, stack, simulate);
    }
}