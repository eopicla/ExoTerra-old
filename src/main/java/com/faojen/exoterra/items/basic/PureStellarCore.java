package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class PureStellarCore extends Item {

    public PureStellarCore() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .rarity(Rarity.EPIC)
                .stacksTo(1)
                .craftRemainder(Registration.INF_STELLAR_CORE.get()));

    }

//    public Item getCraftingRemainingItem() {
//        return this.craftingRemainingItem;
//    }

//    @Override
//    public boolean hasCraftingRemainingItem() {
//        return super.hasCraftingRemainingItem();
//    }


    @Override
    public boolean isFoil(ItemStack pStack) {
        return super.isFoil(pStack);
    }

}
