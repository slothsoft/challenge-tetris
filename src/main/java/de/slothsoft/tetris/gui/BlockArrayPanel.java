package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.BlockArray;

public class BlockArrayPanel extends JPanel {

	private static final long serialVersionUID = -4607180702570402004L;
	private static final long REPAINT_IN_MS = 1000 / 24; // 24 frames per second

	private BlockArray array;
	private int width;
	private int height;

	public BlockArrayPanel() {
		this(null);
	}

	public BlockArrayPanel(BlockArray array) {
		setBlockArray(array);
		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics graphics) {
		if (getBackground() != null) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}

		if (this.array != null) {
			float ratio = (float) Math.min((double) getWidth() / this.width, (double) getHeight() / this.height);
			((Graphics2D) graphics).scale(ratio, ratio);
			((Graphics2D) graphics).translate(Block.WIDTH, Block.HEIGHT);
			this.array.paint((Graphics2D) graphics);
		}
		repaint(REPAINT_IN_MS);
	}

	public BlockArray getBlockArray() {
		return this.array;
	}

	public void setBlockArray(BlockArray array) {
		this.array = array;
		this.width = array == null ? 0 : array.getWidthInPixels() + 2 * Block.WIDTH;
		this.height = array == null ? 0 : array.getHeightInPixels() + 2 * Block.HEIGHT;
		setPreferredSize(new Dimension(this.width, this.height));
		repaint();
	}

}
