package com.withertech.minekea;

import com.withertech.minekea.blocks.BlockCouchTableEnd;
import com.withertech.minekea.blocks.BlockCouchTableMiddle;
import com.withertech.minekea.blocks.BlockLCouchWhiteCorner;
import com.withertech.minekea.blocks.BlockLCouchWhiteEndLeft;
import com.withertech.minekea.blocks.BlockLCouchWhiteEndRight;
import com.withertech.minekea.blocks.BlockLCouchWhiteExtension;
import com.withertech.minekea.blocks.BlockLCouchWhiteMiddle;
import com.withertech.minekea.blocks.BlockSideTable;
import com.withertech.minekea.blocks.BlockTVStandEndLeft;
import com.withertech.minekea.blocks.BlockTVStandEndRight;
import com.withertech.minekea.blocks.BlockTVStandMiddle;
import com.withertech.minekea.blocks.BlockTVWallBottomLeft;
import com.withertech.minekea.blocks.BlockTVWallBottomMiddle;
import com.withertech.minekea.blocks.BlockTVWallBottomRight;
import com.withertech.minekea.blocks.BlockTVWallTopLeft;
import com.withertech.minekea.blocks.BlockTVWallTopMiddle;
import com.withertech.minekea.blocks.BlockTVWallTopRight;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModBlocks 
{
	@GameRegistry.ObjectHolder("minekea:blocktvstandendleft")
	public static BlockTVStandEndLeft blockTVStandEndLeft;
	
	@GameRegistry.ObjectHolder("minekea:blocktvstandendright")
	public static BlockTVStandEndRight blockTVStandEndRight;
	
	@GameRegistry.ObjectHolder("minekea:blocktvstandmiddle")
	public static BlockTVStandMiddle blockTVStandMiddle;
	
	
	@GameRegistry.ObjectHolder("minekea:blocklcouchwhiteendleft")
	public static BlockLCouchWhiteEndLeft blockLCouchWhiteEndLeft;
	
	@GameRegistry.ObjectHolder("minekea:blocklcouchwhiteendright")
	public static BlockLCouchWhiteEndRight blockLCouchWhiteEndRight;
	
	@GameRegistry.ObjectHolder("minekea:blocklcouchwhitemiddle")
	public static BlockLCouchWhiteMiddle blockLCouchWhiteMiddle;
	
	@GameRegistry.ObjectHolder("minekea:blocklcouchwhitecorner")
	public static BlockLCouchWhiteCorner blockLCouchWhiteCorner;
	
	@GameRegistry.ObjectHolder("minekea:blocklcouchwhiteextension")
	public static BlockLCouchWhiteExtension blockLCouchWhiteExtension;
	
	
	@GameRegistry.ObjectHolder("minekea:blockcouchtableend")
	public static BlockCouchTableEnd blockCouchTableEnd;
	
	@GameRegistry.ObjectHolder("minekea:blockcouchtablemiddle")
	public static BlockCouchTableMiddle blockCouchTableMiddle;
	
	
	@GameRegistry.ObjectHolder("minekea:blocktvwalltopleft")
	public static BlockTVWallTopLeft blockTVWallTopLeft;

	@GameRegistry.ObjectHolder("minekea:blocktvwalltopmiddle")
	public static BlockTVWallTopMiddle blockTVWallTopMiddle;
	
	@GameRegistry.ObjectHolder("minekea:blocktvwalltopright")
	public static BlockTVWallTopRight blockTVWallTopRight;
	
	@GameRegistry.ObjectHolder("minekea:blocktvwallbottomleft")
	public static BlockTVWallBottomLeft blockTVWallBottomLeft;
	
	@GameRegistry.ObjectHolder("minekea:blocktvwallbottommiddle")
	public static BlockTVWallBottomMiddle blockTVWallBottomMiddle;
	
	@GameRegistry.ObjectHolder("minekea:blocktvwallbottomright")
	public static BlockTVWallBottomRight blockTVWallBottomRight;
	
	
	@GameRegistry.ObjectHolder("minekea:blocksidetable")
	public static BlockSideTable blockSideTable;

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
		
		blockTVWallTopLeft.initModel();
		blockTVWallTopMiddle.initModel();
		blockTVWallTopRight.initModel();
		blockTVWallBottomLeft.initModel();
		blockTVWallBottomMiddle.initModel();
		blockTVWallBottomRight.initModel();
		
		blockSideTable.initModel();
	}
}