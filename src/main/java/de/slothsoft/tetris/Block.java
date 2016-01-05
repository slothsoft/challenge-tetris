package de.slothsoft.tetris;

import java.awt.Graphics2D;

public interface Block {

	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	/**
	 * Paints the block at the coordinates (0,0) and with the width {@code WIDTH } and
	 * height {@code HEIGHT }
	 *
	 * @param graphics
	 *            graphics
	 */

	void paint(Graphics2D graphics);
}