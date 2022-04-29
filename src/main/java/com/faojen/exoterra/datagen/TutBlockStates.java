package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutBlockStates extends BlockStateProvider {

	public TutBlockStates(DataGenerator gen, ExistingFileHelper helper) {
		super(gen, ExoTerra.MODID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
/*
 * 	ORES
 */
		simpleBlock(Registration.STELLAR_ORE_OVERWORLD.get());
		simpleBlock(Registration.STELLAR_ORE_NETHER.get());
		simpleBlock(Registration.STELLAR_ORE_END.get());
		simpleBlock(Registration.STELLAR_ORE_DEEPSLATE.get());
		simpleBlock(Registration.BAUXITE_ORE_OVERWORLD.get());
		simpleBlock(Registration.BAUXITE_ORE_DEEPSLATE.get());
/*
 * 	BLOCKS	
 */
		simpleBlock(Registration.MACHINE_BODY.get());
/*
 * 	COMPLEX MODELS
 */
		stellarConverterModel();
		fabricationBenchModel();

	}

	private void stellarConverterModel() {

		horizontalBlock(Registration.STELLAR_CONVERTER.get(),
				models().orientableWithBottom(Registration.STELLAR_CONVERTER.get().getRegistryName().getPath(),
						modLoc("block/stellar_converter_side"), modLoc("block/stellar_converter_fronton"),
						modLoc("block/stellar_converter_bottom"), modLoc("block/stellar_converter_top")));

	}
	
	private void fabricationBenchModel() {

		horizontalBlock(Registration.FABRICATION_BENCH.get(),
				models().orientableWithBottom(Registration.FABRICATION_BENCH.get().getRegistryName().getPath(),
						modLoc("block/fabrication_bench_side"), modLoc("block/fabrication_bench_fronton"),
						modLoc("block/fabrication_bench_bottom"), modLoc("block/fabrication_bench_top")));

	}
}
