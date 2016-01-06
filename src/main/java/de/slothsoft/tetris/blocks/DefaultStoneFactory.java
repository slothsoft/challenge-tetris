package de.slothsoft.tetris.blocks;

import java.util.Random;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Stone;
import de.slothsoft.tetris.StoneFactory;

public enum DefaultStoneFactory implements StoneFactory {
	DEFAULT("Default Stones", StoneForm.values()),

	FUNKY("Funky Stones", FunkyStoneForm.values()),

	;

	private static final Random RANDOM = new Random();

	private final String displayName;
	private final StoneProviderEnum[] values;

	private DefaultStoneFactory(String displayName, StoneProviderEnum[] values) {
		this.displayName = displayName;
		this.values = values;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public Stone createRandomStone() {
		return new Stone(this.values[RANDOM.nextInt(this.values.length)].createBlocks());
	}

	/*
	 *
	 */

	static interface StoneProviderEnum {

		Block[][] createBlocks();

	}
}
