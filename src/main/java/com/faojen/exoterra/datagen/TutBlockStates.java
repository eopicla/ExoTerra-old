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
		simpleBlock(Registration.EXO_GLASS_BLOCK.get());
		simpleBlock(Registration.FACETED_ALUMINUM_BLOCK.get());
/*
 * 	COMPLEX MODELS
 */
		stellarConverterModel();
		inferiorPowerBankModel();
		commonPowerBankModel();
		superiorPowerBankModel();
		crystalCatalystModel();
		stellarAccumulatorModel();

	}

	private void stellarAccumulatorModel() {

		horizontalBlock(Registration.STELLAR_ACCUMULATOR.get(),
				models().orientableWithBottom(Registration.STELLAR_ACCUMULATOR.get().getRegistryName().getPath(),
						modLoc("block/stellar_accumulator_side"), modLoc("block/stellar_accumulator_fronton"),
						modLoc("block/stellar_accumulator_bottom"), modLoc("block/stellar_accumulator_top")));

	}

	private void crystalCatalystModel() {

		horizontalBlock(Registration.CRYSTAL_CATALYST.get(),
				models().orientableWithBottom(Registration.CRYSTAL_CATALYST.get().getRegistryName().getPath(),
						modLoc("block/fabrication_bench_side"), modLoc("block/fabrication_bench_fronton"),
						modLoc("block/fabrication_bench_bottom"), modLoc("block/fabrication_bench_top")));

	}

	private void superiorPowerBankModel() {

		horizontalBlock(Registration.SUPERIOR_POWER_BANK.get(),
				models().orientableWithBottom(Registration.SUPERIOR_POWER_BANK.get().getRegistryName().getPath(),
						modLoc("block/superior_power_bank_side"), modLoc("block/superior_power_bank_fronton"),
						modLoc("block/superior_power_bank_bottom"), modLoc("block/superior_power_bank_top")));

	}
	
	private void inferiorPowerBankModel() {

		horizontalBlock(Registration.INFERIOR_POWER_BANK.get(),
				models().orientableWithBottom(Registration.INFERIOR_POWER_BANK.get().getRegistryName().getPath(),
						modLoc("block/inferior_power_bank_side"), modLoc("block/inferior_power_bank_fronton"),
						modLoc("block/inferior_power_bank_bottom"), modLoc("block/inferior_power_bank_top")));

	}
	
	private void commonPowerBankModel() {

		horizontalBlock(Registration.COMMON_POWER_BANK.get(),
				models().orientableWithBottom(Registration.COMMON_POWER_BANK.get().getRegistryName().getPath(),
						modLoc("block/common_power_bank_side"), modLoc("block/common_power_bank_fronton"),
						modLoc("block/common_power_bank_bottom"), modLoc("block/common_power_bank_top")));

	}
	
	private void stellarConverterModel() {

		horizontalBlock(Registration.PURIFICATION_BESTOWER.get(),
				models().orientableWithBottom(Registration.PURIFICATION_BESTOWER.get().getRegistryName().getPath(),
						modLoc("block/purification_bestower_side"), modLoc("block/purification_bestower_fronton"),
						modLoc("block/purification_bestower_bottom"), modLoc("block/purification_bestower_top")));

	}
	
}
