package com.faojen.exoterra.blocks.compowerbank;

import com.faojen.exoterra.utils.MagicHelpers;
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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Mth;

public class CommonPowerBankItem extends BlockItem {

    public CommonPowerBankItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    
    @Override
	public int getBarWidth(ItemStack stack) {
		int energy = stack.getOrCreateTag().getInt("energy");
		int maxEnergy = CommonPowerBankBE.ENERGY_CAPACITY_PUB;
		var stored = maxEnergy - energy;

		return Math.round(13.0F - stored * 13.0F / maxEnergy);
	}

	@Override
	public int getBarColor(ItemStack stack) {
		int energy = stack.getOrCreateTag().getInt("energy");
		int maxEnergy = CommonPowerBankBE.ENERGY_CAPACITY_PUB;

		float f = Math.max(0.0F, (float) energy / (float) maxEnergy);

		return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return true;
	}
    
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);

        int power = stack.getOrCreateTag().getInt("energy");
        if (power == 0)
            return;
 
        tooltip.add(new TranslatableComponent("screen.exoterra.energy", MagicHelpers.withSuffix(power), MagicHelpers.withSuffix(1000000)));
    }

    @Override
    protected boolean updateCustomBlockEntityTag(BlockPos pos, Level worldIn, @Nullable Player player, ItemStack stack, BlockState state) {
        BlockEntity te = worldIn.getBlockEntity(pos);
        if (te instanceof CommonPowerBankBE) {
        	CommonPowerBankBE station = (CommonPowerBankBE) te;
        	
            station.energyStorage.receiveEnergy(stack.getOrCreateTag().getInt("energy"), false);
        }

        return super.updateCustomBlockEntityTag(pos, worldIn, player, stack, state);
    }
}
