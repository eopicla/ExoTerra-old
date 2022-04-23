package com.faojen.exoterra;

import org.slf4j.Logger;

import com.faojen.exoterra.setup.ClientSetup;
import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("exoterra")
public class ExoTerra {

	@SuppressWarnings("unused")
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final String MODID = "exoterra";

	public ExoTerra() {

		// Register the deferred registry
		Registration.init();

		
		
		// Register the setup method for modloading
		IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
		modbus.addListener(ModSetup::init);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));

	}

}
