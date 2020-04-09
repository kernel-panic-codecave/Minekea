package com.withertech.minekea;

import com.withertech.minekea.blocks.*;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModBlocks 
{
	//Main
	//Furniture Station
	@GameRegistry.ObjectHolder("minekea:blockfurniturestation")
	public static BlockFurnitureStation blockFurnitureStation;
	
	//Den
	//TV Stand
	@GameRegistry.ObjectHolder("minekea:blocktvstandendleft")
	public static BlockTVStandEndLeft blockTVStandEndLeft;
	
	@GameRegistry.ObjectHolder("minekea:blocktvstandendright")
	public static BlockTVStandEndRight blockTVStandEndRight;
	
	@GameRegistry.ObjectHolder("minekea:blocktvstandmiddle")
	public static BlockTVStandMiddle blockTVStandMiddle;
	
	//White L-Couch
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
	
	//Couch Table
	@GameRegistry.ObjectHolder("minekea:blockcouchtableend")
	public static BlockCouchTableEnd blockCouchTableEnd;
	
	@GameRegistry.ObjectHolder("minekea:blockcouchtablemiddle")
	public static BlockCouchTableMiddle blockCouchTableMiddle;
	
	//Wall Mounted TV
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
	
	//Side Table
	@GameRegistry.ObjectHolder("minekea:blocksidetable")
	public static BlockSideTable blockSideTable;
	
	
	//Table Lamp
	@GameRegistry.ObjectHolder("minekea:blocktablelamp")
	public static BlockTableLamp blockTableLamp;
	
	@GameRegistry.ObjectHolder("minekea:blocktablelamp_on")
	public static BlockTableLamp blockTableLamp_On;
	
	//Tall Lamp
	@GameRegistry.ObjectHolder("minekea:blocktalllamplight")
	public static BlockTallLampLight blockTallLampLight;
	
	@GameRegistry.ObjectHolder("minekea:blocktalllamplight_on")
	public static BlockTallLampLight blockTallLampLight_On;
	
	@GameRegistry.ObjectHolder("minekea:blocktalllampbase")
	public static BlockTallLampBase blockTallLampBase;
	
	//White Armchair
	@GameRegistry.ObjectHolder("minekea:blockarmchairwhite")
	public static BlockArmchairWhite blockArmchairWhite;
	
	//Kitchen
	//Kitchen Table Single
	@GameRegistry.ObjectHolder("minekea:blockkitchentablesingle")
	public static BlockKitchenTableSingle blockKitchenTableSingle;
	
	//Kitchen Table Tileable
	@GameRegistry.ObjectHolder("minekea:blockkitchentabletileableleft")
	public static BlockKitchenTableTileableLeft blockKitchenTableTileableLeft;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchentabletileableright")
	public static BlockKitchenTableTileableRight blockKitchenTableTileableRight;
	
	//Kitchen Counter
	@GameRegistry.ObjectHolder("minekea:blockkitchencounterstraight")
	public static BlockKitchenCounterStraight blockKitchenCounterStraight;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencountercornerinner")
	public static BlockKitchenCounterCornerInner blockKitchenCounterCornerInner;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencountercornerouter")
	public static BlockKitchenCounterCornerOuter blockKitchenCounterCornerOuter;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencounterendleft")
	public static BlockKitchenCounterEndLeft blockKitchenCounterEndLeft;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencounterendright")
	public static BlockKitchenCounterEndRight blockKitchenCounterEndRight;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencountersink")
	public static BlockKitchenCounterSink blockKitchenCounterSink;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencountersinkfaucet")
	public static BlockKitchenCounterSinkFaucet blockKitchenCounterSinkFaucet;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencounteroven")
	public static BlockKitchenCounterOven blockKitchenCounterOven;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencounterstove")
	public static BlockKitchenCounterStove blockKitchenCounterStove;
	
	@GameRegistry.ObjectHolder("minekea:blockkitchencounterdishwasher")
	public static BlockKitchenCounterDishwasher blockKitchenCounterDishwasher;
	
	//Kitchen Wall Oven
	@GameRegistry.ObjectHolder("minekea:blockkitchenwalloven")
	public static BlockKitchenWallOven blockKitchenWallOven;
	
	//Kitchen Chair
	@GameRegistry.ObjectHolder("minekea:blockkitchenchair")
	public static BlockKitchenChair blockKitchenChair;
	
	@SideOnly(Side.CLIENT)
	public static void initModels() 
	{
		//Main
		//Furniture Station
		blockFurnitureStation.initModel();
		//Den
		//TV Stand
		blockTVStandEndLeft.initModel();
		blockTVStandEndRight.initModel();
		blockTVStandMiddle.initModel();
		//White L-Couch
		blockLCouchWhiteEndLeft.initModel();
		blockLCouchWhiteEndRight.initModel();
		blockLCouchWhiteMiddle.initModel();
		blockLCouchWhiteCorner.initModel();
		blockLCouchWhiteExtension.initModel();
		//Couch Table
		blockCouchTableEnd.initModel();
		blockCouchTableMiddle.initModel();
		//Wall Mounted TV
		blockTVWallTopLeft.initModel();
		blockTVWallTopMiddle.initModel();
		blockTVWallTopRight.initModel();
		blockTVWallBottomLeft.initModel();
		blockTVWallBottomMiddle.initModel();
		blockTVWallBottomRight.initModel();
		//Side Table
		blockSideTable.initModel();
		//Table Lamp
		blockTableLamp.initModel();
		blockTableLamp_On.initModel();
		//Tall Lamp
		blockTallLampLight.initModel();
		blockTallLampLight_On.initModel();
		blockTallLampBase.initModel();
		//White Armchair
		blockArmchairWhite.initModel();
		//Kitchen
		//Kitchen Table Single
		blockKitchenTableSingle.initModel();
		//Kitchen Table Tileable
		blockKitchenTableTileableLeft.initModel();
		blockKitchenTableTileableRight.initModel();
		//Kitchen Counter
		blockKitchenCounterStraight.initModel();
		blockKitchenCounterCornerInner.initModel();
		blockKitchenCounterCornerOuter.initModel();
		blockKitchenCounterEndLeft.initModel();
		blockKitchenCounterEndRight.initModel();
		blockKitchenCounterSink.initModel();
		blockKitchenCounterSinkFaucet.initModel();
		blockKitchenCounterOven.initModel();
		blockKitchenCounterStove.initModel();
		blockKitchenCounterDishwasher.initModel();
		//Kitchen Wall Oven
		blockKitchenWallOven.initModel();
		//Kitchen Chair
		blockKitchenChair.initModel();
	}
}