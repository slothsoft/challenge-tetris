package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class StonePositionerUtilTest {

	@Test
	public void testCalculateFutureY() {
		Board board = new Board();
		board.setBlock(3, 15, new Block(Color.BLUE));

		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS - 3,
				StonePositionerUtil.calculateFutureY(board.getBlocks(), stone, 0));
		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS - 3,
				StonePositionerUtil.calculateFutureY(board.getBlocks(), stone, 1));
		Assert.assertEquals(13, StonePositionerUtil.calculateFutureY(board.getBlocks(), stone, 2));
		Assert.assertEquals(14, StonePositionerUtil.calculateFutureY(board.getBlocks(), stone, 3));
		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS - 3,
				StonePositionerUtil.calculateFutureY(board.getBlocks(), stone, 5));
	}

	@Test
	public void testFutureLinesVanished() {
		Board board = new Board();
		board.setBlock(3, 15, new Block(Color.BLUE));

		Block[][] blocks = board.getBlocks();
		for (int x = 1; x < blocks.length; x++) {
			if (x > 1) {
				board.setBlock(x, 7, new Block(Color.BLUE));
			}
			board.setBlock(x, 8, new Block(Color.BLUE));
		}

		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(2, StonePositionerUtil.getFutureLinesVanished(board, stone, 7));
		stone.setBlock(1, 0, null);
		Assert.assertEquals(1, StonePositionerUtil.getFutureLinesVanished(board, stone, 7));

		Assert.assertEquals(0, StonePositionerUtil.getFutureLinesVanished(board, stone, 0));
	}

	@Test
	public void testHoleCount() {
		Board board = new Board();

		Stone stone = createStone(2, 2);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(1,
				StonePositionerUtil.getHoleCount(board.getBlocks(), stone, 0, Board.HEIGHT_IN_BLOCKS - 2));
		Assert.assertEquals(3,
				StonePositionerUtil.getHoleCount(board.getBlocks(), stone, 0, Board.HEIGHT_IN_BLOCKS - 3));
		stone.setBlock(1, 1, null);
		Assert.assertEquals(2,
				StonePositionerUtil.getHoleCount(board.getBlocks(), stone, 0, Board.HEIGHT_IN_BLOCKS - 2));

		board.setBlock(0, Board.HEIGHT_IN_BLOCKS - 1, new Block(Color.WHITE));
		board.setBlock(1, Board.HEIGHT_IN_BLOCKS - 1, new Block(Color.WHITE));
		Assert.assertEquals(0,
				StonePositionerUtil.getHoleCount(board.getBlocks(), stone, 0, Board.HEIGHT_IN_BLOCKS - 2));
	}

	@Test
	public void testLastYOfStone() {
		Stone stone = createStone(2, 2);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(1, StonePositionerUtil.getLastYOfStone(stone, 0));
		Assert.assertEquals(2, StonePositionerUtil.getLastYOfStone(stone, 1));

		stone = createStone(3, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(1, StonePositionerUtil.getLastYOfStone(stone, 0));
		Assert.assertEquals(2, StonePositionerUtil.getLastYOfStone(stone, 1));
		Assert.assertEquals(0, StonePositionerUtil.getLastYOfStone(stone, 2));
	}

	private Stone createStone(int widthInBlocks, int heightInBlocks) {
		return new Stone(new Block[widthInBlocks][heightInBlocks]);
	}
}
