package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutItemModels extends ItemModelProvider {
	
	public TutItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, ExoTerra.MODID, existingFileHelper);
	}
	
	@Override
	protected void registerModels() {
		withExistingParent(Registration.STELLAR_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_overworld"));
		withExistingParent(Registration.STELLAR_ORE_NETHER_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_nether"));
		withExistingParent(Registration.STELLAR_ORE_END_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_end"));
		withExistingParent(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_deepslate"));
		
		singleTexture(Registration.INF_RAW_STELLAR.get().getRegistryName().getPath(),
			mcLoc("item/generated"),
				"layer0", modLoc("items/inf_raw_stellar"));
		singleTexture(Registration.INF_REFINED_STELLAR.get().getRegistryName().getPath(),
				mcLoc("item/generated"),
					"layer0", modLoc("items/inf_refined_stellar"));
		
		 String path1 = Registration.CHARGING_STATION.get().getRegistryName().getPath();
         getBuilder(path1).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path1)));
	}
}