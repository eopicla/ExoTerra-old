package com.faojen.exoterra.datagen;

import static com.faojen.exoterra.setup.ModSetup.TAB_NAME;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class TutLanguageProvider extends LanguageProvider {

	public TutLanguageProvider(DataGenerator gen, String locale) {
		super(gen, ExoTerra.MODID, locale);
	}

	@Override
	protected void addTranslations() {
		add("itemGroup." + TAB_NAME, "ExoTerra");
/*
 * 		ORES
 */
		add(Registration.STELLAR_ORE_OVERWORLD.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_NETHER.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_END.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_DEEPSLATE.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.BAUXITE_ORE_OVERWORLD.get(), "Bauxite Ore");
		add(Registration.BAUXITE_ORE_DEEPSLATE.get(), "Bauxite Ore");
/*
 * 	ITEMS
 */
		add(Registration.BAUXITE_CHUNK.get(), "Bauxite Chunk");
		add(Registration.ALLUMINUM_INGOT.get(), "Aluminum Ingot");
/*
 * 		STELLAR
 */
		add(Registration.INF_RAW_STELLAR.get(), "Raw \u00A73Stellar\u00A7r (Inferior)");
		add(Registration.INF_REFINED_STELLAR.get(), "Refined \u00A73Stellar\u00A7r (Inferior)");
/*
 * 		FLUIDS
 */
		add(Registration.AQUEOUS_STELLAR_BUCKET.get(), "Bucket of \u00A73Aqueous Stellar\u00A7r");
/*
 * 		MACHINES
 */
		addBlock(Registration.STELLAR_CONVERTER, "\u00A73Stellar\u00A7r Purification Bestower");
		addBlock(Registration.FABRICATION_BENCH, "Component Serializer");
/*
 * 		BASIC BLOCKS
 */
		addBlock(Registration.MACHINE_BODY, "Machine Body");	
/*
 * 		CRAFTING COMPONENTS
 */
		addItem(Registration.FACETED_ALLUMINUM_PART, "Faceted Alluminum Part");
		addItem(Registration.FACETED_ALLUMINUM_PANEL, "Faceted Alluminum Panel");
		addItem(Registration.FLUID_OUTLET, "Fluid Outlet"); 
		addItem(Registration.FRACTURIZER, "Fracturizer");
		addItem(Registration.INF_STELLAR_CORE, "\u00A73Stellar Core\u00A7r (Inferior)");
		addItem(Registration.INF_STELLAR_PART, "\u00A73Stellar Component\u00A7r (Inferior)");
		addItem(Registration.INTERFACE_PANEL, "Interface Panel");
/*
 * 		SCREEN LANG
 */
		add("itemGroup.charginggadgets", "Charging Gadgets");
		add("screen.exoterra.energy", "\u00A74Energy:\u00a7r %s/%s FE");
		add("screen.exoterra.stellar", "\u00A73Stellar:\u00a7r %s MB");
		add("screen.exoterra.no_fuel", "\u00A77Fuel source empty");
		add("screen.exoterra.burn_time", "Burn time left: %ss");
		add("screen.exoterra.purify_time", "\u00A77Time until purification: %ss");
		add("screen.exoterra.fluid", "\u00A73Stellar:\u00A7r %s/%s MB");
		add("screen.exoterra.no_energy", "\u00A77Not enough FE to purify");
		add("screen.exoterra.empty", "Empty");
		add("screen.exoterra.powerload", "\u00A77Load:\u00A7r %s FE/Tick");
		add("screen.exoterra.inventorytip", "%s/%s");
		add("screen.exoterra.test", "facpanel: %s");
		
		add("screen.exoterra.stellar_converter_guititle", "Purification Bestower");

	}

}