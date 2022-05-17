package com.faojen.exoterra.blocks.machine.crystalcatalyst;

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

public class CrystalCatalystScreen extends AbstractContainerScreen<CrystalCatalystContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/crystal_catalyst.png");

	private final CrystalCatalystContainer container;

	public CrystalCatalystScreen(CrystalCatalystContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);
		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip

		// Fluid Tooltip
		ScreenUtils.renderToolTip(stack, this, mouseX, mouseY, 142, 23, 28, 49, "screen.exoterra.fluid", this.container.getFluidStored(), this.container.getMaxCapacity(), leftPos, topPos);
		
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

		// burn status thing
		if (this.container.getMaxBurn() > 0) {
			int burnStatusMaxHeight = 16;
			int remaining = (this.container.getRemaining() * burnStatusMaxHeight) / this.container.getMaxBurn();
			this.blit(stack,
					leftPos + 80,
					topPos + 35 + 16 - remaining,
					187,
					67 - remaining,
					16,
					remaining + 1);
		}

		// Stellar Display
		ScreenUtils.drawVerticalMeter(this.container.getMaxCapacity(), this.container.getFluidStored(), this, stack, 49, 142, 29, 176, 129, 23, leftPos, topPos);
			
		}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

		// Gui Title
		ScreenUtils.drawTranslateWithShadow(stack, font, "screen.exoterra.crystal_catalyst_guititle", 62, 4, 1, Color.DARK_GRAY.getRGB());

		// Crystallizing Percentage
		ScreenUtils.drawComponent(stack, font, "screen.exoterra.percentage", this.container.getCrystallizing()*100/2400, 102, 65, 1, Color.WHITE.getRGB());

	}
}
