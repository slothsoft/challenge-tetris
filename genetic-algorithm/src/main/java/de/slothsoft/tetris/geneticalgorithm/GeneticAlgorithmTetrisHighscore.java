package de.slothsoft.tetris.geneticalgorithm;

import de.slothsoft.tetris.Score;
import de.slothsoft.tetris.StonePositioner;
import de.slothsoft.tetris.TetrisHighscore;

/**
 * This class starts a bunch of games for every contribution without any GUI to
 * see how the AI performs compared to other AIs
 * 
 * @since 1.0.0
 */

public class GeneticAlgorithmTetrisHighscore {

	private static final int THREADS = 4;
	private static final int NUMBER_OF_ROUNDS = 1000;
	
	public static void main(String[] args) throws Exception {
		TetrisHighscore batch = new TetrisHighscore();
		batch.threads(THREADS).numberOfRounds(NUMBER_OF_ROUNDS);
		batch.setOnGameFinish(GeneticAlgorithmTetrisHighscore::onBatchFinished);
		batch.start();
	}

	private static void onBatchFinished(StonePositioner positioner, Score totalScore, int finishedRounds) {
		System.out.print(positioner.getDisplayName() + ":");
		System.out.print("\t\tstoneCount = " + totalScore.getStoneCount() / finishedRounds);
		System.out.print("\t\tlinesRemoved = " + totalScore.getLinesRemoved() / finishedRounds);
		System.out.print("\t\tscore = " + totalScore.getScore() / finishedRounds);
		System.out.println();
	}
}
