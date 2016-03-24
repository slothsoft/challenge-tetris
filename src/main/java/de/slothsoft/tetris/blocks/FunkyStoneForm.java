package de.slothsoft.tetris.blocks;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.blocks.DefaultStoneFactory.StoneProviderEnum;

/**
 * These stones are pretty similar to the funky stones in Tritus
 * 
 * @see <a href="https://github.com/slothsoft/tetris-challenge/issues/2">
 *      Feature Request</a>
 * @since 1.0.0
 * 
 */

public enum FunkyStoneForm implements StoneProviderEnum {
	BIG_L("5555FF", new boolean[][] { { true, true, true, true }, { false, false, false, true } }),

	BIG_J("FF1E04", new boolean[][] { { true, true, true, true }, { true, false, false, false } }),

	FUNKY_L("F82DAB", new boolean[][] { { true, true, true, true }, { false, false, true, false } }),

	FUNKY_J("2BFFD1", new boolean[][] { { true, true, true, true }, { false, true, false, false } }),

	ZO("008EFF", new boolean[][] { { true, true, true }, { true, true, false } }),

	X("FF6A00", new boolean[][] { { false, true, false }, { true, true, true }, { false, true, false } }),

	SO("008EFF", new boolean[][] { { true, true, false }, { true, true, true } }),

	STAIRS("37B700", new boolean[][] { { false, false, true }, { false, true, true }, { true, true, false } })

	; // $NON-NLS-1$

	private final Block block;
	private final boolean[][] blockArray;

	private FunkyStoneForm(String colorHex, boolean[][] blockArray) {
		this.block = new FunkyBlock(BlockUtil.createColor(colorHex));
		this.blockArray = blockArray;
	}

	@Override
	public Block[][] createBlocks() {
		Block[][] blocks = new Block[this.blockArray.length][this.blockArray[0].length];
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[0].length; y++) {
				if (this.blockArray[x][y]) {
					blocks[x][y] = this.block;
				}
			}
		}
		return blocks;
	}

}
