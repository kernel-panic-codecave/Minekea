package com.withertech.minekea.blocks;

import com.withertech.minekea.Minekea;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiKitchenFridge extends GuiContainer
{
	
	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;
	
	private static final ResourceLocation background = new ResourceLocation(Minekea.MODID, "textures/gui/kitchenfridge.png");
	
	public GuiKitchenFridge(TileKitchenFridge tileEntity, ContainerKitchenFridge container)
	{
		super(container);
		
		xSize = WIDTH;
		ySize = HEIGHT;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}
	
}