package com.faojen.exoterra.blocks.fabricationbench;

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

public class FabricationBenchScreen extends AbstractContainerScreen<FabricationBenchContainer> {
	private static final ResourceLocation background = new ResourceLocation(ExoTerra.MODID,
			"textures/gui/fabrication_bench.png");

	private final FabricationBenchContainer container;

	public FabricationBenchScreen(FabricationBenchContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title);
		this.container = container;
	}

	 int facpanel;
	 int facpart;
	 int fluidout;
	 int fract;
	 int infcore;
	 int infpart;
	 int intpanel;
	 int macbody;
	 int infstel;
	
	 private void renderQuantityTooltips(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		// 1 FACPANEL
			this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
			if (mouseX > (leftPos + 64) && mouseX < (leftPos + 64) + 18 && mouseY > (topPos + 26)
					&& mouseY < (topPos + 26) + 18) {
				this.renderTooltip(stack, new TranslatableComponent(
						"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getFacpanel()),
						MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
			}
			// 2 FACPART
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 83) && mouseX < (leftPos + 83) + 18 && mouseY > (topPos + 26)
								&& mouseY < (topPos + 26) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getFacpart()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 3 FLUIDOUT
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 102) && mouseX < (leftPos + 102) + 18 && mouseY > (topPos + 26)
								&& mouseY < (topPos + 26) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getFluidOut()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 4 FRACT
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 121) && mouseX < (leftPos + 121) + 18 && mouseY > (topPos + 26)
								&& mouseY < (topPos + 26) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getFract()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 5 INFCORE
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 140) && mouseX < (leftPos + 140) + 18 && mouseY > (topPos + 26)
								&& mouseY < (topPos + 26) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getInfcore()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 6 INFPART
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 64) && mouseX < (leftPos + 64) + 18 && mouseY > (topPos + 45)
								&& mouseY < (topPos + 45) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getInfpart()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 7 INTPANEL
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 83) && mouseX < (leftPos + 83) + 18 && mouseY > (topPos + 45)
								&& mouseY < (topPos + 45) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getIntpanel()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 8 MACBODY
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 102) && mouseX < (leftPos + 102) + 18 && mouseY > (topPos + 45)
								&& mouseY < (topPos + 45) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getMacbody()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
						// 9 INFSTEL
						this.renderTooltip(stack, mouseX, mouseY); // @mcp: renderTooltip = renderHoveredToolTip
						if (mouseX > (leftPos + 121) && mouseX < (leftPos + 121) + 18 && mouseY > (topPos + 45)
								&& mouseY < (topPos + 45) + 18) {
							this.renderTooltip(stack, new TranslatableComponent(
									"screen.exoterra.inventorytip", MagicHelpers.withSuffix(this.container.getInfstel()),
									MagicHelpers.withSuffix(1000)), mouseX, mouseY);	
						}
	 }
	 
	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(stack);
		super.render(stack, mouseX, mouseY, partialTicks);

		renderQuantityTooltips(stack, mouseX, mouseY, partialTicks);
		
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
		
				// -----------------------------------------------------------------------------------------------
				// ICON BLITS :(
				
					if(this.container.getFacpanel() > 0) {
						// Display facpanel count
						this.blit(stack, leftPos + 64, topPos + 26, 176,  52, 18, 18);
						
					}
					if(this.container.getFacpart() > 0) {
						// Display facpart count
						this.blit(stack, leftPos + 83, topPos + 26, 194,  52, 18, 18);
						
					}
					if(this.container.getFluidOut() > 0) {
						// Display fluidout count
						this.blit(stack, leftPos + 102, topPos + 26, 212, 52, 18, 18);
						
					}
					if(this.container.getFract() > 0) {
						// Display fract count
						this.blit(stack, leftPos + 122, topPos + 26, 212, 70, 18, 18);
						
					}
					if(this.container.getInfcore() > 0) {
						// Display infcore count
						this.blit(stack, leftPos + 121, topPos + 26, 176,  70, 18, 18);
						
					}
					if(this.container.getInfpart() > 0) {
						// Display infpart count
						this.blit(stack, leftPos + 140, topPos + 26, 230, 52, 18, 18);
						
					} 
					if(this.container.getIntpanel() > 0) {
						// Display intpanel count
						this.blit(stack, leftPos + 64, topPos + 45, 194,  70, 18, 18);
						
					}
					if(this.container.getMacbody() > 0) {
						// Display macbody count
						this.blit(stack, leftPos + 83, topPos + 45, 230,  70, 18, 18);
						
					}
					if(this.container.getInfstel() > 0) {
						// Display infstel count
						this.blit(stack, leftPos + 102, topPos + 45, 176,  88, 18, 18);
						
					}
				
				// -----------------------------------------------------------------------------------------------
				
			}
	}
	
	@SuppressWarnings("resource")
	@Override
	protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
		
		Minecraft.getInstance().font.draw(stack, I18n.get("block.exoterra.fabrication_bench"), 
				62, 4,Color.DARK_GRAY.getRGB());
		Minecraft.getInstance().font.draw(stack, (new TranslatableComponent(
				"screen.exoterra.powerload", MagicHelpers.withSuffix(this.container.getPowerLoad()))), 
				62, 15,Color.decode("#6b1e1e").getRGB());

	}
}
