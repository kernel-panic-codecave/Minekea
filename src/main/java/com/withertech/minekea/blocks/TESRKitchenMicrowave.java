package com.withertech.minekea.blocks;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.Animation;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.common.animation.Event;
import net.minecraftforge.common.animation.IEventHandler;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.Properties;

public class TESRKitchenMicrowave<T extends TileKitchenMicrowave> extends FasterTESR<T> implements IEventHandler<T>
{
    protected static BlockRendererDispatcher blockRenderer;
    
    @Override
    public void render(T te, double x, double y, double z, float partialTicks, int destroyStage, float partial)
    {
    	super.render(te, x, y, z, partialTicks, destroyStage, partial);
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
    
    @Override
    public void renderTileEntityFast(T te, double x, double y, double z, float partialTick, int breakStage, float partial, BufferBuilder renderer)
   {
        if(!te.hasCapability(CapabilityAnimation.ANIMATION_CAPABILITY, null))
        {
            return;
        }
        if(blockRenderer == null) blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
        BlockPos pos = te.getPos();
        IBlockAccess world = MinecraftForgeClient.getRegionRenderCache(te.getWorld(), pos);
        IBlockState state = world.getBlockState(pos);
        if(state.getPropertyKeys().contains(Properties.StaticProperty))
        {
            state = state.withProperty(Properties.StaticProperty, false);
        }
        if(state instanceof IExtendedBlockState)
        {
            IExtendedBlockState exState = (IExtendedBlockState)state;
            if(exState.getUnlistedNames().contains(Properties.AnimationProperty))
            {
                float time = Animation.getWorldTime(getWorld(), partialTick);
                IAnimationStateMachine capability = te.getCapability(CapabilityAnimation.ANIMATION_CAPABILITY, null);
                if (capability != null)
                {
                    Pair<IModelState, Iterable<Event>> pair = capability.apply(time);
                    handleEvents(te, time, pair.getRight());

                    // TODO: caching?
                    IBakedModel model = blockRenderer.getBlockModelShapes().getModelForState(exState.getClean());
                    exState = exState.withProperty(Properties.AnimationProperty, pair.getLeft());

                    renderer.setTranslation(x - pos.getX(), y - pos.getY(), z - pos.getZ());

                    blockRenderer.getBlockModelRenderer().renderModel(world, model, exState, pos, renderer, false);
                }
            }
        }
    }

    @Override
    public void handleEvents(T te, float time, Iterable<Event> pastEvents) {}
	private void renderItem(TileKitchenMicrowave te)
	{
		ItemStack stack = te.getStack();
		World world = te.getWorld();
		IBlockState state = world.getBlockState(te.getPos());
		EnumFacing facing = state.getValue(BlockKitchenMicrowave.FACING);
		if (!stack.isEmpty())
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
			
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
			
			GlStateManager.popMatrix();
		}
	}
}
