package com.faojen.exoterra.blocks.fabricationbench;

import java.util.List;

import javax.annotation.Nullable;

import com.faojen.exoterra.utils.MagicHelpers;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class FabricationBenchItem extends BlockItem {

    public FabricationBenchItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    ItemStack sstack;
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn); 

       
        
        int power = stack.getOrCreateTag().getInt("energy");
        int powerload = stack.getOrCreateTag().getInt("powerload");
        
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
        Integer fluidamount = fluid.getAmount();
        if (power == 0)
            return;
        
       tooltip.add(new TranslatableComponent("screen.exoterra.energy", MagicHelpers.withSuffix(power), MagicHelpers.withSuffix(FabricationBenchBE.ENERGY_CAPACITY_PUB)));
       tooltip.add(new TranslatableComponent("screen.exoterra.fluid", MagicHelpers.withSuffix(fluidamount), MagicHelpers.withSuffix(FabricationBenchBE.FLUID_CAP_PUB)));
       tooltip.add(new TranslatableComponent("screen.exoterra.powerload", MagicHelpers.withSuffix(powerload)));
       
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level worldIn, @Nullable Player player, ItemStack stack, BlockState state) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        if (te instanceof FabricationBenchBE) {
        	FabricationBenchBE station = (FabricationBenchBE) te;
        	
        	FluidStack fluid = FluidStack.loadFluidStackFromNBT(stack.getTag());
        	
            station.energyStorage.receiveEnergy(stack.getOrCreateTag().getInt("energy"), false);
            station.fluidStorage.setFluid(fluid);
            
        }

        return super.updateCustomBlockEntityTag(pos, worldIn, player, stack, state);
    }
}
