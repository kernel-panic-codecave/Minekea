package com.withertech.minekea.jei;

import java.util.Collections;

import javax.annotation.Nonnull;

import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.blocks.ContainerKitchenCounterOven;
import com.withertech.minekea.blocks.ContainerKitchenWallOven;
import com.withertech.minekea.blocks.TileKitchenCounterOven;
import com.withertech.minekea.blocks.TileKitchenWallOven;
import com.withertech.minekea.customrecipes.ToasterRecipe;
import com.withertech.minekea.customrecipes.ToasterRecipeRegistry;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin
{
	
	public static final String TOASTER_ID = "Minekea.BlockKitchenToaster";
	
	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		
		registerCounterOvenHandling(registry);
		registerWallOvenHandling(registry);
		registerToasterHandling(registry);
	}
	
	private void registerToasterHandling(@Nonnull IModRegistry registry)
	{
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockKitchenToaster), TOASTER_ID);
		
		registry.addRecipes(ToasterRecipeRegistry.getCustomRecipeList(), TOASTER_ID);
		registry.handleRecipes(ToasterRecipe.class, ToasterRecipeWrapper::new, TOASTER_ID);
		
	}
	
	private void registerCounterOvenHandling(@Nonnull IModRegistry registry)
	{
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockKitchenCounterOven), VanillaRecipeCategoryUid.SMELTING);
		
		IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
		transferRegistry.addRecipeTransferHandler(ContainerKitchenCounterOven.class, VanillaRecipeCategoryUid.SMELTING, 0, TileKitchenCounterOven.INPUT_SLOTS, TileKitchenCounterOven.INPUT_SLOTS + TileKitchenCounterOven.OUTPUT_SLOTS, 36);
	}
	
	private void registerWallOvenHandling(@Nonnull IModRegistry registry)
	{
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.blockKitchenWallOven), VanillaRecipeCategoryUid.SMELTING);
		
		IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
		transferRegistry.addRecipeTransferHandler(ContainerKitchenWallOven.class, VanillaRecipeCategoryUid.SMELTING, 0, TileKitchenWallOven.INPUT_SLOTS, TileKitchenWallOven.INPUT_SLOTS + TileKitchenWallOven.OUTPUT_SLOTS, 36);
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IJeiHelpers helpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new ToasterRecipeCatagory(guiHelper));
	}
	
}