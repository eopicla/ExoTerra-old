package com.faojen.exoterra.datagen;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;


public class TutLootTables extends BaseLootTableProvider {
	
	public TutLootTables(DataGenerator dataGeneratorIn) {super(dataGeneratorIn); }
	
	@Override
	protected void addTables() {
		
		lootTables.put(Registration.STELLAR_CONVERTER.get(), createStandardTable("stellar_converter", Registration.STELLAR_CONVERTER.get(), Registration.STELLAR_CONVERTER_BE.get()));
		
		lootTables.put(Registration.STELLAR_ORE_OVERWORLD.get(), createSilkTouchTable("stellar_ore_overworld", Registration.STELLAR_ORE_OVERWORLD.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
        lootTables.put(Registration.STELLAR_ORE_NETHER.get(), createSilkTouchTable("stellar_ore_nether", Registration.STELLAR_ORE_NETHER.get(), Registration.INF_RAW_STELLAR.get(), 1, 2));
        lootTables.put(Registration.STELLAR_ORE_END.get(), createSilkTouchTable("stellar_ore_end", Registration.STELLAR_ORE_END.get(), Registration.INF_RAW_STELLAR.get(), 1, 3));
        lootTables.put(Registration.STELLAR_ORE_DEEPSLATE.get(), createSilkTouchTable("stellar_ore_deepslate", Registration.STELLAR_ORE_DEEPSLATE.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
	}
} 