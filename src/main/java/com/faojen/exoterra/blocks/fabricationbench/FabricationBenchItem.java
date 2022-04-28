package com.faojen.exoterra.blocks.fabricationbench;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
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

public class FabricationBenchItem extends BlockItem {

    public FabricationBenchItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        int power = stack.getOrCreateTag().getInt("energy");
        if (power == 0)
            return;

       // tooltip.add(new TranslatableComponent("screen.exoterra.energy", MagicHelpers.withSuffix(power), MagicHelpers.withSuffix(Config.GENERAL.chargerMaxPower.get())).withStyle(ChatFormatting.GREEN));
      //  tooltip.add(new TranslatableComponent("screen.exoterra.stellar", MagicHelpers.withSuffix(fluid), MagicHelpers.withSuffix(StellarConverterBE.FLUID_CAP_PUB)).withStyle(ChatFormatting.BLUE));
    }

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
