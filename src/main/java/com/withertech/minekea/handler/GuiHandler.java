package com.withertech.minekea.handler;

import javax.annotation.Nullable;

import com.withertech.minekea.blocks.ContainerKitchenCounterDishwasher;
import com.withertech.minekea.blocks.ContainerKitchenCounterOven;
import com.withertech.minekea.blocks.ContainerKitchenFreezer;
import com.withertech.minekea.blocks.ContainerKitchenFridge;
import com.withertech.minekea.blocks.ContainerKitchenWallOven;
import com.withertech.minekea.blocks.GuiKitchenCounterDishwasher;
import com.withertech.minekea.blocks.GuiKitchenCounterOven;
import com.withertech.minekea.blocks.GuiKitchenFreezer;
import com.withertech.minekea.blocks.GuiKitchenFridge;
import com.withertech.minekea.blocks.GuiKitchenWallOven;
import com.withertech.minekea.blocks.TileKitchenCounterDishwasher;
import com.withertech.minekea.blocks.TileKitchenCounterOven;
import com.withertech.minekea.blocks.TileKitchenFreezer;
import com.withertech.minekea.blocks.TileKitchenFridge;
import com.withertech.minekea.blocks.TileKitchenWallOven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Nullable
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileKitchenCounterOven)
		{
			return new ContainerKitchenCounterOven(player.inventory, (TileKitchenCounterOven) te);
		}
		if (te instanceof TileKitchenWallOven)
		{
			return new ContainerKitchenWallOven(player.inventory, (TileKitchenWallOven) te);
		}
		if (te instanceof TileKitchenCounterDishwasher)
		{
			return new ContainerKitchenCounterDishwasher(player.inventory, (TileKitchenCounterDishwasher) te);
		}
		if (te instanceof TileKitchenFreezer)
		{
			return new ContainerKitchenFreezer(player.inventory, (TileKitchenFreezer) te);
		}
		if (te instanceof TileKitchenFridge)
		{
			return new ContainerKitchenFridge(player.inventory, (TileKitchenFridge) te);
		}
		return null;
	}
	
	@Nullable
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileKitchenCounterOven)
		{
			TileKitchenCounterOven containerTileEntity = (TileKitchenCounterOven) te;
			return new GuiKitchenCounterOven(containerTileEntity, new ContainerKitchenCounterOven(player.inventory, containerTileEntity));
		}
		if (te instanceof TileKitchenWallOven)
		{
			TileKitchenWallOven containerTileEntity = (TileKitchenWallOven) te;
			return new GuiKitchenWallOven(containerTileEntity, new ContainerKitchenWallOven(player.inventory, containerTileEntity));
		}
		if (te instanceof TileKitchenCounterDishwasher)
		{
			TileKitchenCounterDishwasher containerTileEntity = (TileKitchenCounterDishwasher) te;
			return new GuiKitchenCounterDishwasher(containerTileEntity, new ContainerKitchenCounterDishwasher(player.inventory, containerTileEntity));
		}
		if (te instanceof TileKitchenFreezer)
		{
			TileKitchenFreezer containerTileEntity = (TileKitchenFreezer) te;
			return new GuiKitchenFreezer(containerTileEntity, new ContainerKitchenFreezer(player.inventory, containerTileEntity));
		}
		if (te instanceof TileKitchenFridge)
		{
			TileKitchenFridge containerTileEntity = (TileKitchenFridge) te;
			return new GuiKitchenFridge(containerTileEntity, new ContainerKitchenFridge(player.inventory, containerTileEntity));
		}
		return null;
	}
}
