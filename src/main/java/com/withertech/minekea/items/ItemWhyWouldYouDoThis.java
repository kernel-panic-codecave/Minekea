package com.withertech.minekea.items;

import java.util.List;

import com.withertech.minekea.Minekea;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWhyWouldYouDoThis extends ItemFood
{
	
	public ItemWhyWouldYouDoThis()
	{
		super(-10, 0.0f, false);
		setRegistryName("itemwhywouldyoudothis");
		setUnlocalizedName(Minekea.MODID + ".itemwhywouldyoudothis");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flags)
	{
		list.add("You are a bad cook!");
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
}
