package com.withertech.minekea.blocks;

import com.withertech.minekea.Minekea;
import com.withertech.minekea.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockKitchenCounterEndRight extends Block 
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public BlockKitchenCounterEndRight()
	{
		super(Material.ROCK);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(5.0F);
		setUnlocalizedName(Minekea.MODID + ".blockkitchencounterendright");
		setRegistryName("blockkitchencounterendright");
		this.setCreativeTab(CommonProxy.MinekeaKitchenTab);
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
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
    {
        world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.getFront(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
}
