package com.withertech.minekea.jei;

import java.util.List;

import javax.annotation.Nonnull;

import com.withertech.minekea.Minekea;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ToasterRecipeCatagory implements IRecipeCategory<ToasterRecipeWrapper>
{
	
	private final IDrawable background;
	
	public ToasterRecipeCatagory(IGuiHelper guiHelper)
	{
		ResourceLocation location = new ResourceLocation(Minekea.MODID, "textures/gui/toaster.png");
		background = guiHelper.createDrawable(location, 3, 18, 170, 30);
	}
	
	@Nonnull
	@Override
	public String getUid()
	{
		return JeiPlugin.TOASTER_ID;
	}
	
	@Nonnull
	@Override
	public String getTitle()
	{
		return "Kitchen Toaster";
	}
	
	@Nonnull
	@Override
	public IDrawable getBackground()
	{
		return background;
	}
	
	@Override
	public void drawExtras(@Nonnull Minecraft minecraft)
	{
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ToasterRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		
		guiItemStacks.init(0, true, 24, 7);
		guiItemStacks.init(3, false, 132, 7);
		
		List<ItemStack> inputs = ingredients.getInputs(VanillaTypes.ITEM).get(0);
		List<ItemStack> outputs = ingredients.getOutputs(VanillaTypes.ITEM).get(0);
		
		guiItemStacks.set(0, inputs);
		guiItemStacks.set(3, outputs);
	}
	
	@Override
	public String getModName()
	{
		return Minekea.MODNAME;
	}
}