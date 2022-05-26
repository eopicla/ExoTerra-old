package com.faojen.exoterra.blocks.simple.machinebody;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.api.generic.ScreenUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
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


        // Power Display
        if (this.container.getFluidStored() > 0) {
            ScreenUtils.drawVerticalMeter(this.container.getFluidCapacity(), this.container.getFluidStored(), this, stack, 49, 27, 28, 176, 48, 23, leftPos, topPos);
        }

        if (this.container.getEnergyStored() > 0) {
            ScreenUtils.drawVerticalMeter(this.container.getEnergyCapacity(), this.container.getEnergyStored(), this, stack, 49, 126, 28, 200, 48, 23, leftPos, topPos);
        }

    }

    @SuppressWarnings("resource")
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

        // Gui title
        ScreenUtils.drawTranslateWithShadow(stack, font, "screen.exoterra.machine_body_title", 62, 4, 1, Color.DARK_GRAY.getRGB());

    }
}
