package de.slothsoft.tetris.geneticalgorithm;

import de.slothsoft.tetris.StonePositioner;
import de.slothsoft.tetris.TetrisArguments;
import de.slothsoft.tetris.contrib.GeneticAlgorithmStonePositioner;
import de.slothsoft.tetris.gui.TetrisFrame;

/**
 * This class starts a standard game of Tetris to watch how the AI works
 * 
 * @since 1.0.0
 */

public class GeneticAlgorithmTetris {

	public static final StonePositioner POSITIONER = new GeneticAlgorithmStonePositioner();

	public static void main(String[] args) {
		TetrisArguments.check(args);

		TetrisFrame tetris = new TetrisFrame();
		tetris.setStonePositioner(TetrisArguments.getStonePositioner(args, POSITIONER));
		tetris.setShowSettings(TetrisArguments.isShowSettings(args, true));
		tetris.setTimePerStone(TetrisArguments.getTimePerStone(args, tetris.getTimePerStone()));
		tetris.start();
	}

}
