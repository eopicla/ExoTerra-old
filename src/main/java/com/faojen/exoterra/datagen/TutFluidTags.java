package com.faojen.exoterra.datagen;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.setup.Registration;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TutFluidTags extends FluidTagsProvider {
	public TutFluidTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, ExoTerra.MODID, helper);
		
	}

	@Override
	protected void addTags() {
		
		tag(Registration.STELLAR_AQUEOUS)
			.add(Registration.AQUEOUS_STELLAR.get());
		
	}
	
	@Override
	public String getName() { return "ExoTerra Tags"; }
	
}


