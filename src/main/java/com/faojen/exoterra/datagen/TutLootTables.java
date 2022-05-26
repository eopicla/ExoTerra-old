package com.faojen.exoterra.datagen;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;


public class TutLootTables extends BaseLootTableProvider {
	
	public TutLootTables(DataGenerator dataGeneratorIn) {super(dataGeneratorIn); }
	
	@Override
	protected void addTables() {
		
		lootTables.put(Registration.PURIFICATION_BESTOWER.get(), createStandardTable("stellar_converter", Registration.PURIFICATION_BESTOWER.get(), Registration.PURIFICATION_BESTOWER_BE.get()));
		lootTables.put(Registration.INFERIOR_POWER_BANK.get(), createStandardTable("inferior_power_bank", Registration.INFERIOR_POWER_BANK.get(), Registration.INFERIOR_POWER_BANK_BE.get()));
		lootTables.put(Registration.COMMON_POWER_BANK.get(), createStandardTable("common_power_bank", Registration.COMMON_POWER_BANK.get(), Registration.COMMON_POWER_BANK_BE.get()));
		lootTables.put(Registration.SUPERIOR_POWER_BANK.get(), createStandardTable("superior_power_bank", Registration.SUPERIOR_POWER_BANK.get(), Registration.SUPERIOR_POWER_BANK_BE.get()));
		lootTables.put(Registration.CRYSTAL_CATALYST.get(), createStandardTable("crystal_catalyst", Registration.CRYSTAL_CATALYST.get(), Registration.CRYSTAL_CATALYST_BE.get()));
		lootTables.put(Registration.STELLAR_ACCUMULATOR.get(), createStandardTable("stellar_accumulator", Registration.STELLAR_ACCUMULATOR.get(), Registration.STELLAR_ACCUMULATOR_BE.get()));
		lootTables.put(Registration.MACHINE_BODY.get(), createStandardTable("machine_body", Registration.MACHINE_BODY.get(), Registration.MACHINE_BODY_BE.get()));

		lootTables.put(Registration.EXO_GLASS_BLOCK.get(), createSimpleTable("exo_glass_block", Registration.EXO_GLASS_BLOCK.get()));
		lootTables.put(Registration.FACETED_ALUMINUM_BLOCK.get(), createSimpleTable("faceted_aluminum_block", Registration.FACETED_ALUMINUM_BLOCK.get()));
		
		lootTables.put(Registration.STELLAR_ORE_OVERWORLD.get(), createSilkTouchTable("stellar_ore_overworld", Registration.STELLAR_ORE_OVERWORLD.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
        lootTables.put(Registration.STELLAR_ORE_NETHER.get(), createSilkTouchTable("stellar_ore_nether", Registration.STELLAR_ORE_NETHER.get(), Registration.INF_RAW_STELLAR.get(), 1, 2));
        lootTables.put(Registration.STELLAR_ORE_END.get(), createSilkTouchTable("stellar_ore_end", Registration.STELLAR_ORE_END.get(), Registration.INF_RAW_STELLAR.get(), 1, 3));
        lootTables.put(Registration.STELLAR_ORE_DEEPSLATE.get(), createSilkTouchTable("stellar_ore_deepslate", Registration.STELLAR_ORE_DEEPSLATE.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
        
        lootTables.put(Registration.BAUXITE_ORE_OVERWORLD.get(), createSilkTouchTable("bauxite_ore_overworld", Registration.BAUXITE_ORE_OVERWORLD.get(), Registration.BAUXITE_CHUNK.get(), 1, 1));
        lootTables.put(Registration.BAUXITE_ORE_DEEPSLATE.get(), createSilkTouchTable("bauxite_ore_deepslate", Registration.BAUXITE_ORE_DEEPSLATE.get(), Registration.BAUXITE_CHUNK.get(), 1, 2));
	}
} 