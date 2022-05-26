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

	// TODO: create item getbarwidth and getbarcolor utils
	// TODO: Fix up lang
	// TODO: Do status blit animation for accumulator
	// TODO: make blockmodel for accumulator.
	// TODO: Figure out how to do voxelshape stuff with current classes
	// TODO (OPTIONAL): Change filter behavior from shitty to durability based.
	// TODO: Once voxelshapes have been figured out, do other blocks with them.
	// TODO: RECIPES for accumulator, and filter.
	// TODO: Figure out capability side stuff and use it to restrict some machines like accumulator etc.
	// TODO (IMPORTANT): Make basic utility blocks of different values: Power Storage, Power gen (coal, stellar item, stellar fluid), Fluid Storage, Chest type thing ????
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
