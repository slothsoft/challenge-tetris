package de.slothsoft.tetris;

/**
 * Collisions are left and right (e.g. things that don't allow to pass through,
 * but don't make you clonk)
 * 
 * @since 1.0.0
 */

public final class CollisionChecker {

	private final Board board;

	public CollisionChecker(Board board) {
		this.board = board;
	}

	/**
	 * Returns true if the stone can be moved left
	 */

	public boolean canMoveLeft(Stone stone) {
		if (isWallOnLeft(stone)) {
			return false;
		}
		if (isBlockOnLeft(stone)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if there is a wall on the left of the stone
	 */

	public boolean isWallOnLeft(Stone stone) {
		return stone.getXInBlocks() <= 0;
	}

	/**
	 * Returns true if there is a block on the left of the stone
	 */

	public boolean isBlockOnLeft(Stone stone) {
		int blockX = stone.getXInBlocks();
		int blockY = stone.getYInBlocks();

		Block[][] stoneBlocks = stone.getBlocks();
		for (int x = 0; x < stoneBlocks.length; x++) {
			for (int y = 0; y < stoneBlocks[x].length; y++) {
				if (isBlockOnLeft(stone, stoneBlocks[x][y], x + blockX, y + blockY)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBlockOnLeft(Stone stone, Block block, int x, int y) {
		if (block == null) {
			return false;
		}
		if (x <= 0) {
			return false;
		}
		return this.board.getBlock(x - 1, y) != null;
	}

	/**
	 * Returns true if the stone can be moved right
	 */

	public boolean canMoveRight(Stone stone) {
		if (isWallOnRight(stone)) {
			return false;
		}
		if (isBlockOnRight(stone)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns true if there is a wall on the right of the stone
	 */

	public boolean isWallOnRight(Stone stone) {
		return stone.getXInBlocks() + stone.getWidthInBlocks() >= Board.WIDTH_IN_BLOCKS;
	}

	/**
	 * Returns true if there is a block on the right of the stone
	 */

	public boolean isBlockOnRight(Stone stone) {
		int blockX = stone.getXInBlocks();
		int blockY = stone.getYInBlocks();

		Block[][] stoneBlocks = stone.getBlocks();
		for (int x = 0; x < stoneBlocks.length; x++) {
			for (int y = 0; y < stoneBlocks[x].length; y++) {
				if (isBlockOnRight(stone, stoneBlocks[x][y], x + blockX, y + blockY)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBlockOnRight(Stone stone, Block block, int x, int y) {
		if (block == null) {
			return false;
		}
		if (x >= Board.WIDTH_IN_BLOCKS - 1) {
			return false;
		}
		return this.board.getBlock(x + 1, y) != null;
	}

	/**
	 * Returns true if the rotated stone still fits
	 */

	public boolean canRotate(Stone rotated) {
		boolean notStickingOutRight = rotated.getXInBlocks() + rotated.getWidthInBlocks() < Board.WIDTH_IN_BLOCKS;
		boolean notStickingOutLeft = rotated.getXInBlocks() >= 0;
		boolean notStickingOutBottom = rotated.getYInBlocks() + rotated.getHeightInBlocks() < Board.HEIGHT_IN_BLOCKS;
		boolean notStickingOutTop = rotated.getYInBlocks() >= 0;
		return notStickingOutRight && notStickingOutLeft && notStickingOutBottom && notStickingOutTop
				&& !isBlockOnStone(rotated);
	}

	private boolean isBlockOnStone(Stone stone) {
		int blockX = stone.getXInBlocks();
		int blockY = stone.getYInBlocks();

		Block[][] stoneBlocks = stone.getBlocks();
		for (int x = 0; x < stoneBlocks.length; x++) {
			for (int y = 0; y < stoneBlocks[x].length; y++) {
				if (isBlockOnStone(stoneBlocks[x][y], x + blockX, y + blockY)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBlockOnStone(Block block, int x, int y) {
		if (block == null) {
			return false;
		}
		return this.board.getBlock(x, y) != null;
	}
}
