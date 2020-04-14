package com.withertech.minekea.blocks;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class TESRKitchenToaster extends TileEntitySpecialRenderer<TileKitchenToaster>
{
	
	@Override
	public void render(TileKitchenToaster te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		
		// Translate to the location of our tile entity
		
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();
		
		// Render our item
		renderItem(te);
		
		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
	
	private void renderItem(TileKitchenToaster te)
	{
		ItemStack stack1 = te.getStack1();
		ItemStack stack2 = te.getStack2();
		World world = te.getWorld();
		IBlockState state = world.getBlockState(te.getPos());
		EnumFacing facing = state.getValue(BlockKitchenToaster.FACING);
		if (!stack1.isEmpty())
		{
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();
			switch (facing)
			{
				case EAST:
					GlStateManager.translate(.5, .5, .38);
					GlStateManager.rotate(180, 0, 1, 0);
					break;
				case NORTH:
					GlStateManager.translate(.38, .5, .5);
					GlStateManager.rotate(-90, 0, 1, 0);
					break;
				case SOUTH:
					GlStateManager.translate(.38, .5, .5);
					GlStateManager.rotate(90, 0, 1, 0);
					break;
				case WEST:
					GlStateManager.translate(.5, .5, .38);
					break;
				default:
					break;
				
			}
			
			GlStateManager.scale(.4f, .4f, .4f);
			
			Minecraft.getMinecraft().getRenderItem().renderItem(stack1, ItemCameraTransforms.TransformType.NONE);
			
			GlStateManager.popMatrix();
		}
		if (!stack2.isEmpty())
		{
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();
			switch (facing)
			{
				case EAST:
					GlStateManager.translate(.5, .5, .63);
					GlStateManager.rotate(180, 0, 1, 0);
					break;
				case NORTH:
					GlStateManager.translate(.63, .5, .5);
					GlStateManager.rotate(-90, 0, 1, 0);
					break;
				case SOUTH:
					GlStateManager.translate(.63, .5, .5);
					GlStateManager.rotate(90, 0, 1, 0);
					break;
				case WEST:
					GlStateManager.translate(.5, .5, .63);
					break;
				default:
					break;
				
			}
			GlStateManager.scale(.4f, .4f, .4f);
			
			Minecraft.getMinecraft().getRenderItem().renderItem(stack2, ItemCameraTransforms.TransformType.NONE);
			
			GlStateManager.popMatrix();
		}
	}
	
}