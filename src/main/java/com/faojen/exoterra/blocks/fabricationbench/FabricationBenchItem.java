package com.faojen.exoterra.blocks.fabricationbench;

import java.util.List;

import javax.annotation.Nullable;

import com.faojen.exoterra.utils.MagicHelpers;

import net.minecraft.ChatFormatting;
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

public class FabricationBenchItem extends BlockItem {

    public FabricationBenchItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    ItemStack sstack;
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

       
        
        int power = stack.getOrCreateTag().getInt("energy");
        int powerload = stack.getOrCreateTagElement("PowerLoad").getInt("powerload");
        if (power == 0)
            return;
        
        System.out.println("powerload from item: " + powerload);
       tooltip.add(new TranslatableComponent("screen.exoterra.powerload", MagicHelpers.withSuffix(powerload)).withStyle(ChatFormatting.BOLD));
       tooltip.add(new TranslatableComponent("screen.exoterra.energy", MagicHelpers.withSuffix(power), MagicHelpers.withSuffix(FabricationBenchBE.ENERGY_CAPACITY_PUB)).withStyle(ChatFormatting.GREEN));
       
    }

    int test2;
    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level worldIn, @Nullable Player player, ItemStack stack, BlockState state) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        if (te instanceof FabricationBenchBE) {
        	FabricationBenchBE station = (FabricationBenchBE) te;
            station.energyStorage.receiveEnergy(stack.getOrCreateTag().getInt("energy"), false);

        }

        return super.updateCustomBlockEntityTag(pos, worldIn, player, stack, state);
    }
}
