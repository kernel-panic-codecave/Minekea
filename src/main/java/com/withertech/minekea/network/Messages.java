package com.withertech.minekea.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class Messages 
{
	public static SimpleNetworkWrapper INSTANCE;
	
	private static int ID = 0;
	private static int nextID() {return ID++;}
	
	public static void registerMessages(String channelName)
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		
		//Server Side
		
		//Client Side
		INSTANCE.registerMessage(PacketSyncPower.Handler.class, PacketSyncPower.class, nextID(), Side.CLIENT);
	}
}
