package com.faojen.exoterra.setup;

import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchScreen;
import com.faojen.exoterra.blocks.stellarconverter.StellarConverterScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
    	
        event.enqueueWork(() -> {
        	
            MenuScreens.register(Registration.STELLAR_CONVERTER_CONTAINER.get(), StellarConverterScreen::new);
            MenuScreens.register(Registration.FABRICATION_BENCH_CONTAINER.get(), FabricationBenchScreen::new);
            
            ItemBlockRenderTypes.setRenderLayer(Registration.AQUEOUS_STELLAR.get(), renderType -> renderType == RenderType.translucent());
			ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_AQUEOUS_STELLAR.get(), renderType -> renderType == RenderType.translucent());

            
            
        });
    }
}