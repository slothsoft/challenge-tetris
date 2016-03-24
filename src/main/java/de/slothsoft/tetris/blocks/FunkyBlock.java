package de.slothsoft.tetris.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.tetris.Block;

/**
 * These blocks are pretty similar to the funky blocks in Tritus
 * 
 * @see <a href="https://github.com/slothsoft/tetris-challenge/issues/2">
 *      Feature Request</a>
 * @since 1.0.0
 */

public final class FunkyBlock implements Block {

	private static final Color BACKGROUND = BlockUtil.createColor("ECE01C");

	private final Color color;
	private final Color darkColor;

	public FunkyBlock(Color color) {
		this.color = color;
		this.darkColor = color.darker().darker();
	}

	@Override
	public void paint(Graphics2D graphics) {
		graphics.setColor(BACKGROUND);
		graphics.fillRect(0, 0, WIDTH_IN_PIXELS, HEIGHT_IN_PIXELS);

		int oneFourth = WIDTH_IN_PIXELS / 4;
		int oneHalf = WIDTH_IN_PIXELS / 2;

		graphics.setColor(this.darkColor);
		graphics.fillRect(0, oneFourth + 4, WIDTH_IN_PIXELS, oneFourth);
		graphics.fillRect(oneFourth + 4, 0, oneFourth, HEIGHT_IN_PIXELS);

		graphics.setColor(this.color);
		graphics.fillRect(0, oneHalf, WIDTH_IN_PIXELS, oneFourth);
		graphics.fillRect(oneHalf, 0, oneFourth, HEIGHT_IN_PIXELS);
	}

}
