package com.faojen.exoterra.mixin;

import com.faojen.exoterra.setup.Registration;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SentCoreContainerMix {
    @Inject(method = "Lnet/minecraft/world/inventory/Slot;mayPlace(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void exoterra_canPlaceItem(ItemStack pStack, CallbackInfoReturnable<Boolean> callback) {
        if(pStack.is(Registration.SENTIENT_CORE.get())){
            callback.setReturnValue(false);
        }

    }
}
