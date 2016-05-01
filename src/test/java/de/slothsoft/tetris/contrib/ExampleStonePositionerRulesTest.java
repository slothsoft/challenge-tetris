package de.slothsoft.tetris.contrib;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Board;
import de.slothsoft.tetris.Stone;
import de.slothsoft.tetris.blocks.SingleColorBlock;
import de.slothsoft.tetris.blocks.StoneForm;

/**
 * This class tests the rule methods (which are added with varying ratio to a total score)
 *
 * @author Slothsoft &lt;admin@slothsoft.de&gt;
 * @since 2016-01-05
 * @version 1.0.0
 */

public class ExampleStonePositionerRulesTest {

	private final ExampleStonePositioner positioner = new ExampleStonePositioner();
	private final Board board = new Board();

	@Test
	public void testPointsForY() {
		Assert.assertEquals(-Board.HEIGHT_IN_BLOCKS, ExampleStonePositioner.getPointsForY(0));
		Assert.assertEquals(10 - Board.HEIGHT_IN_BLOCKS, ExampleStonePositioner.getPointsForY(10));
		Assert.assertEquals(0, ExampleStonePositioner.getPointsForY(Board.HEIGHT_IN_BLOCKS));
	}

	@Test
	public void testPointsForFlatObjects() {
		Assert.assertEquals(1, ExampleStonePositioner.getPointsForFlatObjects(createStone(4, 1)));
		Assert.assertEquals(0, ExampleStonePositioner.getPointsForFlatObjects(createStone(1, 4)));
		Assert.assertEquals(0, ExampleStonePositioner.getPointsForFlatObjects(createStone(2, 2)));
	}

	private Stone createStone(int widthInBlocks, int heightInBlocks) {
		return new Stone(new Block[widthInBlocks][heightInBlocks]);
	}

	@Test
	public void testMinusPointsForXLeft() {
		this.positioner.setWantsPoleOnTheRight(false);
		Assert.assertEquals(-1, this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), 0));
		Assert.assertEquals(0, this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), 4));
		Assert.assertEquals(0,
				this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), Board.WIDTH_IN_BLOCKS - 2));
	}

	@Test
	public void testMinusPointsForXRight() {
		this.positioner.setWantsPoleOnTheRight(true);
		Assert.assertEquals(0, this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), 0));
		Assert.assertEquals(0, this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), 4));
		Assert.assertEquals(-1,
				this.positioner.getPointsForX(new Stone(StoneForm.O.createBlocks()), Board.WIDTH_IN_BLOCKS - 2));
	}

	@Test
	public void testPointsForHeightDifference() {
		Assert.assertEquals(1, ExampleStonePositioner.getPointsForHeightDifference(2, 2));

		Assert.assertEquals(0, ExampleStonePositioner.getPointsForHeightDifference(2, 1));
		Assert.assertEquals(0, ExampleStonePositioner.getPointsForHeightDifference(1, 2));

		Assert.assertEquals(-1, ExampleStonePositioner.getPointsForHeightDifference(1, 3));
		Assert.assertEquals(-1, ExampleStonePositioner.getPointsForHeightDifference(3, 1));
	}

	@Test
	public void testTopStoneY() {
		for (int i = 0; i < Board.WIDTH_IN_BLOCKS - 1; i++) {
			this.board.setBlock(i, i, new SingleColorBlock(Color.BLACK));
		}
		Assert.assertEquals(0, ExampleStonePositioner.getTopStoneY(this.board, 0));
		Assert.assertEquals(4, ExampleStonePositioner.getTopStoneY(this.board, 4));
		Assert.assertEquals(Board.HEIGHT_IN_BLOCKS,
				ExampleStonePositioner.getTopStoneY(this.board, Board.WIDTH_IN_BLOCKS - 1));
	}

	@Test
	public void testPointsForHeightDifferences() {
		this.board.clear();
		Assert.assertEquals(8, ExampleStonePositioner.getPointsForHeightDifferences(this.board));

		this.board.clear();
		for (int i = 0; i < Board.WIDTH_IN_BLOCKS - 1; i++) {
			this.board.setBlock(i, Board.HEIGHT_IN_BLOCKS - 1 - i, new SingleColorBlock(Color.BLACK));
		}
		Assert.assertEquals(0, ExampleStonePositioner.getPointsForHeightDifferences(this.board));

		this.board.clear();
		for (int i = 0; i < Board.WIDTH_IN_BLOCKS - 1; i++) {
			this.board.setBlock(i, i * 2, new SingleColorBlock(Color.BLACK));
		}
		Assert.assertEquals(-8, ExampleStonePositioner.getPointsForHeightDifferences(this.board));
	}

}
