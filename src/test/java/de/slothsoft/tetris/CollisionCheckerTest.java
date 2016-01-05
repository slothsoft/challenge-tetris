package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class CollisionCheckerTest {

	private final Board board = new Board();
	private final CollisionChecker checker = new CollisionChecker(this.board);

	@Test
	public void testWallOnLeft() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(true, this.checker.isWallOnLeft(stone));
		stone.setXInBlocks(1);
		Assert.assertEquals(false, this.checker.isWallOnLeft(stone));
	}

	@Test
	public void testBlockOnLeft() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		this.board.setBlock(2, 2, new Block(Color.BLUE));
		this.board.setBlock(4, 4, new Block(Color.BLUE));

		Assert.assertEquals(false, this.checker.isBlockOnLeft(stone));
		stone.setPositionInBlocks(2, 0);
		Assert.assertEquals(false, this.checker.isBlockOnLeft(stone));
		stone.setPositionInBlocks(3, 2);
		Assert.assertEquals(true, this.checker.isBlockOnLeft(stone));
		stone.setPositionInBlocks(4, 2);
		Assert.assertEquals(false, this.checker.isBlockOnLeft(stone));
		stone.setPositionInBlocks(3, 1);
		Assert.assertEquals(false, this.checker.isBlockOnLeft(stone));
		stone.setPositionInBlocks(2, 1);
		Assert.assertEquals(true, this.checker.isBlockOnLeft(stone));
	}

	@Test
	public void testCanMoveLeft() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		this.board.setBlock(2, 2, new Block(Color.BLUE));
		this.board.setBlock(4, 4, new Block(Color.BLUE));

		Assert.assertEquals(false, this.checker.canMoveLeft(stone));
		stone.setPositionInBlocks(2, 0);
		Assert.assertEquals(true, this.checker.canMoveLeft(stone));
		stone.setPositionInBlocks(3, 2);
		Assert.assertEquals(false, this.checker.canMoveLeft(stone));
		stone.setPositionInBlocks(4, 2);
		Assert.assertEquals(true, this.checker.canMoveLeft(stone));
		stone.setPositionInBlocks(3, 1);
		Assert.assertEquals(true, this.checker.canMoveLeft(stone));
		stone.setPositionInBlocks(2, 1);
		Assert.assertEquals(false, this.checker.canMoveLeft(stone));
	}

	@Test
	public void testWallOnRight() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		Assert.assertEquals(false, this.checker.isWallOnRight(stone));
		stone.setXInBlocks(Board.WIDTH_IN_BLOCKS - 2);
		Assert.assertEquals(true, this.checker.isWallOnRight(stone));
	}

	@Test
	public void testBlockOnRight() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		this.board.setBlock(2, 2, new Block(Color.BLUE));
		this.board.setBlock(4, 4, new Block(Color.BLUE));

		stone.setPositionInBlocks(Board.WIDTH_IN_BLOCKS - 2, 0);
		Assert.assertEquals(false, this.checker.isBlockOnRight(stone));
		stone.setPositionInBlocks(2, 0);
		Assert.assertEquals(false, this.checker.isBlockOnRight(stone));
		stone.setPositionInBlocks(1, 2);
		Assert.assertEquals(true, this.checker.isBlockOnRight(stone));
		stone.setPositionInBlocks(1, 3);
		Assert.assertEquals(false, this.checker.isBlockOnRight(stone));
		stone.setPositionInBlocks(2, 3);
		Assert.assertEquals(true, this.checker.isBlockOnRight(stone));
		stone.setPositionInBlocks(2, 4);
		Assert.assertEquals(false, this.checker.isBlockOnRight(stone));
	}

	@Test
	public void testCanMoveRight() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		this.board.setBlock(2, 2, new Block(Color.BLUE));
		this.board.setBlock(4, 4, new Block(Color.BLUE));

		stone.setPositionInBlocks(Board.WIDTH_IN_BLOCKS - 2, 0);
		Assert.assertEquals(false, this.checker.canMoveRight(stone));
		stone.setPositionInBlocks(2, 0);
		Assert.assertEquals(true, this.checker.canMoveRight(stone));
		stone.setPositionInBlocks(1, 2);
		Assert.assertEquals(false, this.checker.canMoveRight(stone));
		stone.setPositionInBlocks(1, 3);
		Assert.assertEquals(true, this.checker.canMoveRight(stone));
		stone.setPositionInBlocks(2, 3);
		Assert.assertEquals(false, this.checker.canMoveRight(stone));
		stone.setPositionInBlocks(2, 4);
		Assert.assertEquals(true, this.checker.canMoveRight(stone));
	}

	@Test
	public void testCanRotate() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));

		this.board.setBlock(2, 2, new Block(Color.BLUE));
		this.board.setBlock(4, 4, new Block(Color.BLUE));

		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.setPositionInBlocks(1, 1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
		stone.setPositionInBlocks(2, 3);
		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.setPositionInBlocks(3, 3);
		Assert.assertEquals(false, this.checker.canRotate(stone));
	}

	@Test
	public void testCanRotateStickingOutLeft() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));
		stone.setBlock(0, 2, new Block(Color.RED));

		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.incrementXInBlocks(-1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
		stone.incrementXInBlocks(-1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
	}

	@Test
	public void testCanRotateStickingOutBottom() {
		Stone stone = createStone(3, 2);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));
		stone.setBlock(2, 0, new Block(Color.RED));

		stone.setPositionInBlocks(0, Board.HEIGHT_IN_BLOCKS - stone.getHeightInBlocks() - 1);
		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.incrementYInBlocks(1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
		stone.incrementYInBlocks(1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
	}

	@Test
	public void testCanRotateStickingOutTop() {
		Stone stone = createStone(3, 2);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));
		stone.setBlock(2, 0, new Block(Color.RED));

		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.incrementYInBlocks(-1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
		stone.incrementYInBlocks(-1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
	}

	@Test
	public void testCanRotateStickingOutRight() {
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new Block(Color.RED));
		stone.setBlock(1, 1, new Block(Color.RED));
		stone.setBlock(0, 2, new Block(Color.RED));

		stone.setPositionInBlocks(Board.WIDTH_IN_BLOCKS - stone.getWidthInBlocks() - 1, 0);
		Assert.assertEquals(true, this.checker.canRotate(stone));
		stone.incrementXInBlocks(1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
		stone.incrementXInBlocks(1);
		Assert.assertEquals(false, this.checker.canRotate(stone));
	}

	private Stone createStone(int widthInBlocks, int heightInBlocks) {
		return new Stone(new Block[widthInBlocks][heightInBlocks]);
	}
}
