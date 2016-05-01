package de.slothsoft.tetris.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.tetris.Block;

/**
 * These blocks are pretty similar to the funky blocks in Tritus
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
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		int oneFourth = WIDTH / 4;
		int oneHalf = WIDTH / 2;

		graphics.setColor(this.darkColor);
		graphics.fillRect(0, oneFourth + 4, WIDTH, oneFourth);
		graphics.fillRect(oneFourth + 4, 0, oneFourth, HEIGHT);

		graphics.setColor(this.color);
		graphics.fillRect(0, oneHalf, WIDTH, oneFourth);
		graphics.fillRect(oneHalf, 0, oneFourth, HEIGHT);
	}

}