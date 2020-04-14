package com.withertech.minekea.blocks;

import java.util.Collections;

import com.withertech.minekea.Minekea;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiKitchenWallOven extends GuiContainer
{
	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;
	
	private static final ResourceLocation background = new ResourceLocation(Minekea.MODID, "textures/gui/kitchenwalloven.png");
	private TileKitchenWallOven oven;
	
	public GuiKitchenWallOven(TileKitchenWallOven tileEntity, ContainerKitchenWallOven containerKitchenWallOven)
	{
		super(containerKitchenWallOven);
		
		xSize = WIDTH;
		ySize = HEIGHT;
		
		oven = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		int energy = oven.getClientEnergy();
		drawEnergyBar(energy);
		
		if (oven.getClientProgress() > 0)
		{
			int percentage = 100 - oven.getClientProgress() * 100 / TileKitchenWallOven.MAX_PROGRESS;
			drawString(mc.fontRenderer, percentage + "%", guiLeft + 80, guiTop + 50, 0xffffff);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
		
		if (mouseX > guiLeft + 38 && mouseX < guiLeft + 140 && mouseY > guiTop + 59 && mouseY < guiTop + 69)
		{
			drawHoveringText(Collections.singletonList("Energy: " + oven.getClientEnergy()), mouseX, mouseY, fontRenderer);
		}
	}
	
	private void drawEnergyBar(int energy)
	{
		drawRect(guiLeft + 38, guiTop + 59, guiLeft + 140, guiTop + 69, 0xff555555);
		int percentage = energy * 100 / TileKitchenCounterOven.MAX_POWER;
		for (int i = 0; i < percentage; i++)
		{
			drawVerticalLine(guiLeft + 38 + 1 + i, guiTop + 59, guiTop + 68, i % 2 == 0 ? 0xffff0000 : 0xff000000);
		}
	}
}
