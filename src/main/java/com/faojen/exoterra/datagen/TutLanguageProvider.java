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

		add(Registration.STELLAR_ORE_OVERWORLD.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_NETHER.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_END.get(), "\u00A73Stellar\u00A7r Ore");
		add(Registration.STELLAR_ORE_DEEPSLATE.get(), "\u00A73Stellar\u00A7r Ore");

		add(Registration.INF_RAW_STELLAR.get(), "Raw \u00A73Stellar\u00A7r (Inferior)");
		add(Registration.INF_REFINED_STELLAR.get(), "Refined \u00A73Stellar\u00A7r (Inferior)");

		add(Registration.AQUEOUS_STELLAR_BUCKET.get(), "Bucket of \u00A73Aqueous Stellar\u00A7r");

		addBlock(Registration.STELLAR_CONVERTER, "\u00A73Stellar\u00A7r Purification Bestower");
		add("itemGroup.charginggadgets", "Charging Gadgets");
		add("screen.exoterra.energy", "\u00A74Energy:\u00a7r %s/%s FE");
		add("screen.exoterra.stellar", "\u00A73Stellar:\u00a7r %s MB");
		add("screen.exoterra.no_fuel", "\u00A77Fuel source empty");
		add("screen.exoterra.burn_time", "Burn time left: %ss");
		add("screen.exoterra.purify_time", "\u00A77Time until purification: %ss");
		add("screen.exoterra.fluid", "\u00A73Aqueous Stellar:\u00A7r %s/%s MB");
		add("screen.exoterra.no_energy", "\u00A77Not enough FE to purify");

	}

}