package com.withertech.minekea;

import com.withertech.minekea.items.*;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
	
	@GameRegistry.ObjectHolder("minekea:itemcookedbread")
	public static ItemCookedBread itemCookedBread;
	
	@GameRegistry.ObjectHolder("minekea:itemwhywouldyoudothis")
	public static ItemWhyWouldYouDoThis itemWhyWouldYouDoThis;
	
	@GameRegistry.ObjectHolder("minekea:itempleasestop")
	public static ItemPleaseStop itemPleaseStop;
	
	@GameRegistry.ObjectHolder("minekea:itemnowyadoneit")
	public static ItemNowYaDoneIt itemNowYaDoneIt;
	
	@GameRegistry.ObjectHolder("minekea:itemsmoothie")
	public static ItemSmoothie itemSmoothie;
	
	@SideOnly(Side.CLIENT)
	public static void initModels()
	{
		itemCookedBread.initModel();
		itemWhyWouldYouDoThis.initModel();
		itemPleaseStop.initModel();
		itemNowYaDoneIt.initModel();
		itemSmoothie.initModel();
	}
}
