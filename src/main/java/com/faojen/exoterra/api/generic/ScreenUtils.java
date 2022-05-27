package com.faojen.exoterra.api.generic;

import com.faojen.exoterra.blocks.simple.compowerbank.CommonPowerBankBE;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.TranslatableComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Arrays;

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

    /**
     * Draw the given text with the given width and desired scale.
     * @param matrixStack The matrix stack
     * @param fontRenderer The font renderer
     * @param string The string to draw
     * @param x The center X
     * @param y The center Y
     * @param width The scaled width
     * @param scale The desired scale
     * @param color The color to draw
     */
    public static void drawScaledCenteredString(PoseStack matrixStack, Font fontRenderer, String string, int x, int y, int width, float scale, int color) {
        matrixStack.pushPose();
        matrixStack.scale(scale, scale, 1.0f);
        int titleLength = fontRenderer.width(string);
        int titleHeight = fontRenderer.lineHeight;
        fontRenderer.draw(matrixStack, string, Math.round((x + width / 2) / scale - titleLength / 2), Math.round(y / scale - titleHeight / 2), color);
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
     * @param maxX Width of the trigger field.
     * @param minY Minimum Y coordinate to trigger the tooltip.
     * @param maxY Height of the trigger field.
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

    /**
     * Used very sparingly. not going to provide documentation :( check uses.
     */
    public static void renderToolTip3x2(PoseStack stack, AbstractContainerScreen screen, int mouseX, int mouseY, int minX, int maxX, int minY, int maxY,int conditionalInt, int lessThan, int tickToSec,String key, String key2, String key3, int suffix1, int suffix2, int leftPos, int topPos){
        if (mouseX > (leftPos + minX) && mouseX < (leftPos + minX) + maxX && mouseY > (topPos + minY)
                && mouseY < (topPos + minY) + maxY) {
            screen.renderTooltip(stack,
                    Language.getInstance()
                            .getVisualOrder(Arrays.asList(new TranslatableComponent(
                                            key, MagicHelpers.withSuffix(suffix1),
                                            MagicHelpers.withSuffix(suffix2)),
                                    conditionalInt <= lessThan
                                            ? new TranslatableComponent(key2)
                                            : new TranslatableComponent(key3,
                                            MagicHelpers.ticksInSeconds(tickToSec)))),
                    mouseX, mouseY);
        }
    }

    /**
     * This will move a blit from its initialization point to the left. Same params as horizontal peter.
     */
    public static void drawMovingBlitLeft(int maxCap, int currentVal, AbstractContainerScreen screen, PoseStack stack, int meterWidth, int targetX, int targetY, int sourceX, int sourceY, int sourceHeight, int sourceWidth, int leftPos, int topPos){
        int remaining = (currentVal * meterWidth) / maxCap;
        screen.blit(stack,
                leftPos + targetX - meterWidth + remaining,
                topPos + targetY,
                sourceX,
                sourceY,
                sourceWidth,
                sourceHeight);
    }

    /**
     * This will move a blit from its initialization point to the right. Same params as horizontal meter.
     */
    public static void drawMovingBlitRight(int maxCap, int currentVal, AbstractContainerScreen screen, PoseStack stack, int meterWidth, int targetX, int targetY, int sourceX, int sourceY, int sourceWidth, int sourceHeight, int leftPos, int topPos){
        int remaining = (currentVal * meterWidth) / maxCap;
        screen.blit(stack,
                leftPos + targetX + meterWidth - remaining,
                topPos + targetY,
                sourceX,
                sourceY,
                sourceWidth,
                sourceHeight);
    }

}
