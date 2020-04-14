package com.withertech.minekea.blocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.util.EnergyStorageUtil;

import net.minecraft.entity.player.EntityPlayer;
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

public class TileKitchenWallOven extends TileEntity implements ITickable
{
	public static final int INPUT_SLOTS = 3;
	public static final int OUTPUT_SLOTS = 3;
	public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;
	
	public static final int MAX_PROGRESS = 40;
	public static final int MAX_POWER = 100000;
	public static final int RF_PER_TICK = 20;
	public static final int RF_PER_TICK_INPUT = 100;
	
	private int progress = 0;
	
	private int clientProgress = -1;
	private int clientEnergy = -1;
	
	private EntityPlayer player = Minekea.proxy.getClientPlayer();
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			if (energyStorage.getEnergyStored() < RF_PER_TICK)
			{
				return;
			}
			
			if (progress > 0)
			{
				energyStorage.consumePower(RF_PER_TICK);
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
			if (player.openContainer instanceof ContainerKitchenWallOven && asm.currentState().equals("closed"))
			{
				asm.transition("opening");
				
			} else if (!(player.openContainer instanceof ContainerKitchenWallOven) && asm.currentState().equals("open"))
			{
				asm.transition("closing");
			}
		}
	}
	
	@Nullable
	private final IAnimationStateMachine asm;
	
	public TileKitchenWallOven()
	{
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			asm = Minekea.proxy.load(new ResourceLocation(Minekea.MODID, "asms/block/blockkitchenwalloven.json"), ImmutableMap.of());
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
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputHandler.getStackInSlot(i));
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
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult(inputHandler.getStackInSlot(i));
			if (!result.isEmpty())
			{
				if (insertOutput(result.copy(), false))
				{
					inputHandler.extractItem(i, 1, false);
				}
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
	
	public int getClientEnergy()
	{
		return clientEnergy;
	}
	
	public int getEnergy()
	{
		return energyStorage.getEnergyStored();
	}
	
	public void setClientEnergy(int clientEnergy)
	{
		this.clientEnergy = clientEnergy;
	}
	
	private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS)
	{
		@Override
		public boolean isItemValid(int slot, @Nonnull ItemStack stack)
		{
			ItemStack result = FurnaceRecipes.instance().getSmeltingResult(stack);
			return !result.isEmpty();
		}
		
		@Override
		protected void onContentsChanged(int slot)
		{
			// We need to tell the tile entity that something has changed so
			// that the chest contents is persisted
			TileKitchenWallOven.this.markDirty();
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
			TileKitchenWallOven.this.markDirty();
		}
	};
	
	private CombinedInvWrapper combinedHandler = new CombinedInvWrapper(inputHandler, outputHandler);
	
//-------------------------------------------------------------------------------------------------------------
	
	private EnergyStorageUtil energyStorage = new EnergyStorageUtil(MAX_POWER, RF_PER_TICK_INPUT);
	
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
		if (capability == CapabilityEnergy.ENERGY)
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
		if (capability == CapabilityEnergy.ENERGY)
		{
			return CapabilityEnergy.ENERGY.cast(energyStorage);
		}
		return super.getCapability(capability, facing);
	}
}
