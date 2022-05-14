package com.faojen.exoterra.blocks.crystalcatalyst;

import com.faojen.exoterra.utils.MagicHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;

public class CrystalCatalystItem extends BlockItem {

    public CrystalCatalystItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    
    @Override
	public int getBarWidth(ItemStack stack) {
    	FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
    	int fluidAmount = fluid.getAmount();
		// int fluid = stack.getOrCreateTag().getInt("fluid");
		int maxFluid = CrystalCatalystBE.FLUID_CAP_PUB;
		var stored = maxFluid - fluidAmount;

		return Math.round(13.0F - stored * 13.0F / maxFluid);
	}

	@Override
	public int getBarColor(ItemStack stack) {
		FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
    	int fluidAmount = fluid.getAmount();
		int maxFluid = CrystalCatalystBE.FLUID_CAP_PUB;

		float f = Math.max(0.0F, (float) fluidAmount / (float) maxFluid);

		return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return true;
	}
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
    	int fluidAmount = fluid.getAmount();
        if (fluidAmount == 0)
            return;
 
        tooltip.add(new TranslatableComponent("screen.exoterra.stellar", MagicHelpers.withSuffix(fluidAmount), MagicHelpers.withSuffix(CrystalCatalystBE.FLUID_CAP_PUB)));
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level worldIn, @Nullable Player player, ItemStack stack, BlockState state) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        if (te instanceof CrystalCatalystBE) {
        	CrystalCatalystBE station = (CrystalCatalystBE) te;
        	FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
        	
            station.fluidStorage.setFluid(fluid);
        }

        return super.updateCustomBlockEntityTag(pos, worldIn, player, stack, state);
    }
}
