package de.slothsoft.tetris;

/**
 * Clonks are always when the {@link Stone} hits the bottom ({@link Board} or
 * another {@link Block}). A new stone is about to get born after that.
 */

public final class ClonkChecker {

	private final Board board;

	public ClonkChecker(Board board) {
		this.board = board;
	}

	/**
	 * Returns true if the stone should be clonked to the current position
	 */

	public boolean isClonked(Stone stone) {
		return isClonkedToBottom(stone) || isClonkedToBlock(stone);
	}

	/**
	 * Returns true if the should be clonked to the bottom of the board
	 */

	public boolean isClonkedToBottom(Stone stone) {
		return isClonkedToY(stone, Board.HEIGHT_IN_BLOCKS);
	}

	private static boolean isClonkedToY(Stone stone, float y) {
		return stone.getYInBlocks() + stone.getHeightInBlocks() >= y;
	}

	/**
	 * Returns true if the should be clonked to another block
	 */

	public boolean isClonkedToBlock(Stone stone) {
		int blockX = stone.getXInBlocks();
		int blockY = stone.getYInBlocks();

		Block[][] stoneBlocks = stone.getBlocks();
		for (int x = 0; x < stoneBlocks.length; x++) {
			for (int y = 0; y < stoneBlocks[x].length; y++) {
				if (isClonkedToBlock(stoneBlocks[x][y], x + blockX, y + blockY)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isClonkedToBlock(Block block, int x, int y) {
		if (block == null) {
			return false;
		}
		if (y >= Board.HEIGHT_IN_BLOCKS - 1) {
			return false;
		}
		return this.board.getBlock(x, y + 1) != null;
	}
}
