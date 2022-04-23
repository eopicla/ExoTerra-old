
package com.faojen.exoterra.item;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class AqueousStellarItem extends BucketItem {
	public AqueousStellarItem() {
		super(Registration.AQUEOUS_STELLAR,
				new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ModSetup.ITEM_GROUP));
	}

}
