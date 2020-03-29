package com.withertech.modernfurniture;

import org.apache.logging.log4j.Logger;

import com.withertech.modernfurniture.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModernFurniture.MODID, version = ModernFurniture.VERSION)
public class ModernFurniture
{
    public static final String MODID = "modernfurniture";
    public static final String VERSION = "1.0";
    
    @SidedProxy(clientSide = "com.withertech.modernfurniture.proxy.ClientProxy", serverSide = "com.withertech.modernfurniture.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance
    public static ModernFurniture instance;
    
    public static Logger logger;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
