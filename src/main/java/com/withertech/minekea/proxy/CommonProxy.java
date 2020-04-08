package com.withertech.minekea.proxy;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.blocks.*;
import com.withertech.minekea.handler.GuiHandler;
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

@Mod.EventBusSubscriber
public class CommonProxy {
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
              return new ItemStack(Blocks.CONCRETE);
            }
          };
        MinekeaDenTab = new CreativeTabs("minekeadentab") 
        {
            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() 
            {
              return new ItemStack(Blocks.CONCRETE);
            }
          };
        MinekeaKitchenTab = new CreativeTabs("minekeakitchentab") 
        {
            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() 
            {
              return new ItemStack(Blocks.CONCRETE);
            }
          };
    }

    public void init(FMLInitializationEvent e) 
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Minekea.instance, new GuiHandler());
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
        
        event.getRegistry().register(new BlockFurnitureStation());
        
        event.getRegistry().register(new BlockTableLamp(false).setRegistryName("blocktablelamp").setCreativeTab(CommonProxy.MinekeaDenTab));
        event.getRegistry().register(new BlockTableLamp(true).setRegistryName("blocktablelamp_on"));
        
        event.getRegistry().register(new BlockTallLampLight(false).setRegistryName("blocktalllamplight").setCreativeTab(CommonProxy.MinekeaDenTab));
        event.getRegistry().register(new BlockTallLampLight(true).setRegistryName("blocktalllamplight_on"));
        event.getRegistry().register(new BlockTallLampBase());
        
        event.getRegistry().register(new BlockArmchairWhite());
        
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

        event.getRegistry().register(new BlockKitchenWallOven());
        GameRegistry.registerTileEntity(TileKitchenWallOven.class, Minekea.MODID + "_kitchenwalloven");
        
        event.getRegistry().register(new BlockKitchenChair());
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
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockFurnitureStation).setRegistryName(ModBlocks.blockFurnitureStation.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTableLamp).setRegistryName(ModBlocks.blockTableLamp.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTallLampLight).setRegistryName(ModBlocks.blockTallLampLight.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.blockTallLampBase).setRegistryName(ModBlocks.blockTallLampBase.getRegistryName()));
        
        event.getRegistry().register(new ItemBlock(ModBlocks.blockArmchairWhite).setRegistryName(ModBlocks.blockArmchairWhite.getRegistryName()));
        
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

        event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenWallOven).setRegistryName(ModBlocks.blockKitchenWallOven.getRegistryName()));

        event.getRegistry().register(new ItemBlock(ModBlocks.blockKitchenChair).setRegistryName(ModBlocks.blockKitchenChair.getRegistryName()));
    }
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("This should only be called from client side");
    }
}
