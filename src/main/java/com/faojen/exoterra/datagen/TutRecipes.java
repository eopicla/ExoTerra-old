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

//	STELLAR CONVERTER
		ShapedRecipeBuilder.shaped(Registration.STELLAR_CONVERTER_BI.get())
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
			.pattern("f f")
			.pattern("fff")
			
			.define('f', Registration.FACETED_ALLUMINUM_PANEL.get())
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
					.unlockedBy("stellar", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
					.save(consumer);

					
					// smelting silktouched ore to ingot
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.EXOTERRA_ORE_ITEM), 
							Registration.BAUXITE_CHUNK.get(), 8.0f, 300)
					.unlockedBy("has_ore", has(Registration.EXOTERRA_ORE_ITEM))
					.save(consumer, "bauxite_chunk");
					
					// smelting chunk to ingot
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.BAUXITE_CHUNK.get()), 
							Registration.ALLUMINUM_INGOT.get(), 8.0f, 250)
					.unlockedBy("has_chunk", has(Registration.BAUXITE_CHUNK.get()))
					.save(consumer, "alluminum_ingot");
					
					
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