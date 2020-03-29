package com.withertech.modernfurniture;

import com.withertech.modernfurniture.blocks.BlockTVStandEndLeft;
import com.withertech.modernfurniture.blocks.BlockTVStandEndRight;
import com.withertech.modernfurniture.blocks.BlockTVStandMiddle;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModBlocks 
{
	@GameRegistry.ObjectHolder("modernfurniture:blocktvstandendleft")
	public static BlockTVStandEndLeft blockTVStandEndLeft;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocktvstandendright")
	public static BlockTVStandEndRight blockTVStandEndRight;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocktvstandmiddle")
	public static BlockTVStandMiddle blockTVStandMiddle;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() 
	{
		blockTVStandEndLeft.initModel();
		blockTVStandEndRight.initModel();
		blockTVStandMiddle.initModel();
	}
}