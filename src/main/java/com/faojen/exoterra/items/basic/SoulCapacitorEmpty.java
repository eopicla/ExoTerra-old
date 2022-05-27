package com.faojen.exoterra.items.basic;

import com.faojen.exoterra.setup.ModSetup;
import com.faojen.exoterra.setup.Registration;
import com.faojen.exoterra.utils.MagicHelpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class SoulCapacitorEmpty extends Item {
    public SoulCapacitorEmpty() {
        super(new Item.Properties()
                .tab(ModSetup.ITEM_GROUP)
                .rarity(Rarity.UNCOMMON)
                .stacksTo(1));

    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        //return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
        ItemStack fullcore = new ItemStack(Registration.SOUL_CAPACITOR_FULL.get(), 1);
        CompoundTag level = new CompoundTag();
        if(pInteractionTarget.isBaby() && pInteractionTarget.getHealth() >= 1 && !pPlayer.getLevel().isClientSide()){
            MobEffect wither = MobEffects.WITHER;
            pInteractionTarget.setHealth(pInteractionTarget.getHealth() / 10);
            pInteractionTarget.addEffect(new MobEffectInstance(wither, 120, 10));

            level.putInt("level", MagicHelpers.randomInt(1, 4));

            pStack.setCount(0);
            fullcore.setTag(level);

            pPlayer.getInventory().add(fullcore);

            return InteractionResult.PASS;
        } else

            return InteractionResult.FAIL;
    }
}
