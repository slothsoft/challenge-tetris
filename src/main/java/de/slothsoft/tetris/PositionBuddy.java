package de.slothsoft.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import de.slothsoft.tetris.PositionBasedStonePositioner.Position;

/**
 * Helper class for positioning stones. Just give it something to calculate the
 * score with, and it will do all the magic to find the right position itself
 * 
 * @since 1.0.0
 */

public class PositionBuddy {

	private static final int MAXIMUM_ROTATIONS = 4;
	private static final Random RANDOM = new Random(42L);

	private final Scorer scorer;

	private Stone[] rotatedStones;

	public PositionBuddy(Scorer scorer) {
		this.scorer = Objects.requireNonNull(scorer);
	}

	public Position calculateTargetPosition(Stone incomingStone) {
		this.rotatedStones = createRotatedStones(incomingStone);
		int[][] positionScores = calculatePositionScores();
		return findHighestScore(positionScores);
	}

	private Stone[] createRotatedStones(Stone stone) {
		Set<String> rotatedStonesStrings = new TreeSet<>();
		rotatedStonesStrings.add(stone.stringify());

		Stone[] rotatedStones = new Stone[MAXIMUM_ROTATIONS];
		rotatedStones[0] = stone;
		for (int i = 1; i < rotatedStones.length; i++) {
			Stone rotatedStone = rotatedStones[i - 1].createLeftRotation();
			String rotatedStoneString = rotatedStone.stringify();
			if (rotatedStonesStrings.contains(rotatedStoneString)) {
				break;
			} else {
				rotatedStones[i] = rotatedStone;
				rotatedStonesStrings.add(rotatedStoneString);
			}
		}
		return rotatedStones;
	}

	private int[][] calculatePositionScores() {
		int[][] positionScores = new int[Board.WIDTH_IN_BLOCKS][MAXIMUM_ROTATIONS];
		for (int rotationCount = 0; rotationCount < MAXIMUM_ROTATIONS; rotationCount++) {
			for (int blockX = 0; blockX < Board.WIDTH_IN_BLOCKS; blockX++) {
				positionScores[blockX][rotationCount] = calculatePositionScore(blockX, rotationCount);
			}
		}
		return positionScores;
	}

	private int calculatePositionScore(int blockX, int rotationCount) {
		if (this.rotatedStones[rotationCount] == null)
			return Integer.MIN_VALUE;
		if (blockX + this.rotatedStones[rotationCount].getWidthInBlocks() > Board.WIDTH_IN_BLOCKS)
			return Integer.MIN_VALUE;
		if (blockX < 0)
			return Integer.MIN_VALUE;
		return this.scorer.calculateScore(this.rotatedStones[rotationCount], blockX);
	}

	private Position findHighestScore(int[][] positionScores) {
		int maximumScore = getMaximumScore(positionScores);
		List<Position> maximumScorePositions = getMaximumScorePositions(positionScores, maximumScore);
		return maximumScorePositions.get(RANDOM.nextInt(maximumScorePositions.size()));
	}

	private static int getMaximumScore(int[][] positionScores) {
		return Arrays.stream(positionScores).flatMapToInt(s -> Arrays.stream(s)).max().getAsInt();
	}

	private List<Position> getMaximumScorePositions(int[][] positionScores, int maximumScore) {
		List<Position> result = new ArrayList<>();
		for (int blockX = 0; blockX < Board.WIDTH_IN_BLOCKS; blockX++) {
			for (int rotationCount = 0; rotationCount < MAXIMUM_ROTATIONS; rotationCount++) {
				if (positionScores[blockX][rotationCount] == maximumScore) {
					result.add(new Position().xInBlocks(blockX).leftRotation(rotationCount));
				}
			}
		}
		return result;
	}

	/*
	 *
	 */

	public static interface Scorer {

		/**
		 * The highest score wins
		 */

		int calculateScore(Stone stone, int targetX);
	}
}
