package com.faojen.exoterra.capabilities.crystalcatalyst;

import javax.annotation.Nonnull;

import com.faojen.exoterra.blocks.crystalcatalyst.CrystalCatalystBE;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class CrystalCatalystItemHandler extends ItemStackHandler {
    private final CrystalCatalystBE crystalCatalystBE;

    public CrystalCatalystItemHandler(CrystalCatalystBE crystalCatalystBE) {
        super(3);
        this.crystalCatalystBE = crystalCatalystBE;
    }

    @Override
    protected void onContentsChanged(int slot) {
    	crystalCatalystBE.setChanged();
    }

    @Nonnull
    @Override
    
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
    	
        if (slot == CrystalCatalystBE.Slots.INPUTFUEL.getId() && (! stack.is(Items.DIAMOND) && stack.getCount() == 1))
            return stack;
        
        if (slot == CrystalCatalystBE.Slots.INPUTGHAST.getId() && (! stack.is(Items.GHAST_TEAR) && stack.getCount() == 1))
            return stack;

        return super.insertItem(slot, stack, simulate);
    }
}