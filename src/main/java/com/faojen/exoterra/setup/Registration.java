package com.faojen.exoterra.setup;

import static com.faojen.exoterra.ExoTerra.MODID;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.blocks.compowerbank.CommonPowerBankBE;
import com.faojen.exoterra.blocks.compowerbank.CommonPowerBankBlock;
import com.faojen.exoterra.blocks.compowerbank.CommonPowerBankContainer;
import com.faojen.exoterra.blocks.compowerbank.CommonPowerBankItem;
import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchBE;
import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchBlock;
import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchContainer;
import com.faojen.exoterra.blocks.fabricationbench.FabricationBenchItem;
import com.faojen.exoterra.blocks.fluid.AqueousStellarBlock;
import com.faojen.exoterra.blocks.infpowerbank.InferiorPowerBankBE;
import com.faojen.exoterra.blocks.infpowerbank.InferiorPowerBankBlock;
import com.faojen.exoterra.blocks.infpowerbank.InferiorPowerBankContainer;
import com.faojen.exoterra.blocks.infpowerbank.InferiorPowerBankItem;
import com.faojen.exoterra.blocks.stellarconverter.StellarConverterBE;
import com.faojen.exoterra.blocks.stellarconverter.StellarConverterBlock;
import com.faojen.exoterra.blocks.stellarconverter.StellarConverterContainer;
import com.faojen.exoterra.blocks.stellarconverter.StellarConverterItem;
import com.faojen.exoterra.fluid.AqueousStellarFluid;
import com.faojen.exoterra.item.AqueousStellarItem;
import com.faojen.exoterra.item.InfRefinedStellar;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {

	
	private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
	private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

	public static void init() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus(); 
		BLOCKS.register(bus);
		ITEMS.register(bus);
		BLOCK_ENTITIES.register(bus);
		CONTAINERS.register(bus);
		FLUIDS.register(bus);

	} 
	
	// Some common properties for our blocks and items
	public static final BlockBehaviour.Properties ORE_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
	public static final BlockBehaviour.Properties SIMPLE_BLOCK = BlockBehaviour.Properties.of(Material.METAL).strength(2f).requiresCorrectToolForDrops();
	public static final BlockBehaviour.Properties GLASS_BLOCK = BlockBehaviour.Properties.of(Material.GLASS).explosionResistance(6f).noOcclusion();
	public static final BlockBehaviour.Properties SPACE_BLOCK = BlockBehaviour.Properties.of(Material.METAL).explosionResistance(6f);
	public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP);
	public static final Item.Properties MACHINE_PROPERTIES = new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(1);
	public static final Item.Properties FUEL_STELLAR = new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).setNoRepair();
	public static final Item.Properties BLANK = new Item.Properties().stacksTo(1);

	
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //
	// 																										Complex Registers							   																	    //
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //
	// Block
		
	// Item
	public static final RegistryObject<InfRefinedStellar> INF_REFINED_STELLAR = ITEMS.register("inf_refined_stellar", InfRefinedStellar::new);
	
	// Fluid
	
	public static final RegistryObject<Fluid> AQUEOUS_STELLAR = FLUIDS.register("aqueous_stellar", () -> new AqueousStellarFluid.Source());
	public static final RegistryObject<Fluid> FLOWING_AQUEOUS_STELLAR = FLUIDS.register("flowing_aqueous_stellar",
			() -> new AqueousStellarFluid.Flowing());
	
	public static final RegistryObject<Block> AQUEOUS_STELLAR_BLOCK = BLOCKS.register("aqueous_stellar", () -> new AqueousStellarBlock());
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------//
	/*
	 * 	COMPLEX BLOCKS
	 */
			// Stellar Converter
					// Container
						public static final RegistryObject<MenuType<StellarConverterContainer>> STELLAR_CONVERTER_CONTAINER = CONTAINERS.register("stellar_converter_container", 
								() -> IForgeMenuType.create(StellarConverterContainer::new));
					// Block
						public static final RegistryObject<Block> STELLAR_CONVERTER = BLOCKS.register("stellar_converter", StellarConverterBlock::new);
					// Block Entity
						public static final RegistryObject<BlockEntityType<StellarConverterBE>> STELLAR_CONVERTER_BE =
								BLOCK_ENTITIES.register("stellar_converter_be", () -> BlockEntityType.Builder.of(StellarConverterBE::new, STELLAR_CONVERTER.get()).build(null));
					// Item
						public static final RegistryObject<Item> STELLAR_CONVERTER_BI = ITEMS.register("stellar_converter", 
								() -> new StellarConverterItem(STELLAR_CONVERTER.get(), Registration.MACHINE_PROPERTIES));
				
			// Fabrication Bench
					// Container
						public static final RegistryObject<MenuType<FabricationBenchContainer>> FABRICATION_BENCH_CONTAINER = CONTAINERS.register("fabrication_bench_container", 
								() -> IForgeMenuType.create(FabricationBenchContainer::new));
					// Block
						public static final RegistryObject<Block> FABRICATION_BENCH = BLOCKS.register("fabrication_bench", FabricationBenchBlock::new);
					// Block Entity
						public static final RegistryObject<BlockEntityType<FabricationBenchBE>> FABRICATION_BENCH_BE =
								BLOCK_ENTITIES.register("fabrication_bench_be", () -> BlockEntityType.Builder.of(FabricationBenchBE::new, FABRICATION_BENCH.get()).build(null));
					// Item
						public static final RegistryObject<Item> FABRICATION_BENCH_BI = ITEMS.register("fabrication_bench", 
								() -> new FabricationBenchItem(FABRICATION_BENCH.get(), Registration.MACHINE_PROPERTIES));
						
						
			// inferior powerbank
						// Container
							public static final RegistryObject<MenuType<InferiorPowerBankContainer>> INFERIOR_POWER_BANK_CONTAINER = CONTAINERS.register("inferior_power_bank_container", 
									() -> IForgeMenuType.create(InferiorPowerBankContainer::new));
						// Block
							public static final RegistryObject<Block> INFERIOR_POWER_BANK = BLOCKS.register("inferior_power_bank", InferiorPowerBankBlock::new);
						// Block Entity
							public static final RegistryObject<BlockEntityType<InferiorPowerBankBE>> INFERIOR_POWER_BANK_BE =
									BLOCK_ENTITIES.register("inferior_power_bank_be", () -> BlockEntityType.Builder.of(InferiorPowerBankBE::new, INFERIOR_POWER_BANK.get()).build(null));
						// Item
							public static final RegistryObject<Item> INFERIOR_POWER_BANK_BI = ITEMS.register("inferior_power_bank", 
									() -> new InferiorPowerBankItem(INFERIOR_POWER_BANK.get(), Registration.MACHINE_PROPERTIES));
							
			// common powerbank
							// Container
								public static final RegistryObject<MenuType<CommonPowerBankContainer>> COMMON_POWER_BANK_CONTAINER = CONTAINERS.register("common_power_bank_container", 
										() -> IForgeMenuType.create(CommonPowerBankContainer::new));
							// Block
								public static final RegistryObject<Block> COMMON_POWER_BANK = BLOCKS.register("common_power_bank", CommonPowerBankBlock::new);
							// Block Entity
								public static final RegistryObject<BlockEntityType<CommonPowerBankBE>> COMMON_POWER_BANK_BE =
										BLOCK_ENTITIES.register("common_power_bank_be", () -> BlockEntityType.Builder.of(CommonPowerBankBE::new, COMMON_POWER_BANK.get()).build(null));
							// Item
								public static final RegistryObject<Item> COMMON_POWER_BANK_BI = ITEMS.register("common_power_bank", 
										() -> new CommonPowerBankItem(COMMON_POWER_BANK.get(), Registration.MACHINE_PROPERTIES));
						
			
		
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------- //
/*
 * 		SIMPLE BLOCKS	
 */
	public static final RegistryObject<Block> EXO_GLASS_BLOCK = BLOCKS.register("exo_glass_block", () -> new Block(GLASS_BLOCK));				
	public static final RegistryObject<Item> EXO_GLASS_BLOCK_ITEM = fromBlock(EXO_GLASS_BLOCK);
	
	public static final RegistryObject<Block> FACETED_ALUMINUM_BLOCK = BLOCKS.register("faceted_aluminum_block", () -> new Block(SPACE_BLOCK));			
	public static final RegistryObject<Item> FACETED_ALUMINUM_BLOCK_ITEM = fromBlock(FACETED_ALUMINUM_BLOCK);

/*
 * 		ORES
 */
    public static final RegistryObject<Block> STELLAR_ORE_OVERWORLD = BLOCKS.register("stellar_ore_overworld",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> STELLAR_ORE_OVERWORLD_ITEM = fromBlock(STELLAR_ORE_OVERWORLD);
	public static final RegistryObject<Block> STELLAR_ORE_NETHER = BLOCKS.register("stellar_ore_nether",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> STELLAR_ORE_NETHER_ITEM = fromBlock(STELLAR_ORE_NETHER);
	public static final RegistryObject<Block> STELLAR_ORE_END = BLOCKS.register("stellar_ore_end",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> STELLAR_ORE_END_ITEM = fromBlock(STELLAR_ORE_END);
	public static final RegistryObject<Block> STELLAR_ORE_DEEPSLATE = BLOCKS.register("stellar_ore_deepslate",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> STELLAR_ORE_DEEPSLATE_ITEM = fromBlock(STELLAR_ORE_DEEPSLATE);
	
	public static final RegistryObject<Block> BAUXITE_ORE_OVERWORLD = BLOCKS.register("bauxite_ore_overworld",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> BAUXITE_ORE_OVERWORLD_ITEM = fromBlock(BAUXITE_ORE_OVERWORLD);
	
	public static final RegistryObject<Block> BAUXITE_ORE_DEEPSLATE = BLOCKS.register("bauxite_ore_deepslate",() -> new Block(ORE_PROPERTIES));
	public static final RegistryObject<Item> BAUXITE_ORE_DEEPSLATE_ITEM = fromBlock(BAUXITE_ORE_DEEPSLATE);
	
/*
 * 	SIMPLE ITEMS
 */
	public static final RegistryObject<Item> BAUXITE_CHUNK = ITEMS.register("bauxite_chunk", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> BLOCKER = ITEMS.register("blocker", () -> new Item(BLANK));
	public static final RegistryObject<Item> ALLUMINUM_INGOT = ITEMS.register("alluminum_ingot", () -> new Item(ITEM_PROPERTIES));
/*
 * 		MACHINE BLOCKS	
 */

	public static final RegistryObject<Block> MACHINE_BODY = BLOCKS.register("machine_body",() -> new Block(SIMPLE_BLOCK));
	public static final RegistryObject<Item> MACHINE_BODY_ITEM = fromBlock(MACHINE_BODY);

/*
 * 		STELLAR
 */
	public static final RegistryObject<Item> INF_RAW_STELLAR = ITEMS.register("inf_raw_stellar", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> AQUEOUS_STELLAR_BUCKET = ITEMS.register("aqueous_stellar_bucket", () -> new AqueousStellarItem());
	
/*
 * 		CRAFT COMPONENTS
 */
	public static final RegistryObject<Item> FACETED_ALLUMINUM_PANEL = ITEMS.register("faceted_alluminum_panel", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> FACETED_ALLUMINUM_PART = ITEMS.register("faceted_alluminum_part", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> FLUID_OUTLET = ITEMS.register("fluid_outlet", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> FRACTURIZER = ITEMS.register("fracturizer", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> INF_STELLAR_CORE = ITEMS.register("inf_stellar_core", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> INF_STELLAR_PART = ITEMS.register("inf_stellar_part", () -> new Item(ITEM_PROPERTIES));
	public static final RegistryObject<Item> INTERFACE_PANEL = ITEMS.register("interface_panel", () -> new Item(ITEM_PROPERTIES));
	
/*
 * 		BLOCK TAGS
 */
	public static final TagKey<Block> SPACE_SAFE_BLOCK = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(ExoTerra.MODID, "space_safe_block"));
	
	public static final TagKey<Block> EXOTERRA_BLOCKS = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(ExoTerra.MODID, "exoterra_blocks"));
	public static final TagKey<Block> EXOTERRA_ORES = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(ExoTerra.MODID, "exoterra_ores"));
	public static final TagKey<Block> STELLAR_ORE = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(ExoTerra.MODID, "stellar_ore"));
/*
 * 		ITEM TAGS
 */
	public static final TagKey<Item> SPACE_SAFE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "space_safe_item"));
	
	public static final TagKey<Item> EXOTERRA_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "exoterra_items"));
	public static final TagKey<Item> EXOTERRA_MINERALS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "exoterra_minerals"));
	public static final TagKey<Item> EXOTERRA_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "exoterra_ores"));
	public static final TagKey<Item> STELLAR_ORE_ITEM = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "stellar_ore"));
	public static final TagKey<Item> STELLAR_REFINED = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "stellar_refined"));	
	public static final TagKey<Item> COMPONENTS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(ExoTerra.MODID, "components"));	
/*
 * 		FLUID TAGS
 */
	public static final TagKey<Fluid> STELLAR_AQUEOUS = TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation(ExoTerra.MODID, "stellar_aqueous"));
	
	// Convenience function: Take a RegistryObject<Block> and make a corresponding RegistryObject<Item> from it
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
