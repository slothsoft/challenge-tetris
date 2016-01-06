package de.slothsoft.tetris.gui;

import static de.slothsoft.tetris.Board.BORDER_WIDTH;
import static de.slothsoft.tetris.Board.HEIGHT_IN_PIXELS;
import static de.slothsoft.tetris.Board.WIDTH_IN_PIXELS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.BlockArray;
import de.slothsoft.tetris.Board;
import de.slothsoft.tetris.Stone;

public interface TetrisRenderer {

	TetrisRenderer DEFAULT = new TetrisRenderer() {

		@Override
		public String getDisplayName() {
			return "Default";
		}

		@Override
		public void paintBlock(Graphics2D graphics, Block block) {
			block.paint(graphics);
		}
	};

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Paints a board
	 *
	 * @param graphics
	 *            graphics
	 * @param array
	 *            block array
	 */

	default void paintBoard(Graphics2D graphics, Board board) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.BLACK);
		graphics.fillRect(-BORDER_WIDTH, -BORDER_WIDTH, WIDTH_IN_PIXELS + 2 * BORDER_WIDTH, HEIGHT_IN_PIXELS + 2
				* BORDER_WIDTH);

		graphics.setStroke(new BasicStroke());
		graphics.setColor(Color.WHITE);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, WIDTH_IN_PIXELS + 2 * BORDER_WIDTH, HEIGHT_IN_PIXELS + 2
				* BORDER_WIDTH);

		paintBlockArray(graphics, board);

		Stone currentStone = board.getCurrentStone();
		if (currentStone != null) {
			graphics.translate(currentStone.getXInPixels(), currentStone.getYInPixels());
			paintStone(graphics, currentStone);
			graphics.translate(-currentStone.getXInPixels(), -currentStone.getYInPixels());
		}
	}

	/**
	 * Paints a stone
	 *
	 * @param graphics
	 *            graphics
	 * @param stone
	 *            stone
	 */

	default void paintStone(Graphics2D graphics, Stone stone) {
		paintBlockArray(graphics, stone);
	}

	/**
	 * Paints an entire block array
	 *
	 * @param graphics
	 *            graphics
	 * @param array
	 *            block array
	 */

	default void paintBlockArray(Graphics2D graphics, BlockArray array) {
		Block[][] blocks = array.getBlocks();
		for (int xi = 0; xi < blocks.length; xi++) {
			for (int yi = 0; yi < blocks[xi].length; yi++) {
				Block block = blocks[xi][yi];
				if (block != null) {
					graphics.translate(xi * Block.WIDTH, yi * Block.HEIGHT);
					paintBlock(graphics, block);
					graphics.translate(-xi * Block.WIDTH, -yi * Block.HEIGHT);
				}
			}
		}
	}

	/**
	 * Paints a single block of the board
	 *
	 * @param graphics
	 *            graphics
	 * @param array
	 *            block array
	 */

	void paintBlock(Graphics2D graphics, Block block);

}
