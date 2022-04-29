package com.faojen.exoterra.blocks.stellarconverter;

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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StellarConverterScreen extends AbstractContainerScreen<StellarConverterContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/stellar_converter.png");

	private final StellarConverterContainer container;

	public StellarConverterScreen(StellarConverterContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);

		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
		if (mouseX > (leftPos + 7) && mouseX < (leftPos + 7) + 70 && mouseY > (topPos + 62)
				&& mouseY < (topPos + 62) + 17)
			this.renderTooltip(stack,
					Language.getInstance()
							.getVisualOrder(Arrays.asList(new TranslatableComponent(
									"screen.exoterra.energy", MagicHelpers.withSuffix(this.container.getEnergy()),
									MagicHelpers.withSuffix(this.container.getMaxPower())),
									this.container.getRemaining() <= 0
											? new TranslatableComponent("screen.exoterra.no_fuel")
											: new TranslatableComponent("screen.exoterra.burn_time",
													MagicHelpers.ticksInSeconds(this.container.getRemaining())))),
					mouseX, mouseY);

		this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
		if (mouseX > (leftPos + 97) && mouseX < (leftPos + 97) + 70 && mouseY > (topPos + 62)
				&& mouseY < (topPos + 62) + 17)
			this.renderTooltip(stack,
					Language.getInstance()
							.getVisualOrder(Arrays.asList(new TranslatableComponent(
									"screen.exoterra.fluid", MagicHelpers.withSuffix(this.container.getFluidStored()),
									MagicHelpers.withSuffix(this.container.getMaxCapacity())),
									this.container.getEnergy() <= 19999
											? new TranslatableComponent("screen.exoterra.no_energy")
											: new TranslatableComponent("screen.exoterra.purify_time",
													MagicHelpers.ticksInSeconds(this.container.getPurifyRemaining())))),
					mouseX, mouseY);
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
			this.blit(stack, leftPos + 28, topPos + 42 + 16 - remaining, 177, 48 - remaining, 5, remaining + 1);
		}
		
//purify status thing
		// Stellar drop thing
		if (this.container.getPurifyMaxBurn() > 0) {
			int purifyBarMaxWidth = 58;
			int remaining = (this.container.getPurifyRemaining() * purifyBarMaxWidth) / this.container.getPurifyMaxBurn();
			this.blit(stack, 
					leftPos + 144 - 58 + remaining, 				    
					topPos + 50, 			   
					188, 
					33, 							    // Source top-left corner Y
					4, 			    // Source Image Width - Iterates remaining in order scale width
					2);							    // Source Image height
			// chamber edge layer
			this.blit(stack, 
					leftPos + 98, 				    
					topPos + 50, 			   
					98, 
					50, 							    // Source top-left corner Y
					3, 			    // Source Image Width - Iterates remaining in order scale width
					2);	
			// chamber center layer
			this.blit(stack, 
					leftPos + 86, 				    
					topPos + 50, 			   
					86, 
					50, 							    // Source top-left corner Y
					4, 			    // Source Image Width - Iterates remaining in order scale width
					2);	
			// tunnel cover thing layer
			this.blit(stack, 
					leftPos + 145, 				    
					topPos + 48, 			   
					145, 
					48, 							    // Source top-left corner Y
					5, 			    // Source Image Width - Iterates remaining in order scale width
					6);	
		}
		// Energy drop thing
		if (this.container.getPurifyMaxBurn() > 0) {
			int powerPurifyMaxWidth = 48;
			int remaining = (this.container.getPurifyRemaining() * powerPurifyMaxWidth) / this.container.getPurifyMaxBurn();
			this.blit(stack, 
					leftPos + 38 + 48 - remaining, 				    
					topPos + 50, 			   
					184, 
					33, 
					4, 	
					2);	
			// chamber edge layer
			this.blit(stack, 
					leftPos + 74, 				    
					topPos + 50, 			   
					74, 
					50, 			
					4, 		
					2);	
			// chamber center layer
			this.blit(stack, 
					leftPos + 86, 				    
					topPos + 50, 			   
					86, 
					50, 					
					4, 			
					2);	
		}	
		
		// Power Display

		int maxEnergy = this.container.getMaxPower(), energywidth = 70;
		if (maxEnergy > 0) {
			int remaining = (this.container.getEnergy() * energywidth) / maxEnergy;

			this.blit(stack, 
					leftPos + 8, 				    // Destination top-left corner X
					topPos + 63, 			    // Destination top-left corner Y
					176 + 70 - remaining,  // Source top-left corner X, adding the horizontal bar's width, subtracting the remaining space in the tank.
					16, 							    // Source top-left corner Y
					remaining + 1, 			    // Source Image Width - Iterates remaining in order scale width
					16);							    // Source Image height
			
			// meter overlay
			this.blit(stack, 
					leftPos + 9, 				    
					topPos + 64, 			   
					184, 
					36, 					
					68, 			  
					14);	
		}

		// Stellar display
		int maxFluid = this.container.getMaxCapacity(), stellarwidth = 70;
		if (maxFluid > 0) {
			int remaining = (this.container.getFluidStored() * stellarwidth) / maxFluid;

			this.blit(stack, 
					leftPos + 98, 			
					topPos + 63, 			  
					176 + 70 - remaining,  
					0, 								 
					remaining + 1, 			   
					15);					
			
			// meter overlay
			this.blit(stack, 
					leftPos + 99, 				    
					topPos + 64, 			   
					184, 
					36, 							 
					68, 			   
					14);	
			
		}
	}

	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		
		Minecraft.getInstance().font.draw(stack, I18n.get("block.exoterra.stellar_converter"), 
				15, 28,Color.DARK_GRAY.getRGB());
		
//		Minecraft.getInstance().font.draw(stack, I18n.get("I: " + container.get), 
//				15, 28,Color.DARK_GRAY.getRGB());
//		
//		Minecraft.getInstance().font.draw(stack, I18n.get("O: "), 
//				15, 28,Color.DARK_GRAY.getRGB());
		
		
//		this.font.draw(stack, new TranslatableComponent("screen.exoterra.fluidplaque", MagicHelpers.withSuffix(this.container.getFluidStored()), MagicHelpers.withSuffix(this.container.get())), 
//				98 ,63 ,Color.BLACK.getRGB());
//		
//		this.font.draw(stack, new TranslatableComponent("screen.exoterra.powerplaque", MagicHelpers.withSuffix(this.container.getEnergy()), MagicHelpers.withSuffix(this.container.getMaxPower())), 
//				8 ,63 ,Color.BLACK.getRGB());
		

	}
}
