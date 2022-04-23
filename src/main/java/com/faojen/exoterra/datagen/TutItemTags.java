package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemTags extends ItemTagsProvider {
	
	public TutItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper ) {
		super(generator, blockTags, ExoTerra.MODID, helper);
		}
	
	@Override
	protected void addTags() {
		tag(Tags.Items.ORES)
			.add(Registration.STELLAR_ORE_OVERWORLD_ITEM.get())
			.add(Registration.STELLAR_ORE_NETHER_ITEM.get())
			.add(Registration.STELLAR_ORE_END_ITEM.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get());
		
		tag(Registration.STELLAR_ORE_ITEM)
			.add(Registration.STELLAR_ORE_OVERWORLD_ITEM.get())
			.add(Registration.STELLAR_ORE_NETHER_ITEM.get())
			.add(Registration.STELLAR_ORE_END_ITEM.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get());
		
		tag(Registration.STELLAR_REFINED)
			.add(Registration.INF_REFINED_STELLAR.get());
		

	}
	
	@Override
	public String getName() { return "ExoTerra Tags"; }
	
}