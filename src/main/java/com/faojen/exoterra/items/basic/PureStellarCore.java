package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class PureStellarCore extends Item {

    public PureStellarCore() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .rarity(Rarity.EPIC)
                .stacksTo(1)
                .craftRemainder(Registration.INF_STELLAR_CORE.get()));

        //static String STELLAR_BURN_TIME = "1600";
    }

    public boolean isFoil(){
        return true;
    }

}
