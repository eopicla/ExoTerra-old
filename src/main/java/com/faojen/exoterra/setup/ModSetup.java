package com.faojen.exoterra.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup {

	public static final String IGROUPNAME = "exoterraitems";
	public static final String MGROUPNAME = "exoterramachines";
	
	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(IGROUPNAME) {
		@Override
		public ItemStack makeIcon() { return new ItemStack(Registration.FACETED_ALLUMINUM_PART.get()); }
	};
	
	public static final CreativeModeTab MACHINE_GROUP = new CreativeModeTab(MGROUPNAME) {
		@Override
		public ItemStack makeIcon() { return new ItemStack(Registration.INFERIOR_POWER_BANK_BI.get()); }
	};
	
	public static void init(FMLCommonSetupEvent event) {
		
	}
}
