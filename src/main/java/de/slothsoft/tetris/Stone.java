package de.slothsoft.tetris;

import java.util.Arrays;
import java.util.Objects;

/**
 * A stone is a moveable collection of {@link Block}s (in contrary to the
 * {@link Board}, which is fix)
 * 
 * @see <a href="https://github.com/slothsoft/tetris-challenge/wiki#basics">
 *      Basics</a>
 * @since 1.0.0
 */

public final class Stone extends BlockArray {

	private int xInBlocks;
	private double yInBlocks;

	public Stone(Block[][] blocks) {
		super(blocks);
	}

	/**
	 * Creates a new stone that is rotated left
	 *
	 * @return a new stone
	 */

	public Stone createLeftRotation() {
		return createNewStone(createBlocksForLeftRotation());
	}

	/**
	 * Rotates this stone to the left
	 */

	void rotateLeft() {
		setBlocks(createBlocksForLeftRotation());
	}

	Block[][] createBlocksForLeftRotation() {
		Block[][] rotatedBlocks = new Block[this.blocks[0].length][this.blocks.length];
		for (int xi = 0; xi < rotatedBlocks.length; ++xi) {
			for (int yi = 0; yi < rotatedBlocks[xi].length; ++yi) {
				rotatedBlocks[xi][yi] = this.blocks[rotatedBlocks[xi].length - yi - 1][xi];
			}
		}
		return rotatedBlocks;
	}

	private Stone createNewStone(Block[][] rotatedBlocks) {
		Stone stone = new Stone(rotatedBlocks);
		stone.xInBlocks = this.xInBlocks;
		stone.yInBlocks = this.yInBlocks;
		return stone;
	}

	/**
	 * Creates a new stone that is rotated right
	 *
	 * @return a new stone
	 */

	public Stone createRightRotation() {
		return createNewStone(createBlocksForRightRotation());
	}

	/**
	 * Rotates this stone to the right
	 */

	void rotateRight() {
		setBlocks(createBlocksForRightRotation());
	}

	Block[][] createBlocksForRightRotation() {
		Block[][] rotatedBlocks = new Block[this.blocks[0].length][this.blocks.length];

		for (int xi = 0; xi < rotatedBlocks.length; ++xi) {
			for (int yi = 0; yi < rotatedBlocks[xi].length; ++yi) {
				rotatedBlocks[xi][yi] = this.blocks[yi][rotatedBlocks.length - xi - 1];
			}
		}
		return rotatedBlocks;
	}

	public void setPositionInBlocks(int x, int y) {
		setXInBlocks(x);
		setYInBlocks(y);
	}

	/**
	 * The x coordinate in pixels
	 */

	public int getXInBlocks() {
		return this.xInBlocks;
	}

	public int getXInPixels() {
		return this.xInBlocks * Block.WIDTH_IN_PIXELS;
	}

	void incrementXInBlocks(int increment) {
		this.xInBlocks += increment;
	}

	void setXInBlocks(int x) {
		this.xInBlocks = x;
	}

	/**
	 * The y coordinate in pixels
	 */

	public double getRawYInBlocks() {
		return this.yInBlocks;
	}

	/**
	 * The y coordinate in pixels
	 */

	public int getYInBlocks() {
		return (int) this.yInBlocks;
	}

	public int getYInPixels() {
		return (int) (this.yInBlocks * Block.HEIGHT_IN_PIXELS);
	}

	void incrementYInBlocks(double increment) {
		this.yInBlocks += increment;
	}

	void setYInBlocks(double y) {
		this.yInBlocks = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Stone) {
			Stone that = (Stone) obj;
			return Objects.deepEquals(getBlocks(), that.getBlocks());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(getBlocks());
	}

}
