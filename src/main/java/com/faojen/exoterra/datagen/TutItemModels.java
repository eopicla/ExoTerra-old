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
		String macbod = Registration.MACHINE_BODY.get().getRegistryName().getPath();
        getBuilder(macbod).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + macbod)));
        String exglass = Registration.EXO_GLASS_BLOCK.get().getRegistryName().getPath();
        getBuilder(exglass).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + exglass)));
        String facblock = Registration.FACETED_ALUMINUM_BLOCK.get().getRegistryName().getPath();
        getBuilder(facblock).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + facblock)));
/*
 * 	STELLAR
 */
		singleTexture(Registration.INF_RAW_STELLAR.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_raw_stellar"));
		singleTexture(Registration.INF_REFINED_STELLAR.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0", modLoc("items/inf_refined_stellar"));
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
	}
}