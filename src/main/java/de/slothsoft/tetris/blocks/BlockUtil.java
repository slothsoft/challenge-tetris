package de.slothsoft.tetris.blocks;

import java.awt.Color;

/**
 * A util class for block factories
 * 
 * @since 1.0.0
 */

public final class BlockUtil {

	private static final int HEXADECIMAL = 16;
	private static final int MAX_COLOR_INT = 256;

	public static Color createColor(String colorHex) {
		int colorInt = Integer.parseInt(colorHex, HEXADECIMAL);
		int red = colorInt / MAX_COLOR_INT / MAX_COLOR_INT;
		int green = (colorInt / MAX_COLOR_INT) % MAX_COLOR_INT;
		int blue = colorInt % MAX_COLOR_INT;
		return new Color(red, green, blue);
	}

	private BlockUtil() {
		// hide me
	}
}
