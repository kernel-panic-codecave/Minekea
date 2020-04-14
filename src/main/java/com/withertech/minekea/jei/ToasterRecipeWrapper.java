package com.withertech.minekea.jei;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.withertech.minekea.customrecipes.ToasterRecipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

class ToasterRecipeWrapper implements ICraftingRecipeWrapper
{
	
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;
	
	public ToasterRecipeWrapper(ToasterRecipe recipe)
	{
		this.inputs = Collections.singletonList(Collections.singletonList(recipe.getInput().copy()));
		this.output = recipe.getOutput().copy();
	}
	
	@Override
	public void getIngredients(@Nonnull IIngredients ingredients)
	{
		ingredients.setOutput(VanillaTypes.ITEM, output);
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
	}
	
	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY)
	{
		return Collections.emptyList();
	}
}
