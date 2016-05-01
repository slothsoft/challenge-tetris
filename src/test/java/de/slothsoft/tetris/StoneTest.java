package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.blocks.SingleColorBlock;

public class StoneTest {

	@Test
	public void testCalculateBlockCountOfLine() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, stone.calculateBlockCountOfLine(0));
		Assert.assertEquals(1, stone.calculateBlockCountOfLine(1));
		Assert.assertEquals(2, stone.calculateBlockCountOfLine(2));
	}

	private Stone createStone(int widthInBlocks, int heightInBlocks) {
		return new Stone(new Block[widthInBlocks][heightInBlocks]);
	}

	@Test
	public void testCalculateBlockCountOfColumn() throws Exception {
		Stone stone = createStone(3, 2);
		stone.setBlock(1, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(2, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(2, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(0, stone.calculateBlockCountOfColumn(0));
		Assert.assertEquals(1, stone.calculateBlockCountOfColumn(1));
		Assert.assertEquals(2, stone.calculateBlockCountOfColumn(2));
	}

	@Test
	public void testWidth() throws Exception {
		Stone stone = createStone(3, 2);

		Assert.assertEquals(3, stone.getWidthInBlocks());
		Assert.assertEquals(3 * Block.WIDTH_IN_PIXELS, stone.getWidthInPixels());
	}

	@Test
	public void testHeight() throws Exception {
		Stone stone = createStone(3, 2);

		Assert.assertEquals(2, stone.getHeightInBlocks());
		Assert.assertEquals(2 * Block.HEIGHT_IN_PIXELS, stone.getHeightInPixels());
	}

	@Test
	public void testClone() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		BlockArray clone = stone.clone();

		Assert.assertArrayEquals(stone.getBlocks(), clone.getBlocks());
		Assert.assertNotSame(stone.getBlocks(), clone.getBlocks());

		for (int i = 0; i < stone.getBlocks().length; i++) {
			Assert.assertArrayEquals(stone.getBlocks()[i], clone.getBlocks()[i]);
			Assert.assertNotSame(stone.getBlocks()[i], clone.getBlocks()[i]);
		}
	}

	@Test
	public void testCreateBlocksForLeftRotation() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Block[][] newBlocks = stone.createBlocksForLeftRotation();
		BlockArray newArray = new BlockArray(newBlocks);

		Assert.assertEquals(3, newArray.getWidthInBlocks());
		Assert.assertEquals(2, newArray.getHeightInBlocks());

		Assert.assertNotNull(newArray.getBlock(1, 1));
		Assert.assertNotNull(newArray.getBlock(2, 0));
		Assert.assertNotNull(newArray.getBlock(2, 1));

		Assert.assertNull(newArray.getBlock(0, 0));
		Assert.assertNull(newArray.getBlock(0, 1));
		Assert.assertNull(newArray.getBlock(1, 0));
	}

	@Test
	public void testCreateLeftRotation() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Stone newStone = stone.createLeftRotation();

		Assert.assertEquals(3, newStone.getWidthInBlocks());
		Assert.assertEquals(2, newStone.getHeightInBlocks());

		Assert.assertNotNull(newStone.getBlock(1, 1));
		Assert.assertNotNull(newStone.getBlock(2, 0));
		Assert.assertNotNull(newStone.getBlock(2, 1));

		Assert.assertNull(newStone.getBlock(0, 0));
		Assert.assertNull(newStone.getBlock(0, 1));
		Assert.assertNull(newStone.getBlock(1, 0));
	}

	@Test
	public void testRotateLeft() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		stone.rotateLeft();

		Assert.assertEquals(3, stone.getWidthInBlocks());
		Assert.assertEquals(2, stone.getHeightInBlocks());

		Assert.assertNotNull(stone.getBlock(1, 1));
		Assert.assertNotNull(stone.getBlock(2, 0));
		Assert.assertNotNull(stone.getBlock(2, 1));

		Assert.assertNull(stone.getBlock(0, 0));
		Assert.assertNull(stone.getBlock(0, 1));
		Assert.assertNull(stone.getBlock(1, 0));
	}

	@Test
	public void testCreateBlocksForRightRotation() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Block[][] newBlocks = stone.createBlocksForRightRotation();
		BlockArray newArray = new BlockArray(newBlocks);

		Assert.assertEquals(3, newArray.getWidthInBlocks());
		Assert.assertEquals(2, newArray.getHeightInBlocks());

		Assert.assertNotNull(newArray.getBlock(0, 0));
		Assert.assertNotNull(newArray.getBlock(0, 1));
		Assert.assertNotNull(newArray.getBlock(1, 0));

		Assert.assertNull(newArray.getBlock(1, 1));
		Assert.assertNull(newArray.getBlock(2, 0));
		Assert.assertNull(newArray.getBlock(2, 1));
	}

	@Test
	public void testCreateRightRotation() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		Stone newStone = stone.createRightRotation();

		Assert.assertEquals(3, newStone.getWidthInBlocks());
		Assert.assertEquals(2, newStone.getHeightInBlocks());

		Assert.assertNotNull(newStone.getBlock(0, 0));
		Assert.assertNotNull(newStone.getBlock(0, 1));
		Assert.assertNotNull(newStone.getBlock(1, 0));

		Assert.assertNull(newStone.getBlock(1, 1));
		Assert.assertNull(newStone.getBlock(2, 0));
		Assert.assertNull(newStone.getBlock(2, 1));
	}

	@Test
	public void testRotateRight() throws Exception {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 1, new SingleColorBlock(Color.RED));
		stone.setBlock(0, 2, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 2, new SingleColorBlock(Color.RED));

		stone.rotateRight();

		Assert.assertEquals(3, stone.getWidthInBlocks());
		Assert.assertEquals(2, stone.getHeightInBlocks());

		Assert.assertNotNull(stone.getBlock(0, 0));
		Assert.assertNotNull(stone.getBlock(0, 1));
		Assert.assertNotNull(stone.getBlock(1, 0));

		Assert.assertNull(stone.getBlock(1, 1));
		Assert.assertNull(stone.getBlock(2, 0));
		Assert.assertNull(stone.getBlock(2, 1));
	}

}
