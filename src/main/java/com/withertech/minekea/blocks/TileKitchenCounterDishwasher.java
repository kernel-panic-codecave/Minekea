package com.withertech.minekea.blocks;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.util.EnergyStorageUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemCompass;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
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

public class TileKitchenCounterDishwasher extends TileEntity implements ITickable
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
		if (world != null && world.isRemote)
		{
			if (player.openContainer instanceof ContainerKitchenCounterDishwasher && asm.currentState().equals("closed"))
			{
				asm.transition("opening");
				
			} else if (!(player.openContainer instanceof ContainerKitchenCounterDishwasher) && asm.currentState().equals("open"))
			{
				asm.transition("closing");
			}
		}
		if (energyStorage.getEnergyStored() < RF_PER_TICK)
		{
			return;
		}
		
		for (int i = 0; i < 3; i++)
		{
			if (inputHandler.getStackInSlot(i).getItemDamage() > 0)
			{
				energyStorage.consumePower(RF_PER_TICK);
				if (inputHandler.getStackInSlot(i) != ItemStack.EMPTY)
				{
					if (inputHandler.getStackInSlot(i).getMaxDamage() - inputHandler.getStackInSlot(i).getItemDamage() != inputHandler.getStackInSlot(i).getMaxDamage())
					{
						inputHandler.getStackInSlot(i).setItemDamage(inputHandler.getStackInSlot(i).getItemDamage() - 1);
					}
					markDirty();
				}
			}
			if (inputHandler.getStackInSlot(i).getItemDamage() == 0)
			{
				if (insertOutput(inputHandler.getStackInSlot(i).copy(), false))
				{
					inputHandler.extractItem(i, 1, false);
				}
			}
			markDirty();
		}
	}
	
	@Nullable
	private final IAnimationStateMachine asm;
	
	public TileKitchenCounterDishwasher()
	{
		asm = Minekea.proxy.load(new ResourceLocation(Minekea.MODID, "asms/block/blockkitchencounterdishwasher.json"), ImmutableMap.of());
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
	
	private void attemptRepair()
	{
		for (int i = 0; i < INPUT_SLOTS; i++)
		{
			if (insertOutput(inputHandler.getStackInSlot(i).copy(), false))
			{
				inputHandler.extractItem(i, 1, false);
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
		public boolean isItemValid(int slot, ItemStack stack)
		{
			return (stack.getItem() instanceof ItemSword) || (stack.getItem() instanceof ItemPickaxe) || (stack.getItem() instanceof ItemAxe) || (stack.getItem() instanceof ItemSpade) || (stack.getItem() instanceof ItemHoe) || (stack.getItem() instanceof ItemBow) || (stack.getItem() instanceof ItemFishingRod) || (stack.getItem() instanceof ItemShears) || (stack.getItem() instanceof ItemShield) || (stack.getItem() instanceof ItemCarrotOnAStick);
		}
		
		@Override
		protected void onContentsChanged(int slot)
		{
			// We need to tell the tile entity that something has changed so
			// that the chest contents is persisted
			TileKitchenCounterDishwasher.this.markDirty();
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
			TileKitchenCounterDishwasher.this.markDirty();
		}
	};
	
	private CombinedInvWrapper combinedHandler = new CombinedInvWrapper(inputHandler, outputHandler);
	
	// -------------------------------------------------------------------------------------------------------------
	
	private EnergyStorageUtil energyStorage = new EnergyStorageUtil(MAX_POWER, RF_PER_TICK_INPUT);
	
	// -------------------------------------------------------------------------------------------------------------
	
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
		energyStorage.setEnergy(compound.getInteger("energy"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setTag("itemsIn", inputHandler.serializeNBT());
		compound.setTag("itemsOut", outputHandler.serializeNBT());
		compound.setInteger("progress", progress);
		compound.setInteger("energy", energyStorage.getEnergyStored());
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
