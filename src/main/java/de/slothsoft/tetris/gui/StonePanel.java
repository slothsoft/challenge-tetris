package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Stone;

public class StonePanel extends JPanel {

	private static final long serialVersionUID = 257040200446071807L;

	private Stone stone;
	private int width;
	private int height;

	public StonePanel() {
		this(null);
	}

	public StonePanel(Stone stone) {
		setStone(stone);
		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics graphics) {
		if (getBackground() != null) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}

		if (this.stone != null) {
			((Graphics2D) graphics).translate(Block.WIDTH, Block.HEIGHT);
			TetrisRenderer.DEFAULT.paintStone((Graphics2D) graphics, this.stone);
		}
	}

	public Stone getStone() {
		return this.stone;
	}

	public void setStone(Stone stone) {
		this.stone = stone;
		this.width = stone == null ? 0 : stone.getWidthInPixels() + 2 * Block.WIDTH;
		this.height = stone == null ? 0 : stone.getHeightInPixels() + 2 * Block.HEIGHT;
		setPreferredSize(new Dimension(this.width, this.height));
		repaint();
	}

}
