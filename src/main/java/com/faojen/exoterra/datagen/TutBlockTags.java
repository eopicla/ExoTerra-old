package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockTags extends BlockTagsProvider {
	
	public TutBlockTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, ExoTerra.MODID, helper);
		
	}

	@Override
	protected void addTags() {
		
		tag(Registration.SPACE_SAFE_BLOCK)
			.add(Registration.MACHINE_BODY.get())
			.add(Registration.STELLAR_CONVERTER.get())
			.add(Registration.FABRICATION_BENCH.get());
		
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(Registration.STELLAR_ORE_OVERWORLD.get())
			.add(Registration.STELLAR_ORE_NETHER.get())
			.add(Registration.STELLAR_ORE_END.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE.get())
			.add(Registration.STELLAR_CONVERTER.get())
			.add(Registration.MACHINE_BODY.get())
			.add(Registration.STELLAR_CONVERTER.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_OVERWORLD.get())
			.add(Registration.FABRICATION_BENCH.get());
		
		tag(BlockTags.NEEDS_IRON_TOOL)
			.add(Registration.STELLAR_ORE_OVERWORLD.get())
			.add(Registration.STELLAR_ORE_NETHER.get())
			.add(Registration.STELLAR_ORE_END.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE.get())
			.add(Registration.MACHINE_BODY.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_OVERWORLD.get())
			.add(Registration.FABRICATION_BENCH.get());
		
		tag(Tags.Blocks.ORES)
			.add(Registration.STELLAR_ORE_OVERWORLD.get())
			.add(Registration.STELLAR_ORE_NETHER.get())
			.add(Registration.STELLAR_ORE_END.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_OVERWORLD.get());
		
		tag(Registration.EXOTERRA_ORES)
			.add(Registration.STELLAR_ORE_OVERWORLD.get())
			.add(Registration.STELLAR_ORE_NETHER.get())
			.add(Registration.STELLAR_ORE_END.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_DEEPSLATE.get())
			.add(Registration.BAUXITE_ORE_OVERWORLD.get());		
		
		tag(Registration.STELLAR_ORE)
			.add(Registration.STELLAR_ORE_OVERWORLD.get())
			.add(Registration.STELLAR_ORE_NETHER.get())
			.add(Registration.STELLAR_ORE_END.get())
			.add(Registration.STELLAR_ORE_DEEPSLATE.get());
		
		tag(Registration.EXOTERRA_BLOCKS)
			.add(Registration.MACHINE_BODY.get())
			.add(Registration.STELLAR_CONVERTER.get())
			.add(Registration.FABRICATION_BENCH.get());
		
		
	}
	
	@Override
	public String getName() { return "ExoTerra Tags"; }
	
}
