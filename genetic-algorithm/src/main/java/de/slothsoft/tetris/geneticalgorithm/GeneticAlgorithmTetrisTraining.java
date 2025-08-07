package de.slothsoft.tetris.geneticalgorithm;

import java.util.Random;

import de.slothsoft.tetris.Score;
import de.slothsoft.tetris.StoneFactory;
import de.slothsoft.tetris.TetrisBatch;
import de.slothsoft.tetris.blocks.DefaultStoneFactory;
import de.slothsoft.tetris.contrib.GeneticAlgorithmStonePositioner;
import de.slothsoft.tetris.geneticalgorithm.impl.Weight;

public class GeneticAlgorithmTetrisTraining {
	private static final int THREADS = 4;
	private static final int NUMBER_OF_ROUNDS = 1000;
	private static final StoneFactory STONE_FACTORY = DefaultStoneFactory.DEFAULT;

	public static void main(String[] args) throws Exception {
		GeneticAlgorithmTetrisTraining training = new GeneticAlgorithmTetrisTraining();
		training.start();
	}

	private final Random random = new Random();
	private Weight currentWeight;
	private Score currentScore;
	private int batchNumber = 0;
	private int mutationsWithoutChanges = 0;
	
	public GeneticAlgorithmTetrisTraining() {
		currentWeight = Weight.load();
	}
	
	public void start() {
		TetrisBatch batch = createBatch(currentWeight);
		batch.setOnBatchFinish(totalScore -> {
			currentScore = totalScore;
			System.out.println("--------------------------------------------");
			mutateAndRestartBatch(batch.getFinishedRounds());
		});
		batch.start();
	}
	
	private static TetrisBatch createBatch(Weight weight) {
		TetrisBatch batch = new TetrisBatch(new GeneticAlgorithmStonePositioner(weight));
		batch.threads(THREADS).numberOfRounds(NUMBER_OF_ROUNDS);
		batch.stoneFactory(STONE_FACTORY);
		return batch;
	}

	private void mutateAndRestartBatch(int lastFinishedRounds) {
		if (mutationsWithoutChanges >= 100) {
			return;
		}
		
		Weight mutatedWeight = mutateCurrentWeight();

		TetrisBatch batch = createBatch(mutatedWeight);
		batch.setOnBatchFinish(mutatedScore -> {
			if (currentScore.getScore() < mutatedScore.getScore()) {
				// the new mutation is better
				mutationsWithoutChanges = 0;
				currentScore = mutatedScore;
				currentWeight = mutatedWeight;
				currentWeight.save();
				logScore(batch.getFinishedRounds());
				mutateAndRestartBatch(batch.getFinishedRounds());
			} else {
				// the last mutation was better
				mutationsWithoutChanges++;
				mutateAndRestartBatch(batch.getFinishedRounds());
			}
		});
		batch.start();
	}

	private void logScore(int finishedRounds) {
		System.out.println("--------------------------------------------");
		System.out.println(currentWeight.toJsonString());
		System.out.println("--------------------------------------------");
		System.out.println("Average (batch " + (batchNumber++) + ", " + mutationsWithoutChanges + " mutations without changes):");
		System.out.println("\tstoneCount\t= " + currentScore.getStoneCount() / finishedRounds);
		System.out.println("\tlinesRemoved\t= " + currentScore.getLinesRemoved() / finishedRounds);
		System.out.println("\tscore\t\t= " + currentScore.getScore() / finishedRounds);
		System.out.println("============================================");
	}
	
	private Weight mutateCurrentWeight() {
		Weight result = currentWeight.copy();
		result.heightDifferences += mutateNextValue();
		result.holeCount += mutateNextValue();
		result.futureLinesVanished += mutateNextValue();
		result.pointsForX += mutateNextValue();
		result.pointsForY += mutateNextValue();
		result.pointsForFlatObjects += mutateNextValue();
		return result;
	}

	// we want to mutate about 1 field at once (0 means no mutation)
	// if no mutation was better in a long time, we also want to do stronger changes
	
	private double mutateNextValue() {
		final int fieldCount = 6;
		final double part = 1.0 / fieldCount;
		int changeMultiplicator = mutationsWithoutChanges < 10 ? 1 : (mutationsWithoutChanges / 10);
		return random.nextDouble() <= part ? changeMultiplicator * random.nextGaussian() : 0;
	}
}
