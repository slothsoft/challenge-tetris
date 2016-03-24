package de.slothsoft.tetris;

/**
 * A factory for {@link Stone}s
 */

public interface StoneFactory {

	/**
	 * Returns a display name that should be unique
	 * 
	 * @return a unique display name
	 */

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Creates a random stone
	 *
	 * @return a stone with its blocks
	 */

	Stone createRandomStone();

}
