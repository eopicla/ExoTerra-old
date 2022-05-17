package com.faojen.exoterra.blocks.machine.stellaraccumulator;

import java.awt.Color;
import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.api.generic.ScreenUtils;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
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

        // Energy tooltip
        ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 9, 14, 26, 52, "screen.exoterra.energy", this.container.getEnergy(), this.container.getMaxPower(), leftPos, topPos);

        // Sludge tooltip
        ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 30, 38, 26, 52, "screen.exoterra.sludge", this.container.getFluidStored(), this.container.getMaxFluid(), leftPos, topPos);

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
        if (this.container.getEnergy() > 0) {
            ScreenUtils.drawVerticalMeter(this.container.getMaxPower(),this.container.getEnergy(),this, stack,52, 9, 26, 177, 71, 14, leftPos, topPos);
        }
        // Sludge Display
        if(this.container.getFluidStored() > 0){
            ScreenUtils.drawVerticalMeter(this.container.getMaxFluid(), this.container.getFluidStored(), this, stack, 52, 30, 25, 177, 124, 38, leftPos, topPos);
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

        Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.stellar_accumulator_guititle"),
                62, 4,Color.DARK_GRAY.getRGB());
        /**
         *Status stuff
         */
        ScreenUtils.drawComponent(stack, font, "screen.exoterra.accumulator_progress",this.container.getFilterProgress()*100/512,74,46,0.8f,Color.WHITE.getRGB());
        // if there is a filter installed
        if (this.container.getIsFilterInstalled() == true){
            ScreenUtils.drawTranslate(stack, font, "screen.exoterra.accumulator_filterin", 74, 52, 0.8f, Color.WHITE.getRGB());
        // if there is no filter installed
        }else if (this.container.getIsFilterInstalled() == false){
            ScreenUtils.drawTranslate(stack, font, "screen.exoterra.accumulator_filterout", 74, 58, 0.8f, Color.WHITE.getRGB());
        }

    }

}

