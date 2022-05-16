package com.faojen.exoterra.blocks.machine.crystalcatalyst;

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

public class CrystalCatalystContainer extends AbstractContainerMenu {
    private static final int SLOTS = 3;

    public final ContainerData data;
    public ItemStackHandler handler;

    // Tile can be null and shouldn't be used for accessing any data that needs to be up to date on both sides
    private CrystalCatalystBE tile;

    public CrystalCatalystContainer(int windowId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this((CrystalCatalystBE) playerInventory.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(6), windowId, playerInventory, new ItemStackHandler(4));
    }

    public CrystalCatalystContainer(@Nullable CrystalCatalystBE tile, ContainerData crystalCatalystData, int windowId, Inventory playerInventory, ItemStackHandler handler) {
        super(Registration.CRYSTAL_CATALYST_CONTAINER.get(), windowId);

        this.handler = handler;
        this.tile = tile;

        this.data = crystalCatalystData;
        this.setup(playerInventory);

        addDataSlots(crystalCatalystData);
    }

    public void setup(Inventory inventory) {
    	// InputFuel slot
        addSlot(new RestrictedSlot(handler, 0, 12, 34));
        // InputGhast slot
        addSlot(new RestrictedSlot(handler, 1, 12, 54));
        // Output slot
        addSlot(new RestrictedSlot(handler, 2, 80, 60));

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
    
    public int getMaxCapacity() {
        return this.data.get(1);
    }

    public int getFluidStored() {
        return this.data.get(0);
    }

    public int getMaxBurn() {
        return this.data.get(3);
    }

    public int getRemaining() {
        return this.data.get(2);
    }
    
    public int getCrystallizing() {
        return this.data.get(4);
    }
    
    public int getMaxCrystallizing() {
        return this.data.get(5);
    }

    static class RestrictedSlot extends SlotItemHandler {
        public RestrictedSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(@Nonnull ItemStack stack) {
        	
        	 if (getSlotIndex() == CrystalCatalystBE.Slots.INPUTFUEL.getId() && stack.getCount() == 1)
        		 return stack.getItem() == Items.DIAMOND;
            
            if (getSlotIndex() == CrystalCatalystBE.Slots.INPUTGHAST.getId() && stack.getCount() == 1)
                return stack.getItem() == Items.GHAST_TEAR;
            
            if (getSlotIndex() == CrystalCatalystBE.Slots.OUTPUT.getId())
                return false;

            return super.mayPlace(stack);
		}
    }
}
