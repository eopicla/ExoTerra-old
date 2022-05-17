package com.faojen.exoterra.blocks.machine.purificationbestower;

import java.awt.Color;
import java.util.Arrays;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.api.generic.ScreenUtils;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PurificationBestowerScreen extends AbstractContainerScreen<PurificationBestowerContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/stellar_converter.png");

	private final PurificationBestowerContainer container;

	public PurificationBestowerScreen(PurificationBestowerContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip

		ScreenUtils.renderToolTip3x2(stack, this, mouseX, mouseY, 7, 70, 62, 17,
				this.container.getRemaining(), 0, this.container.getRemaining(),
				"screen.exoterra.energy", "screen.exoterra.no_fuel", "screen.exoterra.burn_time",
				this.container.getEnergy(), this.container.getMaxPower(),
				leftPos, topPos);

		ScreenUtils.renderToolTip3x2(stack, this, mouseX, mouseY, 97, 70, 62, 17,
				this.container.getEnergy(), 19999, this.container.getPurifyRemaining(),
				"screen.exoterra.fluid", "screen.exoterra.no_energy", "screen.exoterra.purify_time",
				this.container.getFluidStored(), this.container.getMaxCapacity(),
				leftPos, topPos);
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

		// Burn Status
		if (this.container.getMaxBurn() > 0) {
			int burnStatusMaxHeight = 16;
			int remaining = (this.container.getRemaining() * burnStatusMaxHeight) / this.container.getMaxBurn();
			this.blit(stack, leftPos + 28, topPos + 42 + 16 - remaining, 177, 48 - remaining, 5, remaining + 1);
		}

		// Stellar Drop
		if (this.container.getPurifyMaxBurn() > 0) {

			ScreenUtils.drawMovingBlitLeft(this.container.getPurifyMaxBurn(), this.container.getPurifyRemaining(), this, stack, 58, 144, 50, 188, 33, 4, 2, leftPos, topPos);

		}
		// Energy Drop
		if (this.container.getPurifyMaxBurn() > 0) {

			ScreenUtils.drawMovingBlitRight(this.container.getPurifyMaxBurn(), this.container.getPurifyRemaining(), this, stack, 48, 38, 50, 184, 33, 4, 2, leftPos, topPos);

		}

		// Power Display
		if (this.container.getEnergy() > 0) {

			ScreenUtils.drawRevHorizontalMeter(this.container.getMaxPower(), this.container.getEnergy(), this, stack, 70, 8, 63, 176, 16, 16, leftPos, topPos);
			// Meter Decoration
			ScreenUtils.drawSimpleBlit(this, stack, 9, 64, 184, 36, 68, 14, leftPos, topPos);

		}

		// Stellar display
		if (this.container.getFluidStored() > 0) {

			ScreenUtils.drawHorizontalMeter(this.container.getMaxCapacity(), this.container.getFluidStored(), this, stack, 70, 98, 63, 176, 0, 15, leftPos, topPos);
			// meter overlay
			ScreenUtils.drawSimpleBlit(this, stack, 99, 64, 184, 36, 68, 14, leftPos, topPos);
			
		}

		buildDecoBlits(stack, partialTicks);
	}

	private void buildDecoBlits(PoseStack stack, float partialTicks){
		// Tank Decoration
		ScreenUtils.drawSimpleBlit(this, stack, 99, 64, 184, 36, 68, 14, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 9, 64, 184, 36, 68, 14, leftPos, topPos);
		// Chamber Blits
		ScreenUtils.drawSimpleBlit(this, stack, 86, 50, 86, 50, 4, 2, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 74, 50, 74, 50, 4, 2, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 74, 50, 74, 50, 4, 2, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 145, 48, 145, 48, 5, 6, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 86, 50, 86, 50, 4, 2, leftPos, topPos);
		ScreenUtils.drawSimpleBlit(this, stack, 98, 50, 98, 50, 3, 2, leftPos, topPos);

	}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		// Gui Title
		ScreenUtils.drawTranslateWithShadow(stack, font, "screen.exoterra.stellar_converter_guititle", 62, 4, 1, Color.DARK_GRAY.getRGB());

	}
}
