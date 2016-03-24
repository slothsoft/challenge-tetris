package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.blocks.SingleColorBlock;

public class BoardTest {

	@Test
	public void testCalculateBlockCountOfLine() throws Exception {
		Board board = new Board();
		board.setBlock(0, 1, new SingleColorBlock(Color.RED));
		board.setBlock(0, 2, new SingleColorBlock(Color.RED));
		board.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, board.calculateBlockCountOfLine(0));
		Assert.assertEquals(1, board.calculateBlockCountOfLine(1));
		Assert.assertEquals(2, board.calculateBlockCountOfLine(2));
	}

	@Test
	public void testCalculateBlockCountOfColumn() throws Exception {
		Board board = new Board();
		board.setBlock(1, 0, new SingleColorBlock(Color.RED));
		board.setBlock(2, 0, new SingleColorBlock(Color.RED));
		board.setBlock(2, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, board.calculateBlockCountOfColumn(0));
		Assert.assertEquals(1, board.calculateBlockCountOfColumn(1));
		Assert.assertEquals(2, board.calculateBlockCountOfColumn(2));
	}

	@Test
	public void testWidth() throws Exception {
		Board board = new Board();

		Assert.assertEquals(Board.WIDTH_IN_BLOCKS, board.getWidthInBlocks());
		Assert.assertEquals(Board.WIDTH_IN_BLOCKS * Block.WIDTH_IN_PIXELS, board.getWidthInPixels());
	}

	@Test
	public void testHeight() throws Exception {
		Board board = new Board();

		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS, board.getHeightInBlocks());
		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS * Block.HEIGHT_IN_PIXELS, board.getHeightInPixels());
	}

	@Test
	public void testClone() throws Exception {
		Board board = new Board();
		board.setBlock(0, 1, new SingleColorBlock(Color.RED));
		board.setBlock(0, 2, new SingleColorBlock(Color.RED));
		board.setBlock(1, 2, new SingleColorBlock(Color.RED));

		BlockArray clone = board.clone();

		Assert.assertArrayEquals(board.getBlocks(), clone.getBlocks());
		Assert.assertNotSame(board.getBlocks(), clone.getBlocks());

		for (int i = 0; i < board.getBlocks().length; i++) {
			Assert.assertArrayEquals(board.getBlocks()[i], clone.getBlocks()[i]);
			Assert.assertNotSame(board.getBlocks()[i], clone.getBlocks()[i]);
		}
	}

	@Test
	public void testClear() throws Exception {
		Board board = new Board();
		board.setBlock(0, 1, new SingleColorBlock(Color.RED));
		board.setBlock(0, 2, new SingleColorBlock(Color.RED));
		board.setBlock(1, 2, new SingleColorBlock(Color.RED));

		board.clear();

		for (Block[] row : board.getBlocks()) {
			for (Block block : row) {
				Assert.assertEquals(null, block);
			}
		}
	}

	@Test
	public void testPutBlocks() throws Exception {
		Board board = new Board();

		BlockArray array = new BlockArray(2, 3);
		array.setBlock(0, 1, new SingleColorBlock(Color.RED));
		array.setBlock(0, 2, new SingleColorBlock(Color.RED));
		array.setBlock(1, 2, new SingleColorBlock(Color.RED));

		board.putBlocks(5, 7, array);

		Assert.assertNotNull(board.getBlock(5, 8));
		Assert.assertNotNull(board.getBlock(5, 9));
		Assert.assertNotNull(board.getBlock(6, 9));
	}
}
