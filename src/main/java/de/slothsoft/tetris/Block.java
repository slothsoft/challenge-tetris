package de.slothsoft.tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.io.Serializable;

public final class Block implements Serializable {

	private static final long serialVersionUID = -190284102481272481L;

	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private static final Shape LIGHT_TRIANGLE = new Polygon(new int[] { 0, WIDTH, 0 }, new int[] { 0, 0, HEIGHT }, 3);
	private static final Shape DARK_TRIANGLE = new Polygon(new int[] { 0, WIDTH, WIDTH },
			new int[] { HEIGHT, 0, HEIGHT }, 3);

	private final Color color;
	private final Color lightColor;
	private final Color darkColor;

	public Block(Color color) {
		this.color = color;
		this.lightColor = color.brighter();
		this.darkColor = color.darker();
	}

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
