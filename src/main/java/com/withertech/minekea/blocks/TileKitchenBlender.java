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

public class TileKitchenBlender extends TileEntity implements ITickable
{
	public static final int INPUT_SLOTS = 3;
	public static final int OUTPUT_SLOTS = 1;
	public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;
	
	public static final int MAX_PROGRESS = 260;
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
					attemptBlend();
					
				}
				markDirty();
			} else
			{
				startBlend();
			}
		}

		if (world != null)
		{

			if (asm.currentState().equals("on") && (inputHandler.getStackInSlot(0).isEmpty() && inputHandler.getStackInSlot(1).isEmpty() && inputHandler.getStackInSlot(2).isEmpty()))
			{
				asm.transition("off");
			}
			if (asm.currentState().equals("off") && (!inputHandler.getStackInSlot(0).isEmpty() && !inputHandler.getStackInSlot(1).isEmpty() && !inputHandler.getStackInSlot(2).isEmpty()))
			{
				asm.transition("on");
				if (!world.isRemote)
				{
					world.playSound(null, pos, ModSounds.SoundKitchenBlender, SoundCategory.BLOCKS, 10, 1);
				}
			}
	
		}
	}
	
	@Nullable
	private final IAnimationStateMachine asm;
	
	public TileKitchenBlender()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			asm = Minekea.proxy.load(new ResourceLocation(Minekea.MODID, "asms/block/blockkitchenblender.json"), ImmutableMap.of());
		} else
		{
			asm = null;
		}
	}
	
	private boolean insertOutput(ItemStack output, boolean simulate)
	{
		for (int i = 0; i < OUTPUT_SLOTS; i++)
		{
			ItemStack remaining = outputHandler.insertItem(i, output, simulate);
			if (remaining.isEmpty())
			{
				return true;
			}
		}
		return false;
	}
	
	private void startBlend()
	{
		if (!inputHandler.getStackInSlot(0).isEmpty() && !inputHandler.getStackInSlot(1).isEmpty() && !inputHandler.getStackInSlot(2).isEmpty())
		{
			progress = MAX_PROGRESS;
			markDirty();
		}
	}
	
	private void attemptBlend()
	{
		if (!inputHandler.getStackInSlot(0).isEmpty() && !inputHandler.getStackInSlot(1).isEmpty() && !inputHandler.getStackInSlot(2).isEmpty())
		{
			if (insertOutput(new ItemStack(ModItems.itemSmoothie), false))
			{
				ModItems.itemSmoothie.setNBT(outputHandler.getStackInSlot(0), inputHandler.getStackInSlot(0).getItem(), inputHandler.getStackInSlot(1).getItem(), inputHandler.getStackInSlot(2).getItem());
				inputHandler.extractItem(0, 1, false);
				inputHandler.extractItem(1, 1, false);
				inputHandler.extractItem(2, 1, false);
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
	
	private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS)
	{
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack)
		{
			return stack.getItem() instanceof ItemFood && !(stack.getItem() instanceof ItemSmoothie);
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
			TileKitchenBlender.this.markDirty();
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
			TileKitchenBlender.this.markDirty();
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
