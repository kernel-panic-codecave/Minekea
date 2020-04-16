package com.withertech.minekea.blocks;

import java.util.Collections;

import com.withertech.minekea.Minekea;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiKitchenBlender extends GuiContainer
{
	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;
	
	private static final ResourceLocation background = new ResourceLocation(Minekea.MODID, "textures/gui/kitchenblender.png");
	private TileKitchenBlender te;
	
	public GuiKitchenBlender(TileKitchenBlender tileEntity, ContainerKitchenBlender container)
	{
		super(container);
		
		xSize = WIDTH;
		ySize = HEIGHT;
		
		te = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (te.getClientProgress() > 0)
		{
			int percentage = 100 - te.getClientProgress() * 100 / TileKitchenBlender.MAX_PROGRESS;
			drawString(mc.fontRenderer, percentage + "%", guiLeft + 80, guiTop + 50, 0xffffff);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		
	}
	
}
