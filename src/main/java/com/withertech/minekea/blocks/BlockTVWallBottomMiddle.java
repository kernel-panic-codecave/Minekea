package com.withertech.minekea.blocks;

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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTVWallBottomMiddle extends Block 
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool ENABLED = PropertyBool.create("enabled");
	
	public BlockTVWallBottomMiddle()
	{
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(10.0F);		setUnlocalizedName(Minekea.MODID + ".blocktvwallbottommiddle");
		setRegistryName("blocktvwallbottommiddle");
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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            state = state.cycleProperty(ENABLED);
            worldIn.setBlockState(pos, state, 3);
            float f = ((Boolean)state.getValue(ENABLED)).booleanValue() ? 0.6F : 0.5F;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
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
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(FACING, EnumFacing.getFront(meta & 7))
                .withProperty(ENABLED, Boolean.valueOf((meta & 8) > 0));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex() + (state.getValue(ENABLED) ? 8 : 0);
    }
    

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ENABLED);
    }
}
