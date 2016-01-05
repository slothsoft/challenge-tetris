package de.slothsoft.tetris.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import de.slothsoft.tetris.Block;

/**
 * These blocks are pretty similar to the default tetris blocks:
 *
 * <pre>
 * {@code
 * .....X
 * ....XX
 * ..++XX
 * ..++XX
 * .XXXXX
 * XXXXXX
 * }
 * </pre>
 */

public final class SingleColorBlock implements Block {

	private static final Shape LIGHT_TRIANGLE = new Polygon(new int[] { 0, WIDTH, 0 }, new int[] { 0, 0, HEIGHT }, 3);
	private static final Shape DARK_TRIANGLE = new Polygon(new int[] { 0, WIDTH, WIDTH },
			new int[] { HEIGHT, 0, HEIGHT }, 3);

	private final Color color;
	private final Color lightColor;
	private final Color darkColor;

	public SingleColorBlock(Color color) {
		this.color = color;
		this.lightColor = color.brighter();
		this.darkColor = color.darker();
	}

	@Override
	public void paint(Graphics2D graphics) {
		graphics.setColor(this.lightColor);
		graphics.fill(LIGHT_TRIANGLE);

		graphics.setColor(this.darkColor);
		graphics.fill(DARK_TRIANGLE);

		int oneThird = WIDTH / 3;
		graphics.setColor(this.color);
		graphics.fillRect(oneThird, oneThird, oneThird, oneThird);
	}

}
