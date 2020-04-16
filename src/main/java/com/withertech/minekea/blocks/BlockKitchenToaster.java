package com.withertech.minekea.blocks;

import java.util.List;

import com.withertech.minekea.Minekea;
import com.withertech.minekea.ModItems;
import com.withertech.minekea.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKitchenToaster extends Block implements ITileEntityProvider
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	private static final AxisAlignedBB BASE_AABB_NORTH = new AxisAlignedBB((3 * 0.0625), (0 * 0.0625), (2 * 0.0625), (13 * 0.0625), (8 * 0.0625), (15 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_SOUTH = new AxisAlignedBB((3 * 0.0625), (0 * 0.0625), (1 * 0.0625), (13 * 0.0625), (8 * 0.0625), (14 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_EAST = new AxisAlignedBB((1 * 0.0625), (0 * 0.0625), (3 * 0.0625), (14 * 0.0625), (8 * 0.0625), (13 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_WEST = new AxisAlignedBB((2 * 0.0625), (0 * 0.0625), (3 * 0.0625), (15 * 0.0625), (8 * 0.0625), (13 * 0.0625));
	
	public BlockKitchenToaster()
	{
		super(Material.ROCK);
		setUnlocalizedName(Minekea.MODID + ".blockkitchentoaster");
		setRegistryName("blockkitchentoaster");
		this.setCreativeTab(CommonProxy.MinekeaKitchenTab);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex();
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
		// Bind our TESR to our tile entity
		ClientRegistry.bindTileEntitySpecialRenderer(TileKitchenToaster.class, new TESRKitchenToaster());
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileKitchenToaster();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return false;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState blockState)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState blockState)
	{
		return false;
	}
	
	@Override
	public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	private TileKitchenToaster getTE(World world, BlockPos pos)
	{
		return (TileKitchenToaster) world.getTileEntity(pos);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileKitchenToaster te = getTE(world, pos);
		if (!te.getStack1().isEmpty() && !te.getStack2().isEmpty())
		{
			world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, 1.0f, 2.0f, true);
			
		}
		if (!world.isRemote)
		{
			if (te.getStack1().isEmpty() && te.getStack2().isEmpty())
			{
				if (player.getHeldItem(hand).getCount() >= 2 && (player.getHeldItem(hand).getItem().equals(Items.BREAD) || player.getHeldItem(hand).getItem().equals(ModItems.itemCookedBread) || player.getHeldItem(hand).getItem().equals(ModItems.itemWhyWouldYouDoThis) || player.getHeldItem(hand).getItem().equals(ModItems.itemPleaseStop)))
				{
					// There is no item in the pedestal and the player is holding an item. We move
					// that item
					// to the pedestal
					te.setStack1(player.getHeldItem(hand).splitStack(1));
					te.setStack2(player.getHeldItem(hand).splitStack(1));
					// Make sure the client knows about the changes in the player inventory
					player.openContainer.detectAndSendChanges();
				}
			} else
			{
				// There is a stack in the pedestal. In this case we remove it and try to put it
				// in the
				// players inventory if there is room
				ItemStack stack1 = te.getStack1();
				ItemStack stack2 = te.getStack2();
				te.setStack1(ItemStack.EMPTY);
				if (!player.inventory.addItemStackToInventory(stack1))
				{
					// Not possible. Throw item in the world
					EntityItem entityItem1 = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack1);
					world.spawnEntity(entityItem1);
				} else
				{
					player.openContainer.detectAndSendChanges();
				}
				if (!player.inventory.addItemStackToInventory(stack2))
				{
					// Not possible. Throw item in the world
					EntityItem entityItem2 = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack2);
					world.spawnEntity(entityItem2);
				} else
				{
					player.openContainer.detectAndSendChanges();
				}
				te.setDone(false);
			}
		}
		
		// Return true also on the client to make sure that MC knows we handled this and
		// will not try to place
		// a block on the client
		return true;
	}
	
	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			TileKitchenToaster te = getTE(world, pos);
			ItemStack stack1 = te.getStack1();
			ItemStack stack2 = te.getStack2();
			if (!stack1.isEmpty())
			{
				EntityItem entityItem1 = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack1);
				world.spawnEntity(entityItem1);
			}
			if (!stack2.isEmpty())
			{
				EntityItem entityItem2 = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), stack2);
				world.spawnEntity(entityItem2);
			}
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		
		switch (state.getValue(FACING))
		{
			
			case NORTH:
				return BASE_AABB_NORTH;
			
			case SOUTH:
				return BASE_AABB_SOUTH;
			
			case EAST:
				return BASE_AABB_EAST;
			
			case WEST:
				return BASE_AABB_WEST;
			
			default:
				break;
			
		}
		return null;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_)
	{
		
		switch (state.getValue(FACING))
		{
			case NORTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB_NORTH);
				break;
			case SOUTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB_SOUTH);
				break;
			case EAST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB_EAST);
				break;
			case WEST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BASE_AABB_WEST);
				
			default:
				break;
		}
		
	}
}