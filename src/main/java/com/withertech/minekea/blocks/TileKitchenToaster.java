package com.withertech.minekea.blocks;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModBlocks;
import com.withertech.minekea.ModItems;
import com.withertech.minekea.util.EnergyStorageUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
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

public class TileKitchenToaster extends TileEntity implements ITickable
{
	
	private ItemStack stack1 = ItemStack.EMPTY;
	private ItemStack stack2 = ItemStack.EMPTY;
	
	private int progress = 0;
	public static final int MAX_PROGRESS = 60;
	public boolean done = false;
	
	@Override
	public void update()
	{
		if (!world.isRemote)
		{
			
			if (progress > 0 && !done)
			{
				progress--;
				if (progress == 0)
				{
					attemptToast();
				} else
				{
					startToast();
				}
				markDirty();
			}
		}
	}
	
	private void startToast()
	{
		ItemStack result1 = getStack1();
		ItemStack result2 = getStack2();
		if (result1.isEmpty() && result2.isEmpty())
		{
			progress = MAX_PROGRESS;
			markDirty();
		}
	}
	
	private void attemptToast()
	{
		ItemStack ingredient1 = getStack1();
		ItemStack ingredient2 = getStack2();
		if (ingredient1.getItem().equals(Item.getItemById(297)) && ingredient2.getItem().equals(Items.BREAD) && done == false)
		{
			setStack1(new ItemStack(ModItems.itemCookedBread));
			setStack2(new ItemStack(ModItems.itemCookedBread));
			setDone(true);
		}
		if (ingredient1.getItem().equals(ModItems.itemCookedBread) && ingredient2.getItem().equals(ModItems.itemCookedBread) && done == false)
		{
			setStack1(new ItemStack(ModItems.itemWhyWouldYouDoThis));
			setStack2(new ItemStack(ModItems.itemWhyWouldYouDoThis));
			setDone(true);
		}
		if (ingredient1.getItem().equals(ModItems.itemWhyWouldYouDoThis) && ingredient2.getItem().equals(ModItems.itemWhyWouldYouDoThis) && done == false)
		{
			setStack1(new ItemStack(ModItems.itemPleaseStop));
			setStack2(new ItemStack(ModItems.itemPleaseStop));
			setDone(true);
		}
		if (ingredient1.getItem().equals(ModItems.itemPleaseStop) && ingredient2.getItem().equals(ModItems.itemPleaseStop) && done == false)
		{
			setStack1(new ItemStack(ModItems.itemNowYaDoneIt));
			setStack2(new ItemStack(ModItems.itemNowYaDoneIt));
			setDone(true);
		}
		progress = MAX_PROGRESS;
	}
	
	public ItemStack getStack1()
	{
		return stack1;
	}
	
	public void setStack1(ItemStack stack)
	{
		this.stack1 = stack;
		markDirty();
		if (world != null)
		{
			IBlockState state = world.getBlockState(getPos());
			world.notifyBlockUpdate(getPos(), state, state, 3);
		}
	}
	
	public ItemStack getStack2()
	{
		return stack2;
	}
	
	public void setStack2(ItemStack stack)
	{
		this.stack2 = stack;
		markDirty();
		if (world != null)
		{
			IBlockState state = world.getBlockState(getPos());
			world.notifyBlockUpdate(getPos(), state, state, 3);
		}
	}
	
	public boolean isDone()
	{
		return done;
	}
	
	public void setDone(boolean done)
	{
		this.done = done;
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		// getUpdateTag() is called whenever the chunkdata is sent to the
		// client. In contrast getUpdatePacket() is called when the tile entity
		// itself wants to sync to the client. In many cases you want to send
		// over the same information in getUpdateTag() as in getUpdatePacket().
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		// Prepare a packet for syncing our TE to the client. Since we only have to sync
		// the stack
		// and that's all we have we just write our entire NBT here. If you have a
		// complex
		// tile entity that doesn't need to have all information on the client you can
		// write
		// a more optimal NBT here.
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
	{
		// Here we get the packet from the server and read it into our client side tile
		// entity
		this.readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("item1"))
		{
			stack1 = new ItemStack(compound.getCompoundTag("item1"));
		} else
		{
			stack1 = ItemStack.EMPTY;
		}
		if (compound.hasKey("item2"))
		{
			stack2 = new ItemStack(compound.getCompoundTag("item2"));
		} else
		{
			stack2 = ItemStack.EMPTY;
		}
		progress = compound.getInteger("progress");
		done = compound.getBoolean("done");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		NBTTagCompound tagCompound = new NBTTagCompound();
		stack1.writeToNBT(tagCompound);
		compound.setTag("item1", tagCompound);
		stack2.writeToNBT(tagCompound);
		compound.setTag("item2", tagCompound);
		compound.setBoolean("done", done);
		compound.setInteger("progress", progress);
		return compound;
	}
}
