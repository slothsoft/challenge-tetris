package de.slothsoft.tetris;

public interface StoneFactory {

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Creates a normal block
	 *
	 * @return a stone with its blocks
	 */

	Stone createRandomStone();

}
