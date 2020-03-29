package com.withertech.minekea;

import com.withertech.minekea.blocks.BlockCouchTableEnd;
import com.withertech.minekea.blocks.BlockCouchTableMiddle;
import com.withertech.minekea.blocks.BlockLCouchWhiteCorner;
import com.withertech.minekea.blocks.BlockLCouchWhiteEndLeft;
import com.withertech.minekea.blocks.BlockLCouchWhiteEndRight;
import com.withertech.minekea.blocks.BlockLCouchWhiteExtension;
import com.withertech.minekea.blocks.BlockLCouchWhiteMiddle;
import com.withertech.minekea.blocks.BlockTVStandEndLeft;
import com.withertech.minekea.blocks.BlockTVStandEndRight;
import com.withertech.minekea.blocks.BlockTVStandMiddle;

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
	
	@GameRegistry.ObjectHolder("modernfurniture:blockcouchtableend")
	public static BlockCouchTableEnd blockCouchTableEnd;
	
	@GameRegistry.ObjectHolder("modernfurniture:blockcouchtablemiddle")
	public static BlockCouchTableMiddle blockCouchTableMiddle;
	
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
		
		blockCouchTableEnd.initModel();
		blockCouchTableMiddle.initModel();
	}
}