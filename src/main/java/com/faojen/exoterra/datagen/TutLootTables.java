package com.faojen.exoterra.datagen;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;


public class TutLootTables extends BaseLootTableProvider {
	
	public TutLootTables(DataGenerator dataGeneratorIn) {super(dataGeneratorIn); }
	
	@Override
	protected void addTables() {
		
		lootTables.put(Registration.CHARGING_STATION.get(), createStandardTable("charge_station", Registration.CHARGING_STATION.get(), Registration.CHARGING_STATION_TILE.get()));
		
		lootTables.put(Registration.STELLAR_ORE_OVERWORLD.get(), createSilkTouchTable("stellar_ore_overworld", Registration.STELLAR_ORE_OVERWORLD.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
        lootTables.put(Registration.STELLAR_ORE_NETHER.get(), createSilkTouchTable("stellar_ore_nether", Registration.STELLAR_ORE_NETHER.get(), Registration.INF_RAW_STELLAR.get(), 1, 2));
        lootTables.put(Registration.STELLAR_ORE_END.get(), createSilkTouchTable("stellar_ore_end", Registration.STELLAR_ORE_END.get(), Registration.INF_RAW_STELLAR.get(), 1, 3));
        lootTables.put(Registration.STELLAR_ORE_DEEPSLATE.get(), createSilkTouchTable("stellar_ore_deepslate", Registration.STELLAR_ORE_DEEPSLATE.get(), Registration.INF_RAW_STELLAR.get(), 1, 1));
	}
} 