package com.withertech.minekea.customrecipes;

import net.minecraft.item.ItemStack;

public class ToasterRecipe
{
	
	private final ItemStack input;
	private final ItemStack output;
	
	public ToasterRecipe(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}
	
	public ItemStack getInput()
	{
		return input;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
}
