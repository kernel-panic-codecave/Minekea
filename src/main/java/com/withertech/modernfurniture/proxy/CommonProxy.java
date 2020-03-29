package com.withertech.modernfurniture.proxy;

import com.withertech.modernfurniture.ModBlocks;
import com.withertech.modernfurniture.blocks.BlockCouchTableEnd;
import com.withertech.modernfurniture.blocks.BlockCouchTableMiddle;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteCorner;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteEndLeft;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteEndRight;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteExtension;
import com.withertech.modernfurniture.blocks.BlockLCouchWhiteMiddle;
import com.withertech.modernfurniture.blocks.BlockTVStandEndLeft;
import com.withertech.modernfurniture.blocks.BlockTVStandEndRight;
import com.withertech.modernfurniture.blocks.BlockTVStandMiddle;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
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
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
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
    }
}
