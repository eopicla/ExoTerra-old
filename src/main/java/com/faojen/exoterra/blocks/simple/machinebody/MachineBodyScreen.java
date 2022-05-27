package com.faojen.exoterra.blocks.simple.machinebody;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.api.generic.ScreenUtils;
import com.faojen.exoterra.items.basic.SentientCore;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.awt.*;

public class MachineBodyScreen extends AbstractContainerScreen<MachineBodyContainer> {
    private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
            "textures/gui/machine_body.png");

    private final MachineBodyContainer container;

    public MachineBodyScreen(MachineBodyContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        this.container = container;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip

        // Stellar tooltip
        ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 27, 23, 28, 49, "screen.exoterra.fluid", this.container.getFluidStored(), this.container.getFluidCapacity(), leftPos, topPos);

        // Energy tooltip
        ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 126, 23, 28, 49, "screen.exoterra.energy", this.container.getEnergyStored(), this.container.getEnergyCapacity(), leftPos, topPos);
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


        // Stellar display
        if (this.container.getFluidStored() > 0) {
            ScreenUtils.drawVerticalMeter(this.container.getFluidCapacity(), this.container.getFluidStored(), this, stack, 49, 27, 28, 176, 48, 23, leftPos, topPos);
        }

        // Energy display
        if (this.container.getEnergyStored() > 0) {
            ScreenUtils.drawVerticalMeter(this.container.getEnergyCapacity(), this.container.getEnergyStored(), this, stack, 49, 126, 28, 200, 48, 23, leftPos, topPos);
        }

        // Intelligence display
        if (this.container.getCoreIntelligence() > 0) {
            ScreenUtils.drawVerticalMeter(100, this.container.getCoreIntelligence(), this, stack, 20, 74, 42, 177, 69, 28, leftPos, topPos);
        }

    }

    @SuppressWarnings("resource")
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

        // Gui title
        ScreenUtils.drawTranslateWithShadow(stack, font, "screen.exoterra.machine_body_guititle", 62, 4, 1, Color.DARK_GRAY.getRGB());

        ScreenUtils.drawString(stack, font, "Stabilizing: ", 65, 65, 0.5f, Color.WHITE.getRGB());

        if(this.container.getIsStabilizing()){
            ScreenUtils.drawString(stack, font, "YES", 92, 65, 0.5f, Color.GREEN.getRGB());
        }

        if(this.container.getIsStabilizing() == false){
            ScreenUtils.drawString(stack, font, "NO", 92, 65, 0.5f, Color.RED.getRGB());
        }

        if(this.container.getIsCoreInSlot() == false) {
            ScreenUtils.drawString(stack,font, "N/A", 65, 71, 1, Color.WHITE.getRGB());
            ScreenUtils.drawString(stack,font, "N/A", 94, 71, 1, Color.WHITE.getRGB());
        }

        // High stability
        if(this.container.getCoreStability() >= SentientCore.getMaxStability() * 0.75f && this.container.getIsCoreInSlot()) {
            ScreenUtils.drawComponent(stack, font, "screen.exoterra.stability_high", MagicHelpers.toPercent(this.container.getCoreStability(), SentientCore.getMaxStability()), 65, 71, 1, Color.GREEN.getRGB());
        }

        // Mid stability
        if(this.container.getCoreStability() < SentientCore.getMaxStability() * 0.75f && this.container.getCoreStability() >= SentientCore.getMaxStability() * 0.25f && this.container.getIsCoreInSlot()) {
            ScreenUtils.drawComponent(stack, font, "screen.exoterra.stability_mid", MagicHelpers.toPercent(this.container.getCoreStability(), SentientCore.getMaxStability()), 65, 71, 1, Color.YELLOW.getRGB());
        }

        // Low stability
        if(this.container.getCoreStability() < SentientCore.getMaxStability() * 0.25f && this.container.getIsCoreInSlot()) {
            ScreenUtils.drawComponent(stack, font, "screen.exoterra.stability_low", MagicHelpers.toPercent(this.container.getCoreStability(), SentientCore.getMaxStability()), 65, 71, 1, Color.RED.getRGB());
        }

        if(this.container.getIsCoreInSlot()) {
            ScreenUtils.drawComponent(stack, font, "screen.exoterra.percentage", this.container.getCoreIntelligence(), 88, 71, 1, Color.BLUE.getRGB());
        }
    }
}
