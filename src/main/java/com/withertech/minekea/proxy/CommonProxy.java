package com.withertech.minekea.proxy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.google.common.util.concurrent.ListenableFuture;
import com.withertech.minekea.EnumColor;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.ModItems;
import com.withertech.minekea.blocks.*;
import com.withertech.minekea.handler.GuiHandler;
import com.withertech.minekea.items.ItemCookedBread;
import com.withertech.minekea.items.ItemNowYaDoneIt;
import com.withertech.minekea.items.ItemPleaseStop;
import com.withertech.minekea.items.ItemWhyWouldYouDoThis;
import com.withertech.minekea.network.Messages;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class CommonProxy
{
	public static CreativeTabs MinekeaMainTab;
	public static CreativeTabs MinekeaDenTab;
	public static CreativeTabs MinekeaKitchenTab;
	
	public void preInit(FMLPreInitializationEvent e)
	{
		Messages.registerMessages("minekea");
		MinekeaMainTab = new CreativeTabs("minekeamaintab")
		{
			@Override
			@SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ModBlocks.blockFurnitureStation);
			}
		};
		MinekeaDenTab = new CreativeTabs("minekeadentab")
		{
			@Override
			@SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ModBlocks.blockArmchairWhite);
			}
		};
		MinekeaKitchenTab = new CreativeTabs("minekeakitchentab")
		{
			@Override
			@SideOnly(Side.CLIENT)
			public ItemStack getTabIconItem()
			{
				return new ItemStack(ModBlocks.blockKitchenCounterOven);
			}
		};
	}
	
	public void init(FMLInitializationEvent e)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(Minekea.instance, new GuiHandler());
		OreDictionary.registerOre("foodToast", ModItems.itemCookedBread);
		
	}
	
	public void postInit(FMLPostInitializationEvent e)
	{
		
	}
	
	@Nullable
	public IAnimationStateMachine load(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters)
	{
		return null;
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(new BlockTVStandEndLeft());
		event.getRegistry().register(new BlockTVStandEndRight());
		event.getRegistry().register(new BlockTVStandMiddle());
		
		event.getRegistry().register(new BlockLCouchEndLeft().setRegistryName("blocklcouchwhiteendleft").setUnlocalizedName(Minekea.MODID + ".blocklcouchwhiteendleft"));
		event.getRegistry().register(new BlockLCouchEndRight().setRegistryName("blocklcouchwhiteendright").setUnlocalizedName(Minekea.MODID + ".blocklcouchwhiteendright"));
		event.getRegistry().register(new BlockLCouchMiddle().setRegistryName("blocklcouchwhitemiddle").setUnlocalizedName(Minekea.MODID + ".blocklcouchwhitemiddle"));
		event.getRegistry().register(new BlockLCouchCorner().setRegistryName("blocklcouchwhitecorner").setUnlocalizedName(Minekea.MODID + ".blocklcouchwhitecorner"));
		event.getRegistry().register(new BlockLCouchExtension().setRegistryName("blocklcouchwhiteextension").setUnlocalizedName(Minekea.MODID + ".blocklcouchwhiteextension"));
		
		event.getRegistry().register(new BlockLCouchEndLeft().setRegistryName("blocklcouchgrayendleft").setUnlocalizedName(Minekea.MODID + ".blocklcouchgrayendleft"));
		event.getRegistry().register(new BlockLCouchEndRight().setRegistryName("blocklcouchgrayendright").setUnlocalizedName(Minekea.MODID + ".blocklcouchgrayendright"));
		event.getRegistry().register(new BlockLCouchMiddle().setRegistryName("blocklcouchgraymiddle").setUnlocalizedName(Minekea.MODID + ".blocklcouchgraymiddle"));
		event.getRegistry().register(new BlockLCouchCorner().setRegistryName("blocklcouchgraycorner").setUnlocalizedName(Minekea.MODID + ".blocklcouchgraycorner"));
		event.getRegistry().register(new BlockLCouchExtension().setRegistryName("blocklcouchgrayextension").setUnlocalizedName(Minekea.MODID + ".blocklcouchgrayextension"));
		
		event.getRegistry().register(new BlockLCouchEndLeft().setRegistryName("blocklcouchblackendleft").setUnlocalizedName(Minekea.MODID + ".blocklcouchblackendleft"));
		event.getRegistry().register(new BlockLCouchEndRight().setRegistryName("blocklcouchblackendright").setUnlocalizedName(Minekea.MODID + ".blocklcouchblackendright"));
		event.getRegistry().register(new BlockLCouchMiddle().setRegistryName("blocklcouchblackmiddle").setUnlocalizedName(Minekea.MODID + ".blocklcouchblackmiddle"));
		event.getRegistry().register(new BlockLCouchCorner().setRegistryName("blocklcouchblackcorner").setUnlocalizedName(Minekea.MODID + ".blocklcouchblackcorner"));
		event.getRegistry().register(new BlockLCouchExtension().setRegistryName("blocklcouchblackextension").setUnlocalizedName(Minekea.MODID + ".blocklcouchblackextension"));
		
		event.getRegistry().register(new BlockCouchTableEnd());
		event.getRegistry().register(new BlockCouchTableMiddle());
		
		event.getRegistry().register(new BlockTVWallTopLeft());
		event.getRegistry().register(new BlockTVWallTopMiddle());
		event.getRegistry().register(new BlockTVWallTopRight());
		event.getRegistry().register(new BlockTVWallBottomLeft());
		event.getRegistry().register(new BlockTVWallBottomMiddle());
		event.getRegistry().register(new BlockTVWallBottomRight());
		
		event.getRegistry().register(new BlockSideTable());
		
		event.getRegistry().register(new BlockFurnitureStation());
		
		event.getRegistry().register(new BlockTableLamp(false).setRegistryName("blocktablelamp").setCreativeTab(CommonProxy.MinekeaDenTab));
		event.getRegistry().register(new BlockTableLamp(true).setRegistryName("blocktablelamp_on"));
		
		event.getRegistry().register(new BlockTallLampLight(false).setRegistryName("blocktalllamplight").setCreativeTab(CommonProxy.MinekeaDenTab));
		event.getRegistry().register(new BlockTallLampLight(true).setRegistryName("blocktalllamplight_on"));
		event.getRegistry().register(new BlockTallLampBase());
		
		event.getRegistry().register(new BlockArmchair().setRegistryName("blockarmchairwhite").setUnlocalizedName(Minekea.MODID + ".blockarmchairwhite"));
		event.getRegistry().register(new BlockArmchair().setRegistryName("blockarmchairgray").setUnlocalizedName(Minekea.MODID + ".blockarmchairgray"));
		event.getRegistry().register(new BlockArmchair().setRegistryName("blockarmchairblack").setUnlocalizedName(Minekea.MODID + ".blockarmchairblack"));
		
		event.getRegistry().register(new BlockKitchenTableSingle());
		
		event.getRegistry().register(new BlockKitchenTableTileableLeft());
		event.getRegistry().register(new BlockKitchenTableTileableRight());
		
		event.getRegistry().register(new BlockKitchenCounterStraight());
		event.getRegistry().register(new BlockKitchenCounterCornerInner());
		event.getRegistry().register(new BlockKitchenCounterCornerOuter());
		event.getRegistry().register(new BlockKitchenCounterEndLeft());
		event.getRegistry().register(new BlockKitchenCounterEndRight());
		event.getRegistry().register(new BlockKitchenCounterSink());
		event.getRegistry().register(new BlockKitchenCounterSinkFaucet());
		event.getRegistry().register(new BlockKitchenCounterOven());
		GameRegistry.registerTileEntity(TileKitchenCounterOven.class, Minekea.MODID + "_kitchencounteroven");
		event.getRegistry().register(new BlockKitchenCounterStove());
		event.getRegistry().register(new BlockKitchenCounterDishwasher());
		GameRegistry.registerTileEntity(TileKitchenCounterDishwasher.class, Minekea.MODID + "_kitchencounterdishwasher");
		
		event.getRegistry().register(new BlockKitchenWallOven());
		GameRegistry.registerTileEntity(TileKitchenWallOven.class, Minekea.MODID + "_kitchenwalloven");
		
		event.getRegistry().register(new BlockKitchenChair());
		
		event.getRegistry().register(new BlockKitchenFreezer());
		GameRegistry.registerTileEntity(TileKitchenFreezer.class, Minekea.MODID + "_kitchenfreezer");
		
		event.getRegistry().register(new BlockKitchenFridge());
		GameRegistry.registerTileEntity(TileKitchenFridge.class, Minekea.MODID + "_kitchenfridge");
		
		event.getRegistry().register(new BlockKitchenToaster());
		GameRegistry.registerTileEntity(TileKitchenToaster.class, Minekea.MODID + "_kitchentoaster");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new ItemCookedBread());
		event.getRegistry().register(new ItemWhyWouldYouDoThis());
		event.getRegistry().register(new ItemPleaseStop());
		event.getRegistry().register(new ItemNowYaDoneIt());
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndLeft).setRegistryName(ModBlocks.blockTVStandEndLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndRight).setRegistryName(ModBlocks.blockTVStandEndRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandMiddle).setRegistryName(ModBlocks.blockTVStandMiddle.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteEndLeft).setRegistryName(ModBlocks.blockLCouchWhiteEndLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteEndRight).setRegistryName(ModBlocks.blockLCouchWhiteEndRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteMiddle).setRegistryName(ModBlocks.blockLCouchWhiteMiddle.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteCorner).setRegistryName(ModBlocks.blockLCouchWhiteCorner.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteExtension).setRegistryName(ModBlocks.blockLCouchWhiteExtension.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchGrayEndLeft).setRegistryName(ModBlocks.blockLCouchGrayEndLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchGrayEndRight).setRegistryName(ModBlocks.blockLCouchGrayEndRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchGrayMiddle).setRegistryName(ModBlocks.blockLCouchGrayMiddle.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchGrayCorner).setRegistryName(ModBlocks.blockLCouchGrayCorner.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchGrayExtension).setRegistryName(ModBlocks.blockLCouchGrayExtension.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchBlackEndLeft).setRegistryName(ModBlocks.blockLCouchBlackEndLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchBlackEndRight).setRegistryName(ModBlocks.blockLCouchBlackEndRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchBlackMiddle).setRegistryName(ModBlocks.blockLCouchBlackMiddle.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchBlackCorner).setRegistryName(ModBlocks.blockLCouchBlackCorner.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchBlackExtension).setRegistryName(ModBlocks.blockLCouchBlackExtension.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockCouchTableEnd).setRegistryName(ModBlocks.blockCouchTableEnd.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockCouchTableMiddle).setRegistryName(ModBlocks.blockCouchTableMiddle.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopLeft).setRegistryName(ModBlocks.blockTVWallTopLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopMiddle).setRegistryName(ModBlocks.blockTVWallTopMiddle.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopRight).setRegistryName(ModBlocks.blockTVWallTopRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomLeft).setRegistryName(ModBlocks.blockTVWallBottomLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomMiddle).setRegistryName(ModBlocks.blockTVWallBottomMiddle.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomRight).setRegistryName(ModBlocks.blockTVWallBottomRight.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockSideTable).setRegistryName(ModBlocks.blockSideTable.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockFurnitureStation).setRegistryName(ModBlocks.blockFurnitureStation.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTableLamp).setRegistryName(ModBlocks.blockTableLamp.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTallLampLight).setRegistryName(ModBlocks.blockTallLampLight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockTallLampBase).setRegistryName(ModBlocks.blockTallLampBase.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockArmchairWhite).setRegistryName(ModBlocks.blockArmchairWhite.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockArmchairGray).setRegistryName(ModBlocks.blockArmchairGray.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockArmchairBlack).setRegistryName(ModBlocks.blockArmchairBlack.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenTableSingle).setRegistryName(ModBlocks.blockKitchenTableSingle.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenTableTileableLeft).setRegistryName(ModBlocks.blockKitchenTableTileableLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenTableTileableRight).setRegistryName(ModBlocks.blockKitchenTableTileableRight.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterStraight).setRegistryName(ModBlocks.blockKitchenCounterStraight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterCornerInner).setRegistryName(ModBlocks.blockKitchenCounterCornerInner.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterCornerOuter).setRegistryName(ModBlocks.blockKitchenCounterCornerOuter.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterEndLeft).setRegistryName(ModBlocks.blockKitchenCounterEndLeft.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterEndRight).setRegistryName(ModBlocks.blockKitchenCounterEndRight.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterSink).setRegistryName(ModBlocks.blockKitchenCounterSink.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterSinkFaucet).setRegistryName(ModBlocks.blockKitchenCounterSinkFaucet.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterOven).setRegistryName(ModBlocks.blockKitchenCounterOven.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterStove).setRegistryName(ModBlocks.blockKitchenCounterStove.getRegistryName()));
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenCounterDishwasher).setRegistryName(ModBlocks.blockKitchenCounterDishwasher.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenWallOven).setRegistryName(ModBlocks.blockKitchenWallOven.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenChair).setRegistryName(ModBlocks.blockKitchenChair.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenFreezer).setRegistryName(ModBlocks.blockKitchenFreezer.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenFridge).setRegistryName(ModBlocks.blockKitchenFridge.getRegistryName()));
		
		event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenToaster).setRegistryName(ModBlocks.blockKitchenToaster.getRegistryName()));
	}
	
	public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule)
	{
		throw new IllegalStateException("This should only be called from client side");
	}
	
	public EntityPlayer getClientPlayer()
	{
		throw new IllegalStateException("This should only be called from client side");
	}
}
