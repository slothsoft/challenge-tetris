package de.slothsoft.tetris.geneticalgorithm;

import de.slothsoft.tetris.Game;
import de.slothsoft.tetris.Score;
import de.slothsoft.tetris.StoneFactory;
import de.slothsoft.tetris.StonePositioner;
import de.slothsoft.tetris.TetrisBatch;
import de.slothsoft.tetris.blocks.DefaultStoneFactory;
import de.slothsoft.tetris.contrib.GeneticAlgorithmStonePositioner;

public class GeneticAlgorithmTetrisBatch {
	private static final StonePositioner POSITIONER = new GeneticAlgorithmStonePositioner();
	private static final int THREADS = 4;
	private static final int NUMBER_OF_ROUNDS = 1000;
	private static final StoneFactory STONE_FACTORY = DefaultStoneFactory.DEFAULT;

	public static void main(String[] args) throws Exception {
		TetrisBatch batch = new TetrisBatch(POSITIONER);
		batch.threads(THREADS).numberOfRounds(NUMBER_OF_ROUNDS);
		batch.setOnRoundFinish((game, score) -> onRoundFinished(game, score, batch.getFinishedRounds()));
		batch.setOnBatchFinish(totalScore -> onBatchFinished(totalScore, batch.getFinishedRounds()));
		batch.stoneFactory(STONE_FACTORY);
		batch.start();
	}

	private static void onRoundFinished(Game game, Score score, int finishedRounds) {
		if (finishedRounds > 0 && (finishedRounds % (NUMBER_OF_ROUNDS / 10) == 0)) {
			System.out.println(finishedRounds + " rounds finished.");
		}
	}

	private static void onBatchFinished(Score totalScore, int finishedRounds) {
		System.out.println("============================================");
		System.out.println(totalScore.toString(true));
		System.out.println("--------------------------------------------");
		System.out.println("Average:");
		System.out.println("\tstoneCount\t= " + totalScore.getStoneCount() / finishedRounds);
		System.out.println("\tlinesRemoved\t= " + totalScore.getLinesRemoved() / finishedRounds);
		System.out.println("\tscore\t\t= " + totalScore.getScore() / finishedRounds);
		System.out.println("============================================");
	}
}
