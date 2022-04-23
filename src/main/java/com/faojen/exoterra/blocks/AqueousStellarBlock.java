package com.faojen.exoterra.blocks;

import com.faojen.exoterra.setup.Registration;

import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

	
	public class AqueousStellarBlock extends LiquidBlock {
		public AqueousStellarBlock() {
			super(() -> (FlowingFluid) Registration.AQUEOUS_STELLAR.get(),
					BlockBehaviour.Properties.of(Material.WATER, MaterialColor.COLOR_LIGHT_BLUE).strength(10f).hasPostProcess((bs, br, bp) -> true)
					.emissiveRendering((bs, br, bp) -> true).lightLevel(s -> 15));


			
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
			return true;
		}
	}
