package de.slothsoft.tetris.contrib;

/**
 * Just to show how a push request works. This stone positioner does nothing at
 * all.
 * 
 * @author Unkenfänger <unkenfaenger@private.com>
 * @since 2016-01-05
 * @version 0.9.0
 */

public class MyGreatStonePositioner extends AbstractStonePositioner {

	@Override
	public String getDisplayName() {
		return "My Great Stone Positioner";
	}

	@Override
	public void position(Context context) {
		// maybe it's not that great
	}

}
