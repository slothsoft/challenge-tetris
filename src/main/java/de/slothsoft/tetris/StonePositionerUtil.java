package de.slothsoft.tetris;

/**
 * Util class for calculating stuff to get a good stone position
 */

public final class StonePositionerUtil {

	/**
	 * Returns the Y coordinate if the stone was dropped on the X coordinate
	 */

	public static int calculateFutureY(Block[][] blocks, Stone stone, int blockX) {
		int stoneHeight = stone.getHeightInBlocks();
		int maxY = Board.HEIGHT_IN_BLOCKS - stoneHeight;
		for (int y = 0; y <= maxY; y++) {
			if (isStoneBlocked(blocks, stone, blockX, y))
				return y - 1;
		}
		return maxY;
	}

	private static boolean isStoneBlocked(Block[][] blocks, Stone stone, int blockX, int blockY) {
		Block[][] stoneBlocks = stone.getBlocks();

		for (int x = 0; x < stone.getWidthInBlocks(); x++) {
			for (int y = 0; y < stone.getHeightInBlocks(); y++) {
				if (blocks[x + blockX][y + blockY] != null && stoneBlocks[x][y] != null)
					return true;
			}
		}
		return false;
	}

	/**
	 * Returns the count of lines that get removed if the stone would be
	 * positioned at the Y coordinate
	 */

	public static int getFutureLinesVanished(Board board, Stone stone, int blockY) {
		int lines = 0;
		for (int y = 0; y < stone.getHeightInBlocks(); y++) {
			int stoneBlocks = stone.calculateBlockCountOfLine(y);
			int boardBlocksCount = board.calculateBlockCountOfLine(y + blockY);
			if (stoneBlocks + boardBlocksCount >= Board.WIDTH_IN_BLOCKS) {
				lines++;
			}
		}
		return lines;
	}

	/**
	 * Gets the number of holes in the board block, if the stone gets clonked to
	 * the coordinates (holes are every missing block below the stone)
	 */

	public static int getHoleCount(Block[][] boardBlocks, Stone stone, int blockX, int blockY) {
		int holes = 0;
		for (int x = 0; x < stone.getWidthInBlocks(); x++) {
			int startY = blockY + getLastYOfStone(stone, x);

			for (int y = startY; y < Board.HEIGHT_IN_BLOCKS; y++) {
				if (boardBlocks[blockX + x][y] == null) {
					holes++;
				} else {
					break;
				}
			}
		}
		return holes;
	}

	/**
	 * Returns the max Y of the stone (in case one line only has null blocks)
	 */

	public static int getLastYOfStone(Stone stone, int x) {
		int height = stone.getHeightInBlocks();
		for (int y = height - 1; y >= 0; y--) {
			if (stone.getBlock(x, y) != null)
				return y + 1;
		}
		return 0;
	}

	private StonePositionerUtil() {
		// hide util class
	}
}
