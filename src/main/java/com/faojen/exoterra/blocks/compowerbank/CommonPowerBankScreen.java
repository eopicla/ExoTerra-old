package com.faojen.exoterra.blocks.compowerbank;

import java.awt.Color;
import com.faojen.exoterra.ExoTerra;
import com.faojen.exoterra.utils.MagicHelpers;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import static com.faojen.exoterra.blocks.compowerbank.CommonPowerBankBE.COM_BANK_CAPACITY_PUB;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CommonPowerBankScreen extends AbstractContainerScreen<CommonPowerBankContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/common_power_bank.png");

	private final CommonPowerBankContainer container;

	public CommonPowerBankScreen(CommonPowerBankContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);

		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
		if (mouseX > (leftPos + 9) && mouseX < (leftPos + 9) + 158 && mouseY > (topPos + 26)
				&& mouseY < (topPos + 26) + 53) {
			this.renderTooltip(stack, new TranslatableComponent(
					"screen.exoterra.energy", MagicHelpers.withSuffix(this.container.getEnergy()),
					MagicHelpers.withSuffix(COM_BANK_CAPACITY_PUB)), mouseX, mouseY);	
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

		int maxEnergy = this.container.getMaxPower(), energywidth = 158;
		if (maxEnergy > 0) {
			int remaining = (this.container.getEnergy() * energywidth) / maxEnergy;

			this.blit(stack, 
					leftPos + 9, 				    // Destination top-left corner X
					topPos + 26, 			    // Destination top-left corner Y
					0,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
					166, 							    // Source top-left corner Y
					remaining + 1, 			    // Source Image Width - Iterates remaining in order scale width
					53);							    // Source Image height
			
			// corner top
			this.blit(stack, 
					leftPos + 11, 				    // Destination top-left corner X
					topPos + 28, 			    // Destination top-left corner Y
					2,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
					221, 							    // Source top-left corner Y
					154, 			    // Source Image Width - Iterates remaining in order scale width
					9);							    // Source Image height
			
			// corner bottom
						this.blit(stack, 
								leftPos + 11, 				    // Destination top-left corner X
								topPos + 68, 			    // Destination top-left corner Y
								2,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
								245, 							    // Source top-left corner Y
								154, 			    // Source Image Width - Iterates remaining in order scale width
								9);							    // Source Image height
			
		}

	}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		
		Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.common_power_bank_guititle"), 
				62, 4,Color.DARK_GRAY.getRGB());

	}
}
