package com.withertech.minekea.items;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.withertech.minekea.Minekea;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNowYaDoneIt extends ItemFood
{
	List<PotionEffect> effectList = Arrays.asList(new PotionEffect(MobEffects.WEAKNESS, 300000, 254, false, true), new PotionEffect(MobEffects.BLINDNESS, 300000, 254, false, true), new PotionEffect(MobEffects.NAUSEA, 300000, 254, false, true), new PotionEffect(MobEffects.WITHER, 300000, 100, false, true), new PotionEffect(MobEffects.LEVITATION, 300000, 254, false, true), new PotionEffect(MobEffects.SLOWNESS, 300000, 254, false, true), new PotionEffect(MobEffects.HUNGER, 300000, 254, false, true), new PotionEffect(MobEffects.POISON, 300000, 100, false, true), new PotionEffect(MobEffects.HEALTH_BOOST, 300000, 254, false, true), new PotionEffect(MobEffects.UNLUCK, 300000, 254, false, true));
	PotionEffect effect;
	
	public ItemNowYaDoneIt()
	{
		super(-10, 0.0f, false);
		setRegistryName("itemnowyadoneit");
		setUnlocalizedName(Minekea.MODID + ".itemnowyadoneit");
		setAlwaysEdible();
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flags)
	{
		list.add("Seriously? it's started nuclear fusion!");
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if (!worldIn.isRemote)
		{
			for (int i = 0; i < effectList.size(); i++)
			{
				effect = effectList.get(i);
				player.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
				
			}
		}
	}
}
