package com.withertech.modernfurniture;

import com.withertech.modernfurniture.blocks.BlockLCouchWhiteCorner;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteEndLeft;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteEndRight;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteExtension;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteMiddle;
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
	
	@GameRegistry.ObjectHolder("modernfurniture:blocklcouchwhiteendleft")
	public static BlockLCouchWhiteEndLeft blockLCouchWhiteEndLeft;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocklcouchwhiteendright")
	public static BlockLCouchWhiteEndRight blockLCouchWhiteEndRight;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocklcouchwhitemiddle")
	public static BlockLCouchWhiteMiddle blockLCouchWhiteMiddle;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocklcouchwhitecorner")
	public static BlockLCouchWhiteCorner blockLCouchWhiteCorner;
	
	@GameRegistry.ObjectHolder("modernfurniture:blocklcouchwhiteextension")
	public static BlockLCouchWhiteExtension blockLCouchWhiteExtension;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() 
	{
		blockTVStandEndLeft.initModel();
		blockTVStandEndRight.initModel();
		blockTVStandMiddle.initModel();
		
		blockLCouchWhiteEndLeft.initModel();
		blockLCouchWhiteEndRight.initModel();
		blockLCouchWhiteMiddle.initModel();
		blockLCouchWhiteCorner.initModel();
		blockLCouchWhiteExtension.initModel();
	}
}