package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
    public class SentientCore extends Item {

        public SentientCore() {
            super(new Item.Properties()
                    .tab(ModSetup.ITEM_GROUP)
                    .rarity(Rarity.EPIC)
                    .stacksTo(1));

        }

        @Override
        public boolean isFoil(ItemStack pStack) {
            return true;
        }

    }

