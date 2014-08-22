package net.turrem.tvf.color;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class TVFPalette
{
	public static TVFPalette make(int type)
	{
		switch (type)
		{
			case 1:
				return new TVFPaletteColor();
			case 2:
				return new TVFPaletteDynamic();
			default:
				return null;
		}
	}

	public abstract int getType();

	protected abstract void readPalette(DataInputStream data) throws IOException;

	protected abstract void writePalette(DataOutputStream data) throws IOException;

	public static TVFPalette read(DataInputStream data) throws IOException
	{
		int type = data.readByte() & 0xFF;
		TVFPalette palette = make(type);
		if (palette != null)
		{
			palette.readPalette(data);
		}
		return palette;
	}

	public static void write(DataOutputStream data, TVFPalette palette) throws IOException
	{
		if (palette == null)
		{
			data.writeByte(0);
		}
		else
		{
			data.writeByte(palette.getType());
			palette.writePalette(data);
		}
	}
}
