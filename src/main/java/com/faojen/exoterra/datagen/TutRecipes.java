package com.faojen.exoterra.datagen;

import java.util.function.Consumer;

// import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

public class TutRecipes extends RecipeProvider {
	
	public TutRecipes(DataGenerator generatorIn) { super(generatorIn); }
	
	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

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