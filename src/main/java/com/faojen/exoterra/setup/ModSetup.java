package com.faojen.exoterra.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	public static final String TAB_NAME = "exoterra";
	
	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
		@Override
		public ItemStack makeIcon() { return new ItemStack(Registration.INF_REFINED_STELLAR.get()); }
	};
	
	public static void init(FMLCommonSetupEvent event) {
		
	}
}
