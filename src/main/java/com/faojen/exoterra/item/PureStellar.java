package com.faojen.exoterra.item;

import com.faojen.exoterra.setup.ModSetup;
import net.minecraft.world.item.Item;

public class PureStellar extends Item {
	
	public PureStellar() {
		super(new Item.Properties()
				.tab(ModSetup.ITEM_GROUP));
		
		//static String STELLAR_BURN_TIME = "1600";
	}
	
}
