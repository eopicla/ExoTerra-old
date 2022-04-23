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
		simpleBlock(Registration.STELLAR_ORE_OVERWORLD.get());
		simpleBlock(Registration.STELLAR_ORE_NETHER.get());
		simpleBlock(Registration.STELLAR_ORE_END.get());
		simpleBlock(Registration.STELLAR_ORE_DEEPSLATE.get());
		chargingStationModel();

	}

	private void chargingStationModel() {

		horizontalBlock(Registration.CHARGING_STATION.get(),
				models().orientableWithBottom(Registration.CHARGING_STATION.get().getRegistryName().getPath(),
						modLoc("block/charging_station_side"), modLoc("block/charging_station_fronton"),
						modLoc("block/charging_station_bottom"), modLoc("block/charging_station_top")));

	}
}
