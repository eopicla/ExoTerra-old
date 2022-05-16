package com.faojen.exoterra.blocks.machine.crystalcatalyst;

import java.awt.Color;

import com.faojen.exoterra.ExoTerra;
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
		if (mouseX > (leftPos + 142) && mouseX < (leftPos + 142) + 23 && mouseY > (topPos + 28)
				&& mouseY < (topPos + 28) + 49) {
			this.renderTooltip(stack, new TranslatableComponent(
					"screen.exoterra.fluid", MagicHelpers.withSuffix(this.container.getFluidStored()),
					MagicHelpers.withSuffix(this.container.getMaxCapacity())), mouseX, mouseY);	
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

		// burn status thing
		if (this.container.getMaxBurn() > 0) {
			int burnStatusMaxHeight = 16;
			int remaining = (this.container.getRemaining() * burnStatusMaxHeight) / this.container.getMaxBurn();
			this.blit(stack, leftPos + 80, topPos + 35 + 16 - remaining, 187, 67 - remaining, 16, remaining + 1);
		}
		
		// Stellar display
		int maxFluid = this.container.getMaxCapacity(), stellarheight = 49;
		if (maxFluid > 0) {
			int remaining = (this.container.getFluidStored() * stellarheight) / maxFluid;
			this.blit(stack, 
					leftPos + 142, 			
					topPos + 28 + 49 - remaining, 			  
					176,  
					129 - remaining, 								 
					23, 			   
					remaining + 1);			
			
		}
	}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		
		Minecraft.getInstance().font.draw(stack, I18n.get("screen.exoterra.crystal_catalyst_guititle"), 
				62, 4,Color.WHITE.getRGB());
		
//		Minecraft.getInstance().font.draw(stack, I18n.get("I: " + container.get), 
//				15, 28,Color.DARK_GRAY.getRGB());
//		
//		Minecraft.getInstance().font.draw(stack, I18n.get("O: "), 
//				15, 28,Color.DARK_GRAY.getRGB());
		
		
		this.font.draw(stack, new TranslatableComponent("screen.exoterra.percentage", MagicHelpers.withSuffix(this.container.getCrystallizing()*100/2400)),  102 ,65 ,Color.WHITE.getRGB());
//		
//		this.font.draw(stack, new TranslatableComponent("screen.exoterra.powerplaque", MagicHelpers.withSuffix(this.container.getEnergy()), MagicHelpers.withSuffix(this.container.getMaxPower())), 
//				8 ,63 ,Color.BLACK.getRGB());
		

	}
}
