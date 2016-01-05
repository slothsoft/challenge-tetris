package de.slothsoft.tetris;

import java.awt.Color;
import java.util.Random;

public enum StoneForm {
	I("00FF00") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true, true, true } };
			return createBlocks(blockArray, this);
		}

	},
	L("5555FF") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true, true }, { false, false, true } };
			return createBlocks(blockArray, this);
		}

	},
	J("000099") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true, true }, { true, false, false } };
			return createBlocks(blockArray, this);
		}

	},
	O("EEEE00") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true }, { true, true } };
			return createBlocks(blockArray, this);
		}

	},
	Z("FF8800") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { false, true, true }, { true, true, false } };
			return createBlocks(blockArray, this);
		}

	},
	T("FF00FF") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true, true }, { false, true, false } };
			return createBlocks(blockArray, this);
		}

	},
	S("FF0000") {

		@Override
		public Block[][] createBlocks() {
			boolean[][] blockArray = { { true, true, false }, { false, true, true } };
			return createBlocks(blockArray, this);
		}

	},
	; // $NON-NLS-1$

	private static final int HEXADECIMAL = 16;
	private static final int MAX_COLOR_INT = 256;
	private static final Random RANDOM = new Random();
	static final StoneForm[] VALUES = values();

	public static Stone createRandomStone() {
		return new Stone(VALUES[RANDOM.nextInt(VALUES.length)]);
	}

	private Block block;

	private StoneForm(String colorHex) {
		this.block = new SingleColorBlock(createColor(colorHex));
	}

	private static Color createColor(String colorHex) {
		int colorInt = Integer.parseInt(colorHex, HEXADECIMAL);
		int red = colorInt / MAX_COLOR_INT / MAX_COLOR_INT;
		int green = (colorInt / MAX_COLOR_INT) % MAX_COLOR_INT;
		int blue = colorInt % MAX_COLOR_INT;
		return new Color(red, green, blue);
	}

	public abstract Block[][] createBlocks();

	private static Block[][] createBlocks(boolean[][] blockArray, StoneForm form) {
		Block[][] blocks = new Block[blockArray.length][blockArray[0].length];
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[0].length; y++) {
				if (blockArray[x][y]) {
					blocks[x][y] = form.block;
				}
			}
		}
		return blocks;
	}

	public Block getBlock() {
		return this.block;
	}
}
