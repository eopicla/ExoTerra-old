package com.faojen.exoterra.api.generic;

import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TranslatableComponent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ScreenUtils {

    public static void drawString(PoseStack matrixStack, Font fontRenderer, String string, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack, string, 0, 0, color);
        matrixStack.popPose();
    }

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

    public static void drawTranslate(PoseStack matrixStack, Font fontRenderer, String key, int x, int y, float scale, int color){
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.draw(matrixStack, I18n.get(key), 0, 0, color);
        matrixStack.popPose();
    }

    public static void drawStringWithShadow(PoseStack matrixStack, Font fontRenderer, String string, int x, int y, float scale, int color) {
        matrixStack.pushPose();
        matrixStack.translate(x, y, 0);
        matrixStack.scale(scale, scale, 1.0f);
        fontRenderer.drawShadow(matrixStack, string, 0, 0, color);
        matrixStack.popPose();
    }

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
     * this.font.draw(stack,
     * new TranslatableComponent("screen.exoterra.accumulator_progress",
     * MagicHelpers.withSuffix(this.container.getFilterProgress()*100/512)), testX, scaleY(46), Color.WHITE.getRGB());
     *
     *
     */

}
