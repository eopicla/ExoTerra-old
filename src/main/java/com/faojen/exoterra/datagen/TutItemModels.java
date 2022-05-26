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

/*
 * 	SIMPLE ITEMS
 */
		singleTexture(Registration.BAUXITE_CHUNK.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/bauxite_chunk"));
		singleTexture(Registration.ALLUMINUM_INGOT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/alluminum_ingot"));
		singleTexture(Registration.ALUMINUM_FILTER.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/aluminum_filter"));
/*
 * 	ORES
 */
		withExistingParent(Registration.STELLAR_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_overworld"));
		withExistingParent(Registration.STELLAR_ORE_NETHER_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_nether"));
		withExistingParent(Registration.STELLAR_ORE_END_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_end"));
		withExistingParent(Registration.STELLAR_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/stellar_ore_deepslate"));
		withExistingParent(Registration.BAUXITE_ORE_OVERWORLD_ITEM.get().getRegistryName().getPath(), modLoc("block/bauxite_ore_overworld"));
		withExistingParent(Registration.BAUXITE_ORE_DEEPSLATE_ITEM.get().getRegistryName().getPath(), modLoc("block/bauxite_ore_deepslate"));
/*
 * 	BLOCKS
 */
        String exglass = Registration.EXO_GLASS_BLOCK.get().getRegistryName().getPath();
        getBuilder(exglass).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exglass)));
        String facblock = Registration.FACETED_ALUMINUM_BLOCK.get().getRegistryName().getPath();
        getBuilder(facblock).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + facblock)));
/*
 * 	STELLAR
 */
		singleTexture(Registration.INF_RAW_STELLAR.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_raw_stellar"));
		singleTexture(Registration.INF_REFINED_STELLAR.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_refined_stellar"));
		singleTexture(Registration.PURE_STELLAR.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/pure_stellar"));
/*
 * 	COMPONENTS
 */
		singleTexture(Registration.FACETED_ALLUMINUM_PANEL.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/faceted_alluminum_panel"));
		singleTexture(Registration.FACETED_ALLUMINUM_PART.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/faceted_alluminum_part"));
		singleTexture(Registration.FLUID_OUTLET.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/fluid_outlet"));
		singleTexture(Registration.FRACTURIZER.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/fracturizer"));
		singleTexture(Registration.INF_STELLAR_CORE.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_stellar_core"));
		singleTexture(Registration.INF_STELLAR_PART.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_stellar_part"));
		singleTexture(Registration.INTERFACE_PANEL.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/interface_panel"));
		singleTexture(Registration.PURE_STELLAR_CORE.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/pure_stellar_core"));
		singleTexture(Registration.SENTIENT_CORE.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/sentient_core"));
		singleTexture(Registration.SOUL_CAPACITOR_FULL.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/soul_capacitor_filled"));
		singleTexture(Registration.SOUL_CAPACITOR_EMPTY.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/soul_capacitor_empty"));

		singleTexture(Registration.LUNAR_WANE_CAST.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/lunar_wane_cast"));
		singleTexture(Registration.TREMI_PIPE.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/tremi_pipe"));
		singleTexture(Registration.DIFFERENTIAL_GURDEL.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/differential_gurdel_empty"));
		singleTexture(Registration.ATTRACTION_MATRIX.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/attraction_matrix"));
		singleTexture(Registration.DEPTH_CONDITIONER.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/depth_conditioner"));

/*
 * 	MACHINES
 */
		 String purbest = Registration.PURIFICATION_BESTOWER.get().getRegistryName().getPath();
         getBuilder(purbest).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + purbest)));
         
         String infbank = Registration.INFERIOR_POWER_BANK.get().getRegistryName().getPath();
         getBuilder(infbank).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + infbank)));
         
         String combank = Registration.COMMON_POWER_BANK.get().getRegistryName().getPath();
         getBuilder(combank).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + combank)));
         
         String supbank = Registration.SUPERIOR_POWER_BANK.get().getRegistryName().getPath();
         getBuilder(supbank).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + supbank)));
         
         String crycat = Registration.CRYSTAL_CATALYST.get().getRegistryName().getPath();
         getBuilder(crycat).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + crycat)));

		String stellac = Registration.STELLAR_ACCUMULATOR.get().getRegistryName().getPath();
		getBuilder(stellac).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + stellac)));

		String macbod = Registration.MACHINE_BODY.get().getRegistryName().getPath();
		getBuilder(macbod).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + macbod)));
	}
}