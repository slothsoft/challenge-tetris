package de.slothsoft.tetris.contrib;

import java.util.Random;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Board;
import de.slothsoft.tetris.PositionBasedStonePositioner;
import de.slothsoft.tetris.PositionBuddy;
import de.slothsoft.tetris.Stone;
import de.slothsoft.tetris.StonePositionerUtil;

public class ExampleStonePositioner extends AbstractStonePositioner implements PositionBasedStonePositioner {

	private static final Random RANDOM = new Random(7L);

	private final PositionBuddy positionBuddy = new PositionBuddy(this::calculatePositionScore);
	private boolean wantsPoleOnTheRight = RANDOM.nextBoolean();

	private Board board;
	private Block[][] boardBlocks;
	private Stone stone;

	@Override
	public String getDisplayName() {
		return "Example";
	}

	@Override
	public Position calculateTargetPosition(Context context) {
		if (this.stone == context.getCurrentStone()) return new Position().xInBlocks(this.stone.getXInBlocks());

		this.board = context.getBoard();
		this.boardBlocks = context.getBoard().getBlocks();
		this.stone = context.getCurrentStone();

		return this.positionBuddy.calculateTargetPosition(this.stone);
	}

	private int calculatePositionScore(Stone rotatedStone, int blockX) {
		int blockY = StonePositionerUtil.calculateFutureY(this.boardBlocks, rotatedStone, blockX);
		if (blockY < 0) return Integer.MIN_VALUE;
		return calculateScore(rotatedStone, blockX, blockY);
	}

	protected int calculateScore(Stone stone, int blockX, int blockY) {
		int score = 0;

		Board newBoard = this.board.clone();
		newBoard.putBlocks(blockX, blockY, stone);

		// points for the height differences
		score += getPointsForHeightDifferences(newBoard);
		// minus points for any holes under the rotated stone. this is major!
		score -= 8 * StonePositionerUtil.getHoleCount(newBoard.getBlocks(), stone, blockX, blockY);
		// points for vanishing lines
		score += 2 * StonePositionerUtil.getFutureLinesVanished(newBoard, stone, blockY);
		// minus points for the left and right column (so we might see 4 rows vanishing)
		score += getPointsForX(stone, blockX);
		// points for the y coordinate so as to prefer the lowest point
		score += getPointsForY(blockY);
		// points for flat objects
		score += getPointsForFlatObjects(stone);

		return score;
	}

	protected void printScore(Stone stone, int blockX, int blockY) {
		Board newBoard = this.board.clone();
		newBoard.putBlocks(blockX, blockY, stone);

		System.out.println("------------------------------------------------------");
		System.out.println(stone.stringify() + blockX);
		System.out.println("getPointsForHeightDifferences = " + getPointsForHeightDifferences(newBoard));
		System.out.println("getHoleCount = " + -8
				* StonePositionerUtil.getHoleCount(newBoard.getBlocks(), stone, blockX, blockY));
		System.out.println("getFutureLinesVanished = "
				+ StonePositionerUtil.getFutureLinesVanished(newBoard, stone, blockY));
		System.out.println("getPointsForX = " + getPointsForX(stone, blockX));
		System.out.println("getPointsForY = " + getPointsForY(blockY));
		System.out.println("getPointsForFlatObjects = " + getPointsForFlatObjects(stone));
		System.out.println("total = " + calculateScore(stone, blockX, blockY));
	}

	protected static int getPointsForHeightDifferences(Board board) {
		int points = 0;
		int lastHeight = -1;
		for (int x = 0; x < Board.WIDTH_IN_BLOCKS; x++) {
			int height = getTopStoneY(board, x);
			if (x >= 1 && x < Board.WIDTH_IN_BLOCKS - 1) {
				points += getPointsForHeightDifference(lastHeight, height);
			}
			lastHeight = height;
		}
		return points;
	}

	protected static int getTopStoneY(Board board, int x) {
		for (int y = 0; y < Board.HEIGHT_IN_BLOCKS; y++) {
			if (board.getBlock(x, y) != null) return y;
		}
		return Board.HEIGHT_IN_BLOCKS;
	}

	protected static int getPointsForHeightDifference(int lastHeight, int height) {
		// 0 difference is good (+1) | 1 difference is ok (+-0) | more than 1 difference
		// is bad (-x)
		return 1 - Math.abs(lastHeight - height);
	}

	protected int getPointsForX(Stone rotatedStone, int blockX) {
		if (blockX == 0 && !this.wantsPoleOnTheRight) return -1;
		if (blockX == Board.WIDTH_IN_BLOCKS - rotatedStone.getWidthInBlocks() && this.wantsPoleOnTheRight) return -1;
		return 0;
	}

	protected static int getPointsForY(int blockY) {
		return blockY - Board.HEIGHT_IN_BLOCKS;
	}

	protected static int getPointsForFlatObjects(Stone rotatedStone) {
		return rotatedStone.getWidthInBlocks() > rotatedStone.getHeightInBlocks() ? 1 : 0;
	}

	public boolean isWantsPoleOnTheRight() {
		return this.wantsPoleOnTheRight;
	}

	public ExampleStonePositioner wantsPoleOnTheRight(boolean newWantsPoleOnTheRight) {
		setWantsPoleOnTheRight(newWantsPoleOnTheRight);
		return this;
	}

	public void setWantsPoleOnTheRight(boolean wantsPoleOnTheRight) {
		this.wantsPoleOnTheRight = wantsPoleOnTheRight;
	}

}
