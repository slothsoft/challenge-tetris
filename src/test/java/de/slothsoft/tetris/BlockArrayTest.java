package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.blocks.SingleColorBlock;

public class BlockArrayTest {

	@Test
	public void testCalculateBlockCountOfLine() throws Exception {
		BlockArray array = new BlockArray(2, 3);
		array.setBlock(0, 1, new SingleColorBlock(Color.RED));
		array.setBlock(0, 2, new SingleColorBlock(Color.RED));
		array.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, array.calculateBlockCountOfLine(0));
		Assert.assertEquals(1, array.calculateBlockCountOfLine(1));
		Assert.assertEquals(2, array.calculateBlockCountOfLine(2));
	}

	@Test
	public void testCalculateBlockCountOfColumn() throws Exception {
		BlockArray array = new BlockArray(3, 2);
		array.setBlock(1, 0, new SingleColorBlock(Color.RED));
		array.setBlock(2, 0, new SingleColorBlock(Color.RED));
		array.setBlock(2, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, array.calculateBlockCountOfColumn(0));
		Assert.assertEquals(1, array.calculateBlockCountOfColumn(1));
		Assert.assertEquals(2, array.calculateBlockCountOfColumn(2));
	}

	@Test
	public void testWidth() throws Exception {
		BlockArray array = new BlockArray(3, 2);

		Assert.assertEquals(3, array.getWidthInBlocks());
		Assert.assertEquals(3 * Block.WIDTH, array.getWidthInPixels());
	}

	@Test
	public void testHeight() throws Exception {
		BlockArray array = new BlockArray(3, 2);

		Assert.assertEquals(2, array.getHeightInBlocks());
		Assert.assertEquals(2 * Block.HEIGHT, array.getHeightInPixels());
	}

	@Test
	public void testClone() throws Exception {
		BlockArray array = new BlockArray(2, 3);
		array.setBlock(0, 1, new SingleColorBlock(Color.RED));
		array.setBlock(0, 2, new SingleColorBlock(Color.RED));
		array.setBlock(1, 2, new SingleColorBlock(Color.RED));

		BlockArray clone = array.clone();

		Assert.assertArrayEquals(array.getBlocks(), clone.getBlocks());
		Assert.assertNotSame(array.getBlocks(), clone.getBlocks());

		for (int i = 0; i < array.getBlocks().length; i++) {
			Assert.assertArrayEquals(array.getBlocks()[i], clone.getBlocks()[i]);
			Assert.assertNotSame(array.getBlocks()[i], clone.getBlocks()[i]);
		}
	}
}
