package com.faojen.exoterra.capabilities.purificationbestower;

import com.faojen.exoterra.blocks.purificationbestower.PurificationBestowerBE;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class PurificationBestowerItemHandler extends ItemStackHandler {
    private final PurificationBestowerBE stellarConverterBE;

    public PurificationBestowerItemHandler(PurificationBestowerBE stellarConverterBE) {
        super(2);
        this.stellarConverterBE = stellarConverterBE;
    }

    @Override
    protected void onContentsChanged(int slot) {
    	stellarConverterBE.setChanged();
    }

    @Nonnull
    @Override
    
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == PurificationBestowerBE.Slots.FUEL.getId() && stack.getItem() == Items.BUCKET)
            return super.insertItem(slot, stack, simulate);

        if (slot == PurificationBestowerBE.Slots.FUEL.getId() && ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) <= 0)
            return stack;
        
        if (slot == PurificationBestowerBE.Slots.STELLAR.getId() && (! stack.is(Registration.INF_REFINED_STELLAR.get()) && getStackInSlot(slot).getCount() > 0))
            return stack;
        
        if (slot == PurificationBestowerBE.Slots.STELLAR.getId() && (! stack.is(Registration.INF_RAW_STELLAR.get()) && getStackInSlot(slot).getCount() > 0))
            return stack;

        return super.insertItem(slot, stack, simulate);
    }
}