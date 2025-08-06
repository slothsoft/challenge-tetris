package de.slothsoft.tetris;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.blocks.SingleColorBlock;

public class ClonkCheckerTest {

	private final Board board = new Board();
	private final ClonkChecker checker = new ClonkChecker(this.board);

	@Test
	public void testClonkedToBlock() {
		// X_
		// _X
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(false, this.checker.isClonkedToBlock(stone));

		// there is a block below the left stone block
		this.board.setBlock(0, 1, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(true, this.checker.isClonkedToBlock(stone));

		// there is a block below the right stone block
		this.board.clear();
		this.board.setBlock(1, 2, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(true, this.checker.isClonkedToBlock(stone));

		// there is a block in the empty whole on the top right
		this.board.clear();
		this.board.setBlock(1, 0, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(false, this.checker.isClonkedToBlock(stone));
	}

	@Test
	public void testClonkedToBottom() {
		Stone stone = createStone(2, 2);
		stone.setBlock(0, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(false, this.checker.isClonkedToBottom(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 2);
		Assert.assertEquals(true, this.checker.isClonkedToBottom(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 1);
		Assert.assertEquals(true, this.checker.isClonkedToBottom(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 0);
		Assert.assertEquals(true, this.checker.isClonkedToBottom(stone));
	}

	@Test
	public void testIsClonkedForToBlock() {
		// X_
		// _X
		Stone stone = createStone(2, 3);
		stone.setBlock(0, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(false, this.checker.isClonked(stone));

		// there is a block below the left stone block
		this.board.setBlock(0, 1, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(true, this.checker.isClonked(stone));

		// there is a block below the right stone block
		this.board.clear();
		this.board.setBlock(1, 2, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(true, this.checker.isClonked(stone));

		// there is a block in the empty whole on the top right
		this.board.clear();
		this.board.setBlock(1, 0, new SingleColorBlock(Color.BLUE));
		Assert.assertEquals(false, this.checker.isClonked(stone));
	}

	@Test
	public void testIsClonkedForToBottom() {
		Stone stone = createStone(2, 2);
		stone.setBlock(0, 0, new SingleColorBlock(Color.RED));
		stone.setBlock(1, 1, new SingleColorBlock(Color.RED));

		Assert.assertEquals(false, this.checker.isClonked(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 2);
		Assert.assertEquals(true, this.checker.isClonked(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 1);
		Assert.assertEquals(true, this.checker.isClonked(stone));

		stone.setYInBlocks(Board.HEIGHT_IN_BLOCKS - 0);
		Assert.assertEquals(true, this.checker.isClonked(stone));
	}

	private Stone createStone(int widthInBlocks, int heightInBlocks) {
		return new Stone(new Block[widthInBlocks][heightInBlocks]);
	}

}
