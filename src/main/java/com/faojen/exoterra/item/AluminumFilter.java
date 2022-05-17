package com.faojen.exoterra.item;

import com.faojen.exoterra.setup.ModSetup;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AluminumFilter extends Item {

    public AluminumFilter() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .defaultDurability(512)
                .durability(512));
    }

}
