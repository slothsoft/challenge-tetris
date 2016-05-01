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
 * 
 * @since 1.0.0
 */

public final class SingleColorBlock implements Block {

	private static final Shape LIGHT_TRIANGLE = new Polygon(new int[] { 0, WIDTH_IN_PIXELS, 0 }, new int[] { 0, 0, HEIGHT_IN_PIXELS }, 3);
	private static final Shape DARK_TRIANGLE = new Polygon(new int[] { 0, WIDTH_IN_PIXELS, WIDTH_IN_PIXELS },
			new int[] { HEIGHT_IN_PIXELS, 0, HEIGHT_IN_PIXELS }, 3);

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

		int oneThird = WIDTH_IN_PIXELS / 3;
		graphics.setColor(this.color);
		graphics.fillRect(oneThird, oneThird, oneThird, oneThird);
	}

}
