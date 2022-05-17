package com.faojen.exoterra.api.generic;

import com.faojen.exoterra.blocks.simple.compowerbank.CommonPowerBankBE;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TranslatableComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ScreenUtils {

    /**
     * Simply draws a string, and allows you to change the scale.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param string The String to draw.
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawString(PoseStack matrixStack, Font fontRenderer, String string, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack, string, 0, 0, color);
        matrixStack.popPose();
    }

    /**
     * This will draw a string from a TranslatableComponent when there a suffix.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param key The TranslatableComponent's lang location ( screen.project.declaration )
     * @param suffix The suffix to stick into the TC's %s
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawComponent(PoseStack matrixStack, Font fontRenderer, String key, int suffix, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack,
                new TranslatableComponent(key,
                MagicHelpers.withSuffix(suffix)),
        0, 0, color);
        matrixStack.popPose();
    }

    /**
     * This will draw a string from a TranslatableComponent when there is no suffix.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param key The TranslatableComponent's lang location ( screen.project.declaration )
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawTranslate(PoseStack matrixStack, Font fontRenderer, String key, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack, I18n.get(key), 0, 0, color);
        matrixStack.popPose();
    }

    /**
     * Simply draws a string with a shadow, and allows you to change the scale.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param string The String to draw.
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawStringWithShadow(PoseStack matrixStack, Font fontRenderer, String string, int x, int y, float scale, int color) {
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.drawShadow(matrixStack, string, 0, 0, color);
        matrixStack.popPose();
    }

    /**
     * This will draw a string (with a shadow) from a TranslatableComponent when there a suffix.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param key The TranslatableComponent's lang location ( screen.project.declaration )
     * @param suffix The suffix to stick into the TC's %s
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawComponentWithShadow(PoseStack matrixStack, Font fontRenderer, String key, int suffix, int x, int y, float scale, int color) {
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.drawShadow(matrixStack,
                new TranslatableComponent(key,
                        MagicHelpers.withSuffix(suffix)),
                0, 0, color);
        matrixStack.popPose();
    }

    /**
     * This will draw a string (with a shadow) from a TranslatableComponent when there is no suffix.
     * @param matrixStack The PoseStack of the screen object.
     * @param fontRenderer The FontRenderer of the screen object.
     * @param key The TranslatableComponent's lang location ( screen.project.declaration )
     * @param x The String's target x position (top left corner)
     * @param y The String's target y position (top left corner)
     * @param scale The scale of the text. 1 is normal font scale.
     * @param color The color integer ( Color.COLOR.getRGB() )
     */
    public static void drawTranslateWithShadow(PoseStack matrixStack, Font fontRenderer, String key, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack, I18n.get(key), 0, 0, color);
        matrixStack.popPose();
    }

    /** This method draws a simple blit on your screen.
     *
     * @param screen The target screen. ( "this" is fine )
     * @param stack The target PoseStack
     * @param targetX The desired X location of the meter.
     * @param targetY The desired Y location of the meter.
     * @param sourceX The X location on your texture of the overlay you intend to use.
     * @param sourceY The Y location on your texture of the overlay you intend to use.
     * @param sourceWidth The width of the overlay.
     * @param sourceHeight The height of the overlay.
     */
    public static void drawSimpleBlit(AbstractContainerScreen screen, PoseStack stack, int targetX, int targetY, int sourceX, int sourceY, int sourceWidth, int sourceHeight, int leftPos, int topPos) {
        screen.blit(stack, leftPos + targetX, topPos + targetY, sourceX, sourceY, sourceWidth, sourceHeight);
    }

    /**This method will allow you to create a vertical meter on your gui ( e.g. power, fluid, any other integer tracked value )
     * ---------
     * This method should be used: if(currentVal > 0){drawVerticalMeter} to avoid issues.
     * ---------
     * @param maxCap The maximum capacity of whatever type of storage the meter is representing.
     * @param currentVal The current stored value of whatever the meter is representing.
     * @param screen The target screen. ( "this" is fine )
     * @param stack The target PoseStack
     * @param meterHeight The height of meter on your texture.
     * @param targetX The desired X location of the meter.
     * @param targetY The desired Y location of the meter.
     * @param sourceX The X location on your texture of the overlay you intend to use.
     * @param sourceBottomY The Y coordinate of the bottom pixel of your overlay.
     * @param sourceWidth The width of the overlay.
     * @param topPos Pass your screen's topPos.
     * @param leftPos Pass your screen's leftPos.
     */
    public static void drawVerticalMeter(int maxCap, int currentVal, AbstractContainerScreen screen, PoseStack stack, int meterHeight, int targetX, int targetY, int sourceX, int sourceBottomY, int sourceWidth, int leftPos, int topPos){
        meterHeight = meterHeight - 1;
        int remaining = (currentVal * meterHeight) / maxCap;
        screen.blit(stack,
                leftPos + targetX,
                topPos + targetY + meterHeight - remaining,
                sourceX,
                sourceBottomY - remaining,
                sourceWidth,
                remaining + 1);

    }

    /**This method will allow you to create a horizontal meter on your gui ( e.g. power, fluid, any other integer tracked value )
     * ---------
     * This method should be used: if(currentVal > 0){drawHorizontalMeter} to avoid issues.
     * ---------
     * @param maxCap The maximum capacity of whatever type of storage the meter is representing.
     * @param currentVal The current stored value of whatever the meter is representing.
     * @param screen The target screen. ( "this" is fine )
     * @param stack The target PoseStack
     * @param meterWidth The width of meter on your texture.
     * @param targetX The desired X location of the meter.
     * @param targetY The desired Y location of the meter.
     * @param sourceX The X location of the meter overlay on your texture.
     * @param sourceY The Y location of the meter overlay on your texture.
     * @param sourceHeight The height of the overlay.
     * @param topPos Pass your screen's topPos.
     * @param leftPos Pass your screen's leftPos.
     */
    public static void drawHorizontalMeter(int maxCap, int currentVal, AbstractContainerScreen screen, PoseStack stack, int meterWidth, int targetX, int targetY, int sourceX, int sourceY, int sourceHeight, int leftPos, int topPos){
        int remaining = (currentVal * meterWidth) / maxCap;
        screen.blit(stack,
                leftPos + targetX,
                topPos + targetY,
                sourceX,
                sourceY,
                remaining + 1,
                sourceHeight);
    }

    /**This method will allow you to create a horizontal meter on your gui. It differs from "drawHorizontalMeter" in that it will fill the meter from right to left.
     * ---------
     * This method should be used: if(currentVal > 0){drawRevHorizontalMeter} to avoid issues.
     * ---------
     * @param maxCap The maximum capacity of whatever type of storage the meter is representing.
     * @param currentVal The current stored value of whatever the meter is representing.
     * @param screen The target screen. ( "this" is fine )
     * @param stack The target PoseStack
     * @param meterWidth The width of meter on your texture.
     * @param targetX The desired X location of the meter.
     * @param targetY The desired Y location of the meter.
     * @param sourceX The X location of the meter overlay on your texture.
     * @param sourceY The Y location of the meter overlay on your texture.
     * @param sourceHeight The height of the overlay.
     * @param topPos Pass your screen's topPos.
     * @param leftPos Pass your screen's leftPos.
     */
    public static void drawRevHorizontalMeter(int maxCap, int currentVal, AbstractContainerScreen screen, PoseStack stack, int meterWidth, int targetX, int targetY, int sourceX, int sourceY, int sourceHeight, int leftPos, int topPos){
        int remaining = (currentVal * meterWidth) / maxCap;
        screen.blit(stack,
                leftPos + targetX + meterWidth - remaining,
                topPos + targetY,
                sourceX + meterWidth - remaining,
                sourceY,
                remaining + 1,
                sourceHeight);
    }

    /** Renders a tooltip with 2 suffixes.
     *
     * @param stack The target PoseStack.
     * @param screen The target screen. ( "this" is fine )
     * @param mouseX Mouse's X position. ( passing "mouseX" is fine)
     * @param mouseY Mouse's Y position. ( passing "mouseY" is fine)
     * @param minX Minimum X coordinate to trigger the tooltip.
     * @param maxX Maximum X coordinate to trigger the tooltip under.
     * @param minY Minimum Y coordinate to trigger the tooltip.
     * @param maxY Maximum Y coordinate to trigger the tooltip under.
     * @param key Key for the TranslatableComponent.
     * @param suffix1 Suffix 1 for the key provided.
     * @param suffix2 Suffix 2 for the key provided.
     * @param leftPos leftPos of the screen. ( passing leftPos is fine )
     * @param topPos topPos of the screen. ( passing topPos is fine )
     */
    public static void renderToolTip(PoseStack stack, AbstractContainerScreen screen, int mouseX, int mouseY, int minX, int maxX, int minY, int maxY, String key, int suffix1, int suffix2, int leftPos, int topPos){
        if (mouseX > (leftPos + minX) && mouseX < (leftPos + minX) + maxX && mouseY > (topPos + minY)
                && mouseY < (topPos + minY) + maxY) {
            screen.renderTooltip(stack, new TranslatableComponent(
                    key, MagicHelpers.withSuffix(suffix1),
                    MagicHelpers.withSuffix(suffix2)), mouseX, mouseY);
        }
    }

}
