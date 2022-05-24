package com.faojen.exoterra.setup;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import com.faojen.exoterra.items.AqueousStellarItem;
import com.faojen.exoterra.ExoTerra;

public class ExoTerraItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ExoTerra.MODID);
	public static final RegistryObject<Item> AQUEOUS_STELLAR_BUCKET = REGISTRY.register("aqueous_stellar_bucket", () -> new AqueousStellarItem());
}
