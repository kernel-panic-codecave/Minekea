package com.withertech.minekea.customrecipes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.withertech.minekea.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ToasterRecipeRegistry
{
	private static boolean isInit = false;
	private static List<ToasterRecipe> customRecipeList = new ArrayList<>();
	
	public static List<ToasterRecipe> getCustomRecipeList()
	{
		if (!isInit)
		{
			init();
			isInit = true;
		}
		return customRecipeList;
	}
	
	@Nullable
	public static ToasterRecipe getRecipe(ItemStack input)
	{
		for (ToasterRecipe recipe : getCustomRecipeList())
		{
			if (ItemStack.areItemsEqual(input, recipe.getInput()))
			{
				return recipe;
			}
		}
		return null;
	}
	
	private static void init()
	{
		customRecipeList.add(new ToasterRecipe(new ItemStack(Items.BREAD, 2), new ItemStack(ModItems.itemCookedBread, 2)));
		customRecipeList.add(new ToasterRecipe(new ItemStack(ModItems.itemCookedBread, 2), new ItemStack(ModItems.itemWhyWouldYouDoThis, 2)));
		customRecipeList.add(new ToasterRecipe(new ItemStack(ModItems.itemWhyWouldYouDoThis, 2), new ItemStack(ModItems.itemPleaseStop, 2)));
		customRecipeList.add(new ToasterRecipe(new ItemStack(ModItems.itemPleaseStop, 2), new ItemStack(ModItems.itemNowYaDoneIt, 2)));
	}
}
