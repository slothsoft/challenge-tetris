package de.slothsoft.tetris.gui;

import java.util.Arrays;
import java.util.List;

public final class TetrisRenderers {

	public static List<TetrisRenderer> getTetrisRenderers() {
		return Arrays.asList(TetrisRenderer.DEFAULT, new FunkyTetrisRenderer(), new SingleColorTetrisRenderer());
	}

	private TetrisRenderers() {
		// hide me
	}

}
