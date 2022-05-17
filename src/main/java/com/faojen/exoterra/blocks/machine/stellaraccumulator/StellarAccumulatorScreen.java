package com.faojen.exoterra.blocks.machine.stellaraccumulator;

import java.awt.Color;
import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class StellarAccumulatorScreen extends AbstractContainerScreen<StellarAccumulatorContainer> {
    private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
            "textures/gui/stellar_accumulator.png");

    private final StellarAccumulatorContainer container;

    public StellarAccumulatorScreen(StellarAccumulatorContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        this.container = container;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);

        this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
        if (mouseX > (leftPos + 9) && mouseX < (leftPos + 9) + 14 && mouseY > (topPos + 26)
                && mouseY < (topPos + 26) + 52) {
            this.renderTooltip(stack, new TranslatableComponent(
                    "screen.exoterra.energy", MagicHelpers.withSuffix(this.container.getEnergy()),
                    MagicHelpers.withSuffix(this.container.getMaxPower())), mouseX, mouseY);
        }

        this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
        if (mouseX > (leftPos + 30) && mouseX < (leftPos + 30) + 38 && mouseY > (topPos + 26)
                && mouseY < (topPos + 26) + 52) {
            this.renderTooltip(stack, new TranslatableComponent(
                    "screen.exoterra.sludge", MagicHelpers.withSuffix(this.container.getFluidStored()),
                    MagicHelpers.withSuffix(this.container.getMaxFluid())), mouseX, mouseY);
        }


    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, background);
        this.blit(stack, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        // Power Display

        int maxEnergy = this.container.getMaxPower(), height = 52;
        if (maxEnergy > 0) {
            int remainingEnergy = (this.container.getEnergy() * height) / maxEnergy;

            // power
            this.blit(stack,
                    leftPos + 9,                    // Destination top-left corner X
                    topPos + 26 + 52 - remainingEnergy,                // Destination top-left corner Y
                    177,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
                    71 - remainingEnergy,                                // Source top-left corner Y
                    14,                // Source Image Width - Iterates remaining in order scale width
                    remainingEnergy + 1);                                // Source Image height
        }
        // sludge
        int maxSludge = this.container.getMaxFluid();
        if(maxSludge > 0){
            int remainingSludge = (this.container.getFluidStored() * height) / maxSludge;
            // sludge
            this.blit(stack,
                    leftPos + 30, 				    // Destination top-left corner X
                    topPos + 26 + 52 - remainingSludge, 			    // Destination top-left corner Y
                    177,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
                    124 - remainingSludge, 							    // Source top-left corner Y
                    38, 			    // Source Image Width - Iterates remaining in order scale width
                    remainingSludge + 1);							    // Source Image height

        }

        int maxFilter = 512, width = 79;
        if (this.container.getFilterProgress() > 0) {
            int remainingFilter = (this.container.getFilterProgress() * width) / maxFilter;

            // filter progress
            this.blit(stack,
                    leftPos + 72,                    // Destination top-left corner X
                    topPos + 24,                // Destination top-left corner Y
                    176,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
                    1,                                // Source top-left corner Y
                    remainingFilter + 1,                // Source Image Width - Iterates remaining in order scale width
                    18);                                // Source Image height
        }

    }

    @SuppressWarnings("resource")
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        float scaleAmount;

        Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.stellar_accumulator_guititle"),
                62, 4,Color.DARK_GRAY.getRGB());
        /**
         *Status stuff
         */
        scaleAmount = 0.8f;
        int round = Math.round(scaleAmount);
        stack.scale(scaleAmount,scaleAmount,scaleAmount);
        // Percent progress
        float num = 74 * 0.25f;
        float testX = 74 + num;
        float newX = Math.round(testX);

        // percentage progress of filtering
        this.font.draw(stack,
                new TranslatableComponent("screen.exoterra.accumulator_progress",
                        MagicHelpers.withSuffix(this.container.getFilterProgress()*100/512)), testX, scaleY(46), Color.WHITE.getRGB());

        // if there is a filter installed
        if (this.container.getIsFilterInstalled() == true){
            Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.accumulator_filterin"), newX, scaleY(54), Color.WHITE.getRGB());
        // if there is no filter installed
        }else if (this.container.getIsFilterInstalled() == false){
            Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.accumulator_filterout"), newX, scaleY(54), Color.WHITE.getRGB());
        }

    }

    private float scaleY(float in){
        float num = in * 0.25f;
        float testX = in + num;
        float newX = Math.round(testX);

        return newX;
    }
}

