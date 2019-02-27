package de.slothsoft.tetris;

import java.awt.Graphics2D;

/**
 * The block is the smallest unit of a Tetris board. Both {@link Board}s and
 * {@link Stone}s are made from them.
 * 
 * @see <a href="https://github.com/slothsoft/challenge-tetris/wiki#basics">
 *      Basics</a>
 * @since 1.0.0
 */

public interface Block {

	public static final int WIDTH_IN_PIXELS = 30;
	public static final int HEIGHT_IN_PIXELS = 30;

	/**
	 * Paints the block at the coordinates (0,0) and with the width
	 * {@code WIDTH } and height {@code HEIGHT }
	 *
	 * @param graphics
	 *            graphics
	 */

	void paint(Graphics2D graphics);
}