package com.faojen.exoterra.blocks.simple.machinebody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class MachineBodyContainer extends AbstractContainerMenu {
    private static final int SLOTS = 2;

    public final ContainerData data;
    public ItemStackHandler handler;

    // Tile can be null and shouldn't be used for accessing any data that needs to be up to date on both sides
    private MachineBodyBE tile;

    public MachineBodyContainer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this((MachineBodyBE) playerInventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(8), windowId, playerInventory, new ItemStackHandler(2));
    }

    public MachineBodyContainer(@Nullable MachineBodyBE tile, ContainerData machineBodyData, int windowId, Inventory playerInventory, ItemStackHandler handler) {
        super(Registration.MACHINE_BODY_CONTAINER.get(), windowId);

        this.handler = handler;
        this.tile = tile;

        this.data = machineBodyData;
        this.setup(playerInventory);

        addDataSlots(machineBodyData);
    }

    public void setup(Inventory inventory) {
        // Core slot
        addSlot(new RestrictedSlot(handler, 0, 79, 44));

        // Soul slot
        addSlot(new RestrictedSlot(handler, 1, 80, 26));

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 56 + 86;
            addSlot(new Slot(inventory, row, x, y));
        }
        // Slots for the main inventory
        for (int row = 1; row < 4; ++ row) {
            for (int col = 0; col < 9; ++ col) {
                int x = 8 + col * 18;
                int y = row * 18 + (56 + 10);
                addSlot(new Slot(inventory, col + row * 9, x, y));
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack currentStack = slot.getItem();
            itemstack = currentStack.copy();

            if (index < SLOTS) {
                if (!this.moveItemStackTo(currentStack, SLOTS, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(currentStack, 0, SLOTS, false)) {
                return ItemStack.EMPTY;
            }

            if (currentStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        BlockPos pos = this.tile.getBlockPos();
        return this.tile != null && !this.tile.isRemoved() && playerIn.distanceToSqr(new Vec3(pos.getX(), pos.getY(), pos.getZ()).add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    public int getEnergyStored() {
        return this.data.get(0) * 32;
    }

    public int getEnergyCapacity() {
        return this.data.get(1) * 32;
    }

    public int getFluidStored() {
        return this.data.get(2);
    }

    public int getFluidCapacity() {
        return this.data.get(3);
    }

    public int getCoreStability() { return this.data.get(4); }

    public int getCoreIntelligence() { return this.data.get(5); }

    public boolean getIsStabilizing() { if (this.data.get(6) == 1) { return true; } else return false; }

    public boolean getIsCoreInSlot() { if (this.data.get(7) == 1) { return true; } else return false; }

    static class RestrictedSlot extends SlotItemHandler {
        public RestrictedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@Nonnull ItemStack stack) {

            if (getSlotIndex() == MachineBodyBE.Slots.CORE.getId() && stack.getCount() == 1)
                return stack.getItem() == Registration.SENTIENT_CORE.get();

            if (getSlotIndex() == MachineBodyBE.Slots.SOUL.getId() && stack.getCount() == 1)
                return stack.getItem() == Registration.SOUL_CAPACITOR_FULL.get();

            return super.mayPlace(stack);
        }
    }
}
