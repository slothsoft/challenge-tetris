package de.slothsoft.tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A board is where the entire action plays out
 */

public final class Board extends BlockArray {

	public static final int WIDTH_IN_BLOCKS = 10;
	public static final int WIDTH_IN_PIXELS = WIDTH_IN_BLOCKS * Block.WIDTH;
	public static final int HEIGHT_IN_BLOCKS = 22;
	public static final int HEIGHT_IN_PIXELS = HEIGHT_IN_BLOCKS * Block.WIDTH;
	public static final int BORDER_WIDTH = 2;

	private Stone currentStone;

	public Board() {
		super(WIDTH_IN_BLOCKS, HEIGHT_IN_BLOCKS);
	}

	@Override
	public void paint(Graphics2D graphics) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.BLACK);
		graphics.fillRect(-BORDER_WIDTH, -BORDER_WIDTH, WIDTH_IN_PIXELS + 2 * BORDER_WIDTH, HEIGHT_IN_PIXELS + 2
				* BORDER_WIDTH);

		graphics.setStroke(new BasicStroke());
		graphics.setColor(Color.WHITE);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, WIDTH_IN_PIXELS + 2 * BORDER_WIDTH, HEIGHT_IN_PIXELS + 2
				* BORDER_WIDTH);

		super.paint(graphics);

		if (this.currentStone != null) {
			graphics.translate(this.currentStone.getXInPixels(), this.currentStone.getYInPixels());
			this.currentStone.paint(graphics);
			graphics.translate(-this.currentStone.getXInPixels(), -this.currentStone.getYInPixels());
		}
	}

	/**
	 * Clears the entire board
	 */

	public void clear() {
		for (int xi = 0; xi < this.blocks.length; xi++) {
			for (int yi = 0; yi < this.blocks[xi].length; yi++) {
				this.blocks[xi][yi] = null;
			}
		}
	}

	/**
	 * Puts the blocks of another block array down
	 */

	public void putBlocks(int blockX, int blockY, BlockArray array) {
		Block[][] otherBlocks = array.getBlocks();
		for (int startX = 0; startX < otherBlocks.length; startX++) {
			for (int startY = 0; startY < otherBlocks[startX].length; startY++) {
				if (otherBlocks[startX][startY] != null) {
					this.blocks[startX + blockX][startY + blockY] = otherBlocks[startX][startY];
				}
			}
		}
	}

	public Stone getCurrentStone() {
		return this.currentStone;
	}

	public void setCurrentStone(Stone currentStone) {
		this.currentStone = currentStone;
	}

	@Override
	public Board clone() {
		return (Board) super.clone();
	}
}
