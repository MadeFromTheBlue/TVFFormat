package net.turrem.tvf.color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.turrem.tvf.ITVFRenderInterface;

public class TVFPaletteDynamic extends TVFPaletteColor
{	
	public EnumDynamicColorMode mode;
	public byte colorChannel;
	
	@Override
	public int getType()
	{
		return 2;
	}
	
	@Override
	public void readPalette(DataInputStream data) throws IOException
	{
		super.readPalette(data);
		this.mode = EnumDynamicColorMode.values()[data.readByte() & 0xFF];
		this.colorChannel = data.readByte();
	}

	@Override
	public void writePalette(DataOutputStream data) throws IOException
	{
		super.writePalette(data);
		data.writeByte(this.mode.ordinal());
		data.writeByte(this.colorChannel & 0xFF);
	}
	
	@Override
	public void startRender(ITVFRenderInterface render, Object[] pars)
	{
		render.setDynamicColor(this.mode, pars[this.colorChannel & 0xFF]);
	}

	@Override
	public void clearRender(ITVFRenderInterface render)
	{
		render.clearDynamicColor();
	}
}
