package com.withertech.minekea.items;

import java.util.List;

import com.withertech.minekea.Minekea;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPleaseStop extends ItemFood
{
	
	public ItemPleaseStop()
	{
		super(-10, 0.0f, false);
		setRegistryName("itempleasestop");
		setUnlocalizedName(Minekea.MODID + ".itempleasestop");
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flags)
	{
		list.add("Don't burn the house down!");
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		super.onFoodEaten(stack, worldIn, player);
		player.setFire(20);
	}
	
}
