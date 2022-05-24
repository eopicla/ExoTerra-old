package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import net.minecraft.world.item.Item;

public class AluminumFilter extends Item {

    public AluminumFilter() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .defaultDurability(512)
                .durability(512));
    }

}
