package de.slothsoft.tetris;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CompleteLineUpdaterTest {

	private final CompleteLineUpdater updater = new CompleteLineUpdater();

	@Test
	public void test() {
		Board board = new Board();
		Block block = new Block(Color.RED);
		Block[][] blocks = board.getBlocks();
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[x].length; y++) {
				board.setBlock(x, y, block);
			}
		}

		this.updater.update(board);

		blocks = board.getBlocks();
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[x].length; y++) {
				Assert.assertNull(blocks[x][y]);
			}
		}
	}

	@Test
	public void testLinesToBeRemoved() {
		Board board = new Board();
		Block block = new Block(Color.RED);
		Block[][] blocks = board.getBlocks();

		for (int x = 0; x < blocks.length; x++) {
			board.setBlock(x, 7, block);
		}

		List<Integer> lines = CompleteLineUpdater.getLinesToBeRemoved(board);
		Assert.assertEquals(Arrays.asList(7), lines);
	}

	@Test
	public void testShouldLineBeRemoved() {
		Board board = new Board();
		Block block = new Block(Color.RED);
		Block[][] blocks = board.getBlocks();

		for (int x = 0; x < blocks.length; x++) {
			board.setBlock(x, 7, block);
		}

		Assert.assertEquals(false, CompleteLineUpdater.shouldLineBeRemoved(board, 6));
		Assert.assertEquals(true, CompleteLineUpdater.shouldLineBeRemoved(board, 7));
		Assert.assertEquals(false, CompleteLineUpdater.shouldLineBeRemoved(board, 8));
	}

	@Test
	public void testRemoveLine() {
		Board board = new Board();
		Block block = new Block(Color.RED);
		Block[][] blocks = board.getBlocks();

		for (int x = 0; x < blocks.length; x++) {
			board.setBlock(x, 7, block);
		}
		board.setBlock(6, 6, block);
		board.setBlock(5, 5, block);
		board.setBlock(4, 4, block);

		this.updater.removeLine(board, 7);

		Assert.assertSame(block, board.getBlock(6, 7));
		Assert.assertSame(block, board.getBlock(5, 6));
		Assert.assertSame(block, board.getBlock(4, 5));
	}

	@Test
	public void testOnLinesRemoved() {
		Board board = new Board();
		Block block = new Block(Color.RED);
		Block[][] blocks = board.getBlocks();

		for (int x = 0; x < blocks.length; x++) {
			board.setBlock(x, 7, block);
		}

		int[] result = new int[] { 0 };
		this.updater.setOnLinesRemoved(i -> result[0] = i[0]);
		this.updater.update(board);

		Assert.assertEquals(7, result[0]);
	}
}
