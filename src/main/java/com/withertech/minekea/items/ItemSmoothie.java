package com.withertech.minekea.items;

import java.util.List;

import com.withertech.minekea.Minekea;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSmoothie extends ItemFood
{
	public int ingredient1food;
	public int ingredient2food;
	public int ingredient3food;
	public float ingredient1sat;
	public float ingredient2sat;
	public float ingredient3sat;
	
	public ItemSmoothie()
	{
		super(1, 1.0f, false);
		setRegistryName("itemsmoothie");
		setUnlocalizedName(Minekea.MODID + ".itemsmoothie");
		setCreativeTab(null);
		maxStackSize = 1;
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	public ItemStack setNBT(ItemStack stack, Item ingredient1, Item ingredient2, Item ingredient3)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
		} else
		{
			nbt = new NBTTagCompound();
		}
		
		if (!nbt.hasKey("ingredient1"))
		{
			nbt.setString("ingredient1", ingredient1.getRegistryName().toString());
		}
		if (!nbt.hasKey("ingredient2"))
		{
			nbt.setString("ingredient2", ingredient2.getRegistryName().toString());
		}
		if (!nbt.hasKey("ingredient3"))
		{
			nbt.setString("ingredient3", ingredient3.getRegistryName().toString());
		}
		stack.setTagCompound(nbt);
		
		return stack;
	}
	
	public ItemStack getIngredients(ItemStack stack, int index)
	{
		
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
			ItemStack ingredient1 = ItemStack.EMPTY;
			ItemStack ingredient2 = ItemStack.EMPTY;
			ItemStack ingredient3 = ItemStack.EMPTY;
			ingredient1 = new ItemStack(ingredient1.getItem().getByNameOrId(nbt.getString("ingredient1")));
			ingredient2 = new ItemStack(ingredient2.getItem().getByNameOrId(nbt.getString("ingredient2")));
			ingredient3 = new ItemStack(ingredient3.getItem().getByNameOrId(nbt.getString("ingredient3")));
			switch (index)
			{
				case 0:
					return ingredient1;
				case 1:
					return ingredient2;
				case 2:
					return ingredient3;
				default:
					break;
			}
		}
		return stack;
	}
	
	@Override
	public int getHealAmount(ItemStack stack)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
			ItemStack ingredient1 = ItemStack.EMPTY;
			ItemStack ingredient2 = ItemStack.EMPTY;
			ItemStack ingredient3 = ItemStack.EMPTY;
			ingredient1 = new ItemStack(ingredient1.getItem().getByNameOrId(nbt.getString("ingredient1")));
			ingredient2 = new ItemStack(ingredient2.getItem().getByNameOrId(nbt.getString("ingredient2")));
			ingredient3 = new ItemStack(ingredient3.getItem().getByNameOrId(nbt.getString("ingredient3")));
			ingredient1food = ((ItemFood) ingredient1.getItem()).getHealAmount(ingredient1);
			ingredient2food = ((ItemFood) ingredient2.getItem()).getHealAmount(ingredient2);
			ingredient3food = ((ItemFood) ingredient3.getItem()).getHealAmount(ingredient3);
		}
		return ingredient1food + ingredient2food + ingredient3food;
	}
	
	@Override
	public float getSaturationModifier(ItemStack stack)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
			ItemStack ingredient1 = ItemStack.EMPTY;
			ItemStack ingredient2 = ItemStack.EMPTY;
			ItemStack ingredient3 = ItemStack.EMPTY;
			ingredient1 = new ItemStack(ingredient1.getItem().getByNameOrId(nbt.getString("ingredient1")));
			ingredient2 = new ItemStack(ingredient2.getItem().getByNameOrId(nbt.getString("ingredient2")));
			ingredient3 = new ItemStack(ingredient3.getItem().getByNameOrId(nbt.getString("ingredient3")));
			ingredient1sat = ((ItemFood) ingredient1.getItem()).getSaturationModifier(ingredient1);
			ingredient2sat = ((ItemFood) ingredient2.getItem()).getSaturationModifier(ingredient2);
			ingredient3sat = ((ItemFood) ingredient3.getItem()).getSaturationModifier(ingredient3);
		}
		return ingredient1sat + ingredient2sat + ingredient3sat;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flags)
	{
		NBTTagCompound nbt;
		if (stack.hasTagCompound())
		{
			nbt = stack.getTagCompound();
			ItemStack ingredient1 = ItemStack.EMPTY;
			ItemStack ingredient2 = ItemStack.EMPTY;
			ItemStack ingredient3 = ItemStack.EMPTY;
			list.add(new ItemStack(ingredient1.getItem().getByNameOrId(nbt.getString("ingredient1"))).getDisplayName());
			list.add(new ItemStack(ingredient2.getItem().getByNameOrId(nbt.getString("ingredient2"))).getDisplayName());
			list.add(new ItemStack(ingredient3.getItem().getByNameOrId(nbt.getString("ingredient3"))).getDisplayName());
		}
	}
}
