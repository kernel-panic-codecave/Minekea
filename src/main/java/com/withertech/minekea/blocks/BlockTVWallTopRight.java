package com.withertech.minekea.blocks;

import java.util.List;

import com.withertech.minekea.Minekea;
import com.withertech.minekea.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTVWallTopRight extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool ENABLED = PropertyBool.create("enabled");
	private static final AxisAlignedBB BASE_AABB_NORTH = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (14 * 0.0625), (16 * 0.0625), (16 * 0.0625), (16 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_SOUTH = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (0 * 0.0625), (16 * 0.0625), (16 * 0.0625), (2 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_EAST = new AxisAlignedBB((0 * 0.0625), (0 * 0.0625), (0 * 0.0625), (2 * 0.0625), (16 * 0.0625), (16 * 0.0625));
	private static final AxisAlignedBB BASE_AABB_WEST = new AxisAlignedBB((14 * 0.0625), (0 * 0.0625), (0 * 0.0625), (16 * 0.0625), (16 * 0.0625), (16 * 0.0625));
	
	public BlockTVWallTopRight()
	{
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(10.0F);
		setUnlocalizedName(Minekea.MODID + ".blocktvwalltopright");
		setRegistryName("blocktvwalltopright");
		this.setCreativeTab(CommonProxy.MinekeaDenTab);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
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
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		} else
		{
			state = state.cycleProperty(ENABLED);
			worldIn.setBlockState(pos, state, 3);
			float f = ((Boolean) state.getValue(ENABLED)).booleanValue() ? 0.6F : 0.5F;
			worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
			worldIn.notifyNeighborsOfStateChange(pos, this, false);
			return true;
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7)).withProperty(ENABLED, Boolean.valueOf((meta & 8) > 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(FACING).getIndex() + (state.getValue(ENABLED) ? 8 : 0);
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, FACING, ENABLED);
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
