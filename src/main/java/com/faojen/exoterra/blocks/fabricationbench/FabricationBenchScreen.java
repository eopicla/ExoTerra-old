package com.faojen.exoterra.blocks.fabricationbench;

import java.awt.Color;
import java.util.Arrays;

import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FabricationBenchScreen extends AbstractContainerScreen<FabricationBenchContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/fabrication_bench.png");

	private final FabricationBenchContainer container;

	public FabricationBenchScreen(FabricationBenchContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);

		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
		if (mouseX > (leftPos + 7) && mouseX < (leftPos + 7) + 6 && mouseY > (topPos + 24)
				&& mouseY < (topPos + 24) + 52) {
			this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.energy", MagicHelpers.withSuffix(this.container.getEnergy()),
									MagicHelpers.withSuffix(this.container.getMaxPower())), mouseX, mouseY);
		}
							

		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
		if (mouseX > (leftPos + 16) && mouseX < (leftPos + 16) + 6 && mouseY > (topPos + 24)
				&& mouseY < (topPos + 24) + 52)
			this.renderTooltip(stack, new TranslatableComponent(
					"screen.exoterra.fluid", MagicHelpers.withSuffix(this.container.getFluidStored()),
					MagicHelpers.withSuffix(this.container.getMaxCapacity())), mouseX, mouseY);
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
		
	// POWER
		int maxEnergy = this.container.getMaxPower(), energyheight = 52;
		if (maxEnergy > 0) {
			int remaining = (this.container.getEnergy() * energyheight) / maxEnergy;

			this.blit(stack, 
					leftPos + 9, 				    // Destination top-left corner X
					topPos + 26 + 52 - remaining, 			    // Destination top-left corner Y
					176,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
					52 - remaining, 							    // Source top-left corner Y
					3, 			    // Source Image Width - Iterates remaining in order scale width
					remaining + 1);							    // Source Image height

		}
	// STELLAR
			int maxFluid = this.container.getMaxCapacity(), stellarheight = 52;
			if (maxFluid > 0) {
				int remaining = (this.container.getFluidStored() * stellarheight) / maxFluid;

				this.blit(stack, 
						leftPos + 18, 			
						topPos + 26 + 52 - remaining, 			  
						179,  
						52 - remaining, 								 
						3, 			   
						remaining + 1);					
				
			}
	}
	
	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		
		Minecraft.getInstance().font.draw(stack, I18n.get("block.exoterra.fabrication_bench"), 
				62, 4,Color.DARK_GRAY.getRGB());

	}
}
