package com.withertech.minekea.proxy;

import com.withertech.minekea.ModBlocks;
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

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class CommonProxy {
	public static CreativeTabs MinekeaTab;               // will hold our first custom creative tab
    public void preInit(FMLPreInitializationEvent e) 
    {
        MinekeaTab = new CreativeTabs("minekeatab") {
            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
              return new ItemStack(Blocks.CONCRETE);
            }
          };
    }

    public void init(FMLInitializationEvent e) 
    {
    	
    }

    public void postInit(FMLPostInitializationEvent e) 
    {
    	
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) 
    {
        event.getRegistry().register(new BlockTVStandEndLeft());
        event.getRegistry().register(new BlockTVStandEndRight());
        event.getRegistry().register(new BlockTVStandMiddle());
        
        event.getRegistry().register(new BlockLCouchWhiteEndLeft());
        event.getRegistry().register(new BlockLCouchWhiteEndRight());
        event.getRegistry().register(new BlockLCouchWhiteMiddle());
        event.getRegistry().register(new BlockLCouchWhiteCorner());
        event.getRegistry().register(new BlockLCouchWhiteExtension());
        
        event.getRegistry().register(new BlockCouchTableEnd());
        event.getRegistry().register(new BlockCouchTableMiddle());
        
        event.getRegistry().register(new BlockTVWallTopLeft());
        event.getRegistry().register(new BlockTVWallTopMiddle());
        event.getRegistry().register(new BlockTVWallTopRight());
        event.getRegistry().register(new BlockTVWallBottomLeft());
        event.getRegistry().register(new BlockTVWallBottomMiddle());
        event.getRegistry().register(new BlockTVWallBottomRight());
        
        event.getRegistry().register(new BlockSideTable());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndLeft).setRegistryName(ModBlocks.blockTVStandEndLeft.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndRight).setRegistryName(ModBlocks.blockTVStandEndRight.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandMiddle).setRegistryName(ModBlocks.blockTVStandMiddle.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteEndLeft).setRegistryName(ModBlocks.blockLCouchWhiteEndLeft.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteEndRight).setRegistryName(ModBlocks.blockLCouchWhiteEndRight.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteMiddle).setRegistryName(ModBlocks.blockLCouchWhiteMiddle.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteCorner).setRegistryName(ModBlocks.blockLCouchWhiteCorner.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockLCouchWhiteExtension).setRegistryName(ModBlocks.blockLCouchWhiteExtension.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockCouchTableEnd).setRegistryName(ModBlocks.blockCouchTableEnd.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockCouchTableMiddle).setRegistryName(ModBlocks.blockCouchTableMiddle.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopLeft).setRegistryName(ModBlocks.blockTVWallTopLeft.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopMiddle).setRegistryName(ModBlocks.blockTVWallTopMiddle.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallTopRight).setRegistryName(ModBlocks.blockTVWallTopRight.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomLeft).setRegistryName(ModBlocks.blockTVWallBottomLeft.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomMiddle).setRegistryName(ModBlocks.blockTVWallBottomMiddle.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVWallBottomRight).setRegistryName(ModBlocks.blockTVWallBottomRight.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockSideTable).setRegistryName(ModBlocks.blockSideTable.getRegistryName()));
    }
}
