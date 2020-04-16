package com.withertech.minekea.blocks;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.ModItems;
import com.withertech.minekea.ModSounds;
import com.withertech.minekea.items.ItemSmoothie;
import com.withertech.minekea.util.EnergyStorageUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileKitchenFoodProcessor extends TileEntity implements ITickable
{
	public static final int INPUT_SLOTS = 1;
	public static final int OUTPUT_SLOTS = 3;
	public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;
	public static boolean done = false;
	public static final int MAX_PROGRESS = 180;
	private int progress = 0;
	
	private int clientProgress = -1;
	
	private EntityPlayer player = Minekea.proxy.getClientPlayer();
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			if (progress > 0)
			{
				progress--;
				if (progress == 0)
				{
					attemptProcess();
					
				}
				markDirty();
			} else
			{
				startProcess();
			}
		}

		if (world != null)
		{

			if (asm.currentState().equals("on") && inputHandler.getStackInSlot(0).isEmpty())
			{
				asm.transition("off");
				System.out.println(world.isRemote);
			}
			if (asm.currentState().equals("off") && !inputHandler.getStackInSlot(0).isEmpty())
			{
				asm.transition("on");
				System.out.println(world.isRemote);
				 if (!world.isRemote) { world.playSound(null, pos, ModSounds.SoundKitchenFoodProcessor, SoundCategory.BLOCKS, 10, 1);}
																																			 
			}
	
		}
	}
	
	@Nullable
	private final IAnimationStateMachine asm;
	
	public TileKitchenFoodProcessor()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			asm = Minekea.proxy.load(new ResourceLocation(Minekea.MODID, "asms/block/blockkitchenfoodprocessor.json"), ImmutableMap.of());
		} else
		{
			asm = null;
		}
	}
	
	private boolean insertOutput(ItemStack output, boolean simulate, int slot)
	{
			ItemStack remaining = outputHandler.insertItem(slot, output, simulate);
			if (remaining.isEmpty())
			{
				return true;
			}
		return false;
	}
	
	private void startProcess()
	{
		if (!inputHandler.getStackInSlot(0).isEmpty())
		{
			progress = MAX_PROGRESS;
			markDirty();
		}
	}
	
	private void attemptProcess()
	{
		if (!inputHandler.getStackInSlot(0).isEmpty())
		{
			if (insertOutput(ModItems.itemSmoothie.getIngredients(inputHandler.getStackInSlot(0).copy(), 0).copy(), false, 0) && insertOutput(ModItems.itemSmoothie.getIngredients(inputHandler.getStackInSlot(0).copy(), 1).copy(), false, 1) && insertOutput(ModItems.itemSmoothie.getIngredients(inputHandler.getStackInSlot(0).copy(), 2).copy(), false, 2))
			{
				inputHandler.extractItem(0, 1, false);
			}
		}
	}
	
	public int getProgress()
	{
		return progress;
	}
	
	public void setProgress(int progress)
	{
		this.progress = progress;
	}
	
	public int getClientProgress()
	{
		return clientProgress;
	}
	
	public void setClientProgress(int clientProgress)
	{
		this.clientProgress = clientProgress;
	}
	
	public boolean isDone()
	{
		return done;
	}
	
	public void setDone(boolean done)
	{
		this.done = done;
	}
	
	private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS)
	{
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack)
		{
			return stack.getItem() instanceof ItemSmoothie;
		}
		
		@Override
		public int getSlotLimit(int slot)
		{
			// TODO Auto-generated method stub
			return 1;
		}
		
		@Override
		protected void onContentsChanged(int slot)
		{
			// We need to tell the tile entity that something has changed so
			// that the chest contents is persisted
			TileKitchenFoodProcessor.this.markDirty();
		}
	};
	
	private ItemStackHandler outputHandler = new ItemStackHandler(OUTPUT_SLOTS)
	{
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack)
		{
			return false;
		}
		
		@Override
		protected void onContentsChanged(int slot)
		{
			// We need to tell the tile entity that something has changed so
			// that the chest contents is persisted
			TileKitchenFoodProcessor.this.markDirty();
		}
	};
	
	private CombinedInvWrapper combinedHandler = new CombinedInvWrapper(inputHandler, outputHandler);
	
//-------------------------------------------------------------------------------------------------------------
	
//-------------------------------------------------------------------------------------------------------------
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("itemsIn"))
		{
			inputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsIn"));
		}
		if (compound.hasKey("itemsOut"))
		{
			outputHandler.deserializeNBT((NBTTagCompound) compound.getTag("itemsOut"));
		}
		progress = compound.getInteger("progress");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("itemsIn", inputHandler.serializeNBT());
		compound.setTag("itemsOut", outputHandler.serializeNBT());
		compound.setInteger("progress", progress);
		return compound;
	}
	
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		// If we are too far away from this tile entity you cannot use it
		return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY)
		{
			return true;
		}
		return super.hasCapability(capability, facing);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			if (facing == null)
			{
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(combinedHandler);
			} else if (facing == EnumFacing.UP)
			{
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inputHandler);
			} else
			{
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(outputHandler);
			}
		}
		if (capability == CapabilityAnimation.ANIMATION_CAPABILITY)
		{
			return CapabilityAnimation.ANIMATION_CAPABILITY.cast(asm);
		}
		return super.getCapability(capability, facing);
	}
}
