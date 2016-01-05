package de.slothsoft.tetris;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.slothsoft.tetris.Game.StonePositionerContext;
import de.slothsoft.tetris.PositionBasedStonePositioner.Position;
import de.slothsoft.tetris.blocks.StoneForm;

public class PositionBasedStonePositionerTest {

	private final Board board = new Board();
	private final Stone stone = new Stone(StoneForm.O.createBlocks());
	private StonePositionerContext context;

	@Before
	public void setUp() {
		this.board.setCurrentStone(this.stone);
		this.context = new StonePositionerContext(new EventHandler(this.board));
	}

	@Test
	public void testMoveRight() throws Exception {
		this.stone.setPositionInBlocks(3, 0);

		PositionBasedStonePositioner positioner = (c) -> new Position().xInBlocks(4);
		positioner.position(this.context);

		Assert.assertEquals(4, this.stone.getXInBlocks());
		Assert.assertEquals(0, this.stone.getYInBlocks());
	}

	@Test
	public void testMoveLeft() throws Exception {
		this.stone.setPositionInBlocks(3, 0);

		PositionBasedStonePositioner positioner = (c) -> new Position().xInBlocks(2);
		positioner.position(this.context);

		Assert.assertEquals(2, this.stone.getXInBlocks());
		Assert.assertEquals(0, this.stone.getYInBlocks());
	}
}
