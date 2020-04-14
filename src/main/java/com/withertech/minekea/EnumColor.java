package com.withertech.minekea;

import net.minecraft.util.IStringSerializable;

public enum EnumColor implements IStringSerializable
{
	WHITE("White"), LIGHT_GRAY("Light gray"), BLACK("Black");
	
	public int getMetadata()
	{
		return ordinal();
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
	
	public static EnumColor byMetadata(int meta)
	{
		if (meta < 0 || meta >= values().length)
		{
			meta = 0;
		}
		
		return values()[meta];
	}
	
	public String getName()
	{
		return this.name.toLowerCase().replace(' ', '_');
	}
	
	private final String name;
	
	EnumColor(String i_name)
	{
		this.name = i_name;
	}
}
