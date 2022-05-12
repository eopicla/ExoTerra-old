package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {

	public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, ExoTerra.MODID, helper);
	}

	@Override
	protected void addTags() {
		tag(Registration.SPACE_SAFE_ITEM)
			.add(Registration.MACHINE_BODY_ITEM.get())
			.add(Registration.STELLAR_CONVERTER_BI.get())
			.add(Registration.INFERIOR_POWER_BANK_BI.get())
			.add(Registration.COMMON_POWER_BANK_BI.get())
			.add(Registration.FABRICATION_BENCH_BI.get())
			.add(Registration.EXO_GLASS_BLOCK_ITEM.get())
			.add(Registration.FACETED_ALUMINUM_BLOCK_ITEM.get());
		
		tag(Tags.Items.ORES)
			.add(Registration.STELLAR_ORE_OVERWORLD_ITEM.get())
			.add(Registration.STELLAR_ORE_NETHER_ITEM.get())
			.add(Registration.STELLAR_ORE_END_ITEM.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get())
			.add(Registration.BAUXITE_ORE_OVERWORLD_ITEM.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE_ITEM.get());
		
		tag(Registration.EXOTERRA_MINERALS)
			.add(Registration.BAUXITE_CHUNK.get())
			.add(Registration.ALLUMINUM_INGOT.get());
		
		tag(Registration.EXOTERRA_ORE_ITEM)
			.add(Registration.BAUXITE_ORE_OVERWORLD_ITEM.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE_ITEM.get());
		
		tag(Registration.STELLAR_ORE_ITEM)
			.add(Registration.STELLAR_ORE_OVERWORLD_ITEM.get())
			.add(Registration.STELLAR_ORE_NETHER_ITEM.get())
			.add(Registration.STELLAR_ORE_END_ITEM.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get());

		tag(Registration.STELLAR_REFINED)
			.add(Registration.INF_REFINED_STELLAR.get());

		tag(Registration.EXOTERRA_ITEMS)
			.add(Registration.FACETED_ALLUMINUM_PART.get())
			.add(Registration.FACETED_ALLUMINUM_PANEL.get())
			.add(Registration.FLUID_OUTLET.get())
			.add(Registration.FRACTURIZER.get())
			.add(Registration.INF_STELLAR_CORE.get())
			.add(Registration.INF_STELLAR_PART.get())
			.add(Registration.INTERFACE_PANEL.get())
			.add(Registration.MACHINE_BODY_ITEM.get())
			.add(Registration.BAUXITE_CHUNK.get())
			.add(Registration.ALLUMINUM_INGOT.get());

		tag(Registration.COMPONENTS)
			.add(Registration.FACETED_ALLUMINUM_PART.get())
			.add(Registration.FACETED_ALLUMINUM_PANEL.get())
			.add(Registration.FLUID_OUTLET.get())
			.add(Registration.FRACTURIZER.get())
			.add(Registration.MACHINE_BODY_ITEM.get())
			.add(Registration.INF_STELLAR_CORE.get())
			.add(Registration.INF_STELLAR_PART.get())
			.add(Registration.INTERFACE_PANEL.get());

	}

	@Override
	public String getName() {
		return "ExoTerra Tags";
	}

}