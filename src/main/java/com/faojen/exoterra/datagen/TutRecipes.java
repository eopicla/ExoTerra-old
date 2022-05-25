package com.faojen.exoterra.datagen;

import java.util.function.Consumer;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
// import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class TutRecipes extends RecipeProvider {
	
	public TutRecipes(DataGenerator generatorIn) { super(generatorIn); }
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		
		// SUP POWER BANK
				ShapedRecipeBuilder.shaped(Registration.SUPERIOR_POWER_BANK_BI.get())
				.pattern("sns")
				.pattern("bcb")
				.pattern("sns")
						
				.define('c', Registration.INFERIOR_POWER_BANK_BI.get())
				.define('n', Items.NETHER_STAR)
				.define('b', Registration.MACHINE_BODY_ITEM.get())
				.define('s', Registration.INF_REFINED_STELLAR.get())
				.group("exoterra_machines")
				.unlockedBy("combank", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.COMMON_POWER_BANK_BI.get()))
				.save(consumer);
		
		// COM POWER BANK
		ShapedRecipeBuilder.shaped(Registration.COMMON_POWER_BANK_BI.get())
		.pattern("sgs")
		.pattern("bcb")
		.pattern("sbs")
				
		.define('c', Registration.INFERIOR_POWER_BANK_BI.get())
		.define('g', Registration.EXO_GLASS_BLOCK_ITEM.get())
		.define('b', Registration.MACHINE_BODY_ITEM.get())
		.define('s', Registration.INF_REFINED_STELLAR.get())
		.group("exoterra_machines")
		.unlockedBy("infbank", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INFERIOR_POWER_BANK_BI.get()))
		.save(consumer);		

		// INF POWER BANK
				ShapedRecipeBuilder.shaped(Registration.INFERIOR_POWER_BANK_BI.get())
				.pattern("gag")
				.pattern("bcb")
				.pattern("gbg")
						
				.define('c', Registration.INF_STELLAR_CORE.get())
				.define('g', Registration.EXO_GLASS_BLOCK_ITEM.get())
				.define('b', Registration.MACHINE_BODY_ITEM.get())
				.define('a', Registration.INTERFACE_PANEL.get())
				.group("exoterra_machines")
				.unlockedBy("stelcore", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_STELLAR_CORE.get()))
				.save(consumer);
		
// EXO GLASS BLOCK
		ShapedRecipeBuilder.shaped(Registration.EXO_GLASS_BLOCK_ITEM.get())
		.pattern("ccc")
		.pattern("cgc")
		.pattern("ccc")
				
		.define('c', Registration.FACETED_ALLUMINUM_PART.get())
		.define('g', Items.GLASS)
		.group("exoterra_blocks")
		.unlockedBy("facpart", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PART.get()))
		.save(consumer);		
		
// FACETED ALUMINUM BLOCK
		ShapedRecipeBuilder.shaped(Registration.FACETED_ALUMINUM_BLOCK_ITEM.get())
		.pattern("ppp")
		.pattern(" i ")
		.pattern("ppp")
		
		.define('p', Registration.FACETED_ALLUMINUM_PANEL.get())
		.define('i', Items.IRON_BLOCK)
		.group("exoterra_blocks")
		.unlockedBy("facpanel", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PANEL.get()))
		.save(consumer);		
		
//	PURIFICATION_BESTOWER
		ShapedRecipeBuilder.shaped(Registration.PURIFICATION_BESTOWER_BI.get())
		.pattern("pfo")
		.pattern("mcm")
		.pattern("mmm")
		
		.define('p', Registration.INTERFACE_PANEL.get())
		.define('f', Registration.FRACTURIZER.get())
		.define('o', Registration.FLUID_OUTLET.get())
		.define('m', Registration.MACHINE_BODY_ITEM.get())
		.define('c', Registration.INF_STELLAR_CORE.get())
		.group("exoterra_machines")
		.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_STELLAR_CORE.get()))
		.save(consumer);

//	FRACTURIZER
		ShapedRecipeBuilder.shaped(Registration.FRACTURIZER.get())
		.pattern("n n")
		.pattern("pgp")
		.pattern("pnp")
		
		.define('n', Tags.Items.NUGGETS_IRON)
		.define('p', Registration.FACETED_ALLUMINUM_PANEL.get())
		.define('g', Tags.Items.GRAVEL)
		// .define('a', Items.AIR)
		.group("exoterra_components")
		.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PANEL.get()))
		.save(consumer);
		
//	MACHINE BODY
			ShapedRecipeBuilder.shaped(Registration.MACHINE_BODY.get())
			.pattern("fff")
			.pattern("fbf")
			.pattern("fff")
			
			.define('f', Registration.FACETED_ALLUMINUM_PANEL.get())
			.define('b', Registration.FACETED_ALUMINUM_BLOCK_ITEM.get())
			// .define('a', Items.AIR)
			.group("exoterra_blocks")
			.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PANEL.get()))
			.save(consumer);
			
//	FACETED ALLUMINUM PANEL
			ShapedRecipeBuilder.shaped(Registration.FACETED_ALLUMINUM_PANEL.get())
			.pattern("pp ")
			.pattern("pp ")
			.pattern("pp ")
			
			.define('p', Registration.FACETED_ALLUMINUM_PART.get())
			// .define('a', Items.AIR)
			.group("exoterra_components")
			.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PART.get()))
			.save(consumer);
			
//	INTERFACE PANEL
			ShapedRecipeBuilder.shaped(Registration.INTERFACE_PANEL.get())
			.pattern("ggg")
			.pattern("gsg")
			.pattern("ppp")
			
			.define('g', Tags.Items.GLASS_PANES)
			.define('s', Registration.INF_STELLAR_PART.get())
			.define('p', Registration.FACETED_ALLUMINUM_PANEL.get())
			.group("exoterra_components")
			.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_STELLAR_PART.get()))
			.save(consumer);
			
//	INFERIOR STELLAR PART
					ShapedRecipeBuilder.shaped(Registration.INF_STELLAR_PART.get())
					.pattern("fff")
					.pattern("fsf")
					.pattern("fff")
					
					.define('f', Registration.FACETED_ALLUMINUM_PART.get())
					.define('s', Registration.INF_REFINED_STELLAR.get())
					.group("exoterra_components")
					.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_REFINED_STELLAR.get()))
					.save(consumer);
				
//	FLUID OUTLET
					ShapedRecipeBuilder.shaped(Registration.FLUID_OUTLET.get())
					.pattern("ppp")
					.pattern("psp")
					.pattern("ppp")
					
					.define('p', Registration.FACETED_ALLUMINUM_PANEL.get())
					.define('s', Items.PISTON)
					.group("exoterra_components")
					.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.FACETED_ALLUMINUM_PANEL.get()))
					.save(consumer);
					
//	INFERIOR STELLAR CORE
					ShapedRecipeBuilder.shaped(Registration.INF_STELLAR_CORE.get())
					.pattern("pdp")
					.pattern("sid")
					.pattern("psp")
					
					.define('p', Registration.FACETED_ALLUMINUM_PANEL.get())
					.define('s', Registration.INF_REFINED_STELLAR.get())
					.define('i', Registration.INF_STELLAR_PART.get())
					.define('d', Tags.Items.GEMS_DIAMOND)
					.group("exoterra_components")
					.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_REFINED_STELLAR.get()))
					.save(consumer);

//	FACETED ALLUMINUM PART
					ShapedRecipeBuilder.shaped(Registration.FACETED_ALLUMINUM_PART.get())
					.pattern("aca")
					.pattern("cac")
					.pattern("aca")
					
					.define('a', Registration.ALLUMINUM_INGOT.get())
					.define('c', Items.COAL)
					.group("exoterra_components")
					.unlockedBy("aluminum", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.ALLUMINUM_INGOT.get()))
					.save(consumer);
// PURE STELLAR CORE
		ShapedRecipeBuilder.shaped(Registration.FACETED_ALLUMINUM_PART.get())
				.pattern("pup")
				.pattern("ucu")
				.pattern("pup")

				.define('c', Registration.INF_STELLAR_CORE.get())
				.define('p', Registration.INF_STELLAR_PART.get())
				.define('u', Registration.PURE_STELLAR.get())
				.group("exoterra_components")
				.unlockedBy("infcore", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_STELLAR_CORE.get()))
				.save(consumer);

					
					// smelting silktouched aluminum to chunk
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.EXOTERRA_ORE_ITEM), 
							Registration.BAUXITE_CHUNK.get(), 8.0f, 300)
					.unlockedBy("has_ore", has(Registration.EXOTERRA_ORE_ITEM))
					.save(consumer, "bauxite_chunk");
					
					// smelting aluminum chunk to ingot
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.BAUXITE_CHUNK.get()), 
							Registration.ALLUMINUM_INGOT.get(), 8.0f, 250)
					.unlockedBy("has_chunk", has(Registration.BAUXITE_CHUNK.get()))
					.save(consumer, "alluminum_ingot");
					
					// smelting silktouched stellar to raw
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.STELLAR_ORE_ITEM), 
							Registration.INF_RAW_STELLAR.get(), 24.0f, 1000)
					.unlockedBy("has_stel_ore", has(Registration.STELLAR_ORE_ITEM))
					.save(consumer, "inf_raw_stellar");
					
					// smelting raw stellar to ingot
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.INF_RAW_STELLAR.get()), 
							Registration.INF_REFINED_STELLAR.get(), 32.0f, 2000)
					.unlockedBy("has_stel_chunk", has(Registration.INF_RAW_STELLAR.get()))
					.save(consumer, "inf_refined_stellar");
					
					
//		ShapedRecipeBuilder.shaped(Registration.POWER_GEN.get())
//		.pattern("#n#")
//		.pattern("xox")
//		.pattern("#x#")
//		
//		.define('x', Tags.Items.DUSTS_REDSTONE)
//		.define('#', Tags.Items.INGOTS_IRON)
//		.define('n', Tags.Items.INGOTS_NETHERITE)
//		.define('o', Registration.INF_REFINED_STELLAR.get())
//		.group("exoterra")
//		.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INF_REFINED_STELLAR.get()))
//		.save(consumer);
//		
//		// smelting silktouched ore to ingot
//		SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.STELLAR_ORE_ITEM), 
//				Registration.INF_RAW_STELLAR.get(), 8.0f, 300)
//		.unlockedBy("has_ore", has(Registration.STELLAR_ORE_ITEM))
//		.save(consumer, "inf_raw_stellar");
//		
//		// smelting chunk to ingot
//		SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.INF_RAW_STELLAR.get()), 
//				Registration.INF_REFINED_STELLAR.get(), 8.0f, 250)
//		.unlockedBy("has_chunk", has(Registration.INF_RAW_STELLAR.get()))
//		.save(consumer, "inf_refined_stellar");
//		
	}
}