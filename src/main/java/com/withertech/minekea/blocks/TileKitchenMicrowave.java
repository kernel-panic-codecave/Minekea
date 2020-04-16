package com.withertech.minekea.blocks;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.util.EnergyStorageUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileKitchenMicrowave extends TileEntity implements ITickable
{
	public static final int INPUT_SLOTS = 1;
	public static final int OUTPUT_SLOTS = 1;
	public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;
	
	private ItemStack stack = ItemStack.EMPTY;

	public static final int MAX_PROGRESS = 40;
	
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
					attemptSmelt();
				}
				markDirty();
			} else
			{
				startSmelt();
			}
		}
		if (world != null && world.isRemote)
		{
			if (player.openContainer instanceof ContainerKitchenMicrowave && asm.currentState().equals("closed"))
			{
				asm.transition("opening");
				
			} else if (!(player.openContainer instanceof ContainerKitchenMicrowave) && asm.currentState().equals("open"))
			{
				asm.transition("closing");
			}
		}
	}
	
	@Nullable
	private final IAnimationStateMachine asm;
	
	public TileKitchenMicrowave()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			asm = Minekea.proxy.load(new ResourceLocation(Minekea.MODID, "asms/block/blockkitchenmicrowave.json"), ImmutableMap.of());
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
	
	private void startSmelt()
	{
		for (int i = 0; i < INPUT_SLOTS; i++)
		{
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult((inputHandler.getStackInSlot(i).getItem() instanceof ItemFood) ? inputHandler.getStackInSlot(i) : ItemStack.EMPTY);
			if (!result.isEmpty())
			{
				if (insertOutput(result.copy(), true))
				{
					progress = MAX_PROGRESS;
					markDirty();
				}
				break;
			}
		}
	}
	
	private void attemptSmelt()
	{
		for (int i = 0; i < INPUT_SLOTS; i++)
		{
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult((inputHandler.getStackInSlot(i).getItem() instanceof ItemFood) ? inputHandler.getStackInSlot(i) : ItemStack.EMPTY);
			if (!result.isEmpty())
			{
				if (insertOutput(result.copy(), false))
				{
					inputHandler.extractItem(i, 1, false);
				}
			}
		}
	}
	
	public ItemStack getStack()
	{
		return stack;
	}
	
	public void setStack()
	{
		if (world.isRemote)
		{
			if (!inputHandler.getStackInSlot(0).isEmpty())
			{
				this.stack = inputHandler.getStackInSlot(0);
			} else if (!outputHandler.getStackInSlot(0).isEmpty())
			{
				this.stack = outputHandler.getStackInSlot(0);
			} else
			{
				this.stack = ItemStack.EMPTY;
			}
			markDirty();
			if (world != null)
			{
				IBlockState state = world.getBlockState(getPos());
				world.notifyBlockUpdate(getPos(), state, state, 3);
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
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult((stack.getItem() instanceof ItemFood) ? stack : ItemStack.EMPTY);
			return !result.isEmpty();
		}
		
		@Override
		protected void onContentsChanged(int slot)
		{
			// We need to tell the tile entity that something has changed so
			// that the chest contents is persisted
			TileKitchenMicrowave.this.markDirty();
			setStack();
			if (world != null)
			{
				IBlockState state = world.getBlockState(getPos());
				world.notifyBlockUpdate(getPos(), state, state, 3);
			}
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
			TileKitchenMicrowave.this.markDirty();
			setStack();
			if (world != null)
			{
				IBlockState state = world.getBlockState(getPos());
				world.notifyBlockUpdate(getPos(), state, state, 3);
			}
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
		if (compound.hasKey("stack"))
		{
			stack.deserializeNBT((NBTTagCompound) compound.getTag("stack"));
		}
		progress = compound.getInteger("progress");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("itemsIn", inputHandler.serializeNBT());
		compound.setTag("itemsOut", outputHandler.serializeNBT());
		compound.setTag("stack", stack.serializeNBT());
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
