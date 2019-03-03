package de.slothsoft.tetris;

import java.util.List;

import de.slothsoft.challenger.core.Contributions;
import de.slothsoft.tetris.contrib.ExampleStonePositioner;

/**
 * Util class for getting and managing {@link StonePositioner}s (hence the name)
 *
 * @since 1.0.0
 */

public final class StonePositioners {

	/**
	 * Returns all instances of {@link StonePositioner}s in the <code>contrib</code>
	 * package.
	 *
	 * @return a list of stone positioners
	 */

	public static List<StonePositioner> getStonePositioners() {
		return Contributions.fetchImplementations(ExampleStonePositioner.class.getPackage(), StonePositioner.class);
	}

	private StonePositioners() {
		// hide me
	}
}
