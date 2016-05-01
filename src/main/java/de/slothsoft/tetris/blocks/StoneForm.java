package de.slothsoft.tetris.blocks;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.blocks.DefaultStoneFactory.StoneProviderEnum;

/**
 * These stones are pretty similar to the default tetris stones
 * 
 * @since 1.0.0
 */

public enum StoneForm implements StoneProviderEnum {
	I("00FF00", new boolean[][] { { true, true, true, true } }),

	L("5555FF", new boolean[][] { { true, true, true }, { false, false, true } }),

	J("000099", new boolean[][] { { true, true, true }, { true, false, false } }),

	O("EEEE00", new boolean[][] { { true, true }, { true, true } }),

	Z("FF8800", new boolean[][] { { false, true, true }, { true, true, false } }),

	T("FF00FF", new boolean[][] { { true, true, true }, { false, true, false } }),

	S("FF0000", new boolean[][] { { true, true, false }, { false, true, true } }),

	; // $NON-NLS-1$

	private final Block block;
	private final boolean[][] blockArray;

	private StoneForm(String colorHex, boolean[][] blockArray) {
		this.block = new SingleColorBlock(BlockUtil.createColor(colorHex));
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
