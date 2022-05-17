package com.faojen.exoterra.blocks.simple.infpowerbank;

import java.awt.Color;
import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.api.generic.ScreenUtils;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class InferiorPowerBankScreen extends AbstractContainerScreen<InferiorPowerBankContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/inferior_power_bank.png");

	private final InferiorPowerBankContainer container;

	public InferiorPowerBankScreen(InferiorPowerBankContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip

		ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 9, 158, 26, 53, "screen.exoterra.energy", this.container.getEnergy(), this.container.getMaxPower(), leftPos, topPos);

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
			ScreenUtils.drawHorizontalMeter(this.container.getMaxPower(), this.container.getEnergy(), this, stack, 158, 9, 26, 0, 166, 53, topPos, leftPos);
		}

		// Draw power meter corners
		ScreenUtils.drawSimpleBlit(this, stack, 11, 28, 2, 221,154, 9, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 11, 68, 2, 245,154, 9, leftPos, topPos);
			
	}


	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

		// Gui title
		ScreenUtils.drawTranslateWithShadow(stack, font, "screen.exoterra.inferior_power_bank_guititle", 62, 4, 1, Color.DARK_GRAY.getRGB());

	}
}
