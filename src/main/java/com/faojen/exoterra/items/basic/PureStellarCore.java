package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class PureStellarCore extends Item {

    public PureStellarCore() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .rarity(Rarity.RARE)
                .stacksTo(1)
                .craftRemainder(Registration.INF_STELLAR_CORE.get()));

    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        //return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
        ItemStack sentient = new ItemStack(Registration.SENTIENT_CORE.get(), 1);
        CompoundTag level = new CompoundTag();
        if(pInteractionTarget.isBaby()){

            pInteractionTarget.kill();
            level.putInt("level",1);
            pStack.setCount(0);
            sentient.setTag(level);

            pPlayer.getInventory().add(sentient);

            return InteractionResult.PASS;
        } else

        return InteractionResult.FAIL;
    }

}
