package de.slothsoft.tetris;

import de.slothsoft.tetris.contrib.ExampleStonePositioner;
import de.slothsoft.tetris.gui.TetrisFrame;

/**
 * This class starts a standard game of Tetris to watch how the AI works
 */

public class Tetris {

	public static final StonePositioner POSITIONER = new ExampleStonePositioner();

	public static void main(String[] args) {
		TetrisFrame tetris = new TetrisFrame();
		tetris.start();
	}

}
