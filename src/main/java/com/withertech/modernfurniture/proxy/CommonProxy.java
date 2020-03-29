package com.withertech.modernfurniture.proxy;

import com.withertech.modernfurniture.ModBlocks;
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
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndLeft).setRegistryName(ModBlocks.blockTVStandEndLeft.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandEndRight).setRegistryName(ModBlocks.blockTVStandEndRight.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTVStandMiddle).setRegistryName(ModBlocks.blockTVStandMiddle.getRegistryName()));
    }
}
