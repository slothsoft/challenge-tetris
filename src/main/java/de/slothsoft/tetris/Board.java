package de.slothsoft.tetris;

/**
 * A board is where the entire action plays out
 * 
 * @see <a href="https://github.com/slothsoft/tetris-challenge/wiki#basics">
 *      Basics</a>
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
