package de.slothsoft.tetris;

/**
 * A block array is exactly what it says on the tin - a two dimensional array of
 * {@link Block}s
 * 
 * @since 1.0.0
 */

public class BlockArray implements Cloneable {

	protected Block[][] blocks;
	private int widthInBlocks;
	private int heightInBlocks;

	public BlockArray(int widthInBlocks, int heightInBlocks) {
		this(new Block[widthInBlocks][heightInBlocks]);
	}

	public BlockArray(Block[][] blocks) {
		this.blocks = blocks.clone();
		this.widthInBlocks = blocks.length;
		this.heightInBlocks = blocks[0].length;
	}

	/**
	 * Calculates the number of blocks in the line
	 *
	 * @param blockY
	 *            - line index
	 * @return count
	 */

	public int calculateBlockCountOfLine(int blockY) {
		int blocksCount = 0;
		for (int blockX = 0; blockX < this.widthInBlocks; blockX++) {
			if (this.blocks[blockX][blockY] != null) {
				blocksCount++;
			}
		}
		return blocksCount;
	}

	/**
	 * Calculates the number of blocks in the column
	 *
	 * @param blockX
	 *            - column index
	 * @return count
	 */

	public int calculateBlockCountOfColumn(int blockX) {
		int blocksCount = 0;
		for (int blockY = 0; blockY < this.heightInBlocks; blockY++) {
			if (this.blocks[blockX][blockY] != null) {
				blocksCount++;
			}
		}
		return blocksCount;
	}

	@Override
	public BlockArray clone() {
		try {
			BlockArray clone = (BlockArray) super.clone();
			Block[][] cloneBlocks = this.blocks.clone();
			for (int xi = 0; xi < this.blocks.length; xi++) {
				cloneBlocks[xi] = this.blocks[xi].clone();
				for (int yi = 0; yi < this.blocks[xi].length; yi++) {
					cloneBlocks[xi][yi] = this.blocks[xi][yi];
				}
			}
			clone.setBlocks(cloneBlocks);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}

	public String stringify() {
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < this.blocks[0].length; y++) {
			for (int x = 0; x < this.blocks.length; x++) {
				sb.append(this.blocks[x][y] == null ? " " : "X");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public int getWidthInPixels() {
		return this.widthInBlocks * Block.WIDTH_IN_PIXELS;
	}

	public int getHeightInPixels() {
		return this.heightInBlocks * Block.HEIGHT_IN_PIXELS;
	}

	final void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
		this.widthInBlocks = blocks.length;
		this.heightInBlocks = blocks[0].length;
	}

	public final Block[][] getBlocks() {
		return this.blocks.clone();
	}

	public final void setBlock(int x, int y, Block block) {
		this.blocks[x][y] = block;
	}

	public final Block getBlock(int x, int y) {
		return this.blocks[x][y];
	}

	public int getWidthInBlocks() {
		return this.widthInBlocks;
	}

	public int getHeightInBlocks() {
		return this.heightInBlocks;
	}

}
