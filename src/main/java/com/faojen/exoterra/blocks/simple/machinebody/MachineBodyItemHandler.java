package com.faojen.exoterra.blocks.simple.machinebody;

import javax.annotation.Nonnull;

import com.faojen.exoterra.setup.Registration;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;

public class MachineBodyItemHandler extends ItemStackHandler {
    private final MachineBodyBE machineBodyBE;

    public MachineBodyItemHandler(MachineBodyBE machineBodyBE) {
        super(1);
        this.machineBodyBE = machineBodyBE;
    }

    @Override
    protected void onContentsChanged(int slot) {
        machineBodyBE.setChanged();
    }

    @Nonnull
    @Override

    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

        if (slot == MachineBodyBE.Slots.CORE.getId() && (! stack.is(Registration.SENTIENT_CORE.get()) && stack.getCount() == 1))
            return stack;

        return super.insertItem(slot, stack, simulate);
    }
}