package de.slothsoft.tetris.contrib;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Board;
import de.slothsoft.tetris.EventHandler;
import de.slothsoft.tetris.Game.StonePositionerContext;
import de.slothsoft.tetris.Stone;
import de.slothsoft.tetris.StonePositioner;
import de.slothsoft.tetris.blocks.SingleColorBlock;
import de.slothsoft.tetris.blocks.StoneForm;

/**
 * We test some clear cases here
 *
 * @author Slothsoft &lt;admin@slothsoft.de&gt;
 * @since 2016-01-05
 * @version 1.0.0
 */

public class ExampleStonePositionerTest {

	private final StonePositioner positioner = new ExampleStonePositioner();

	private final Board board = new Board();

	@Test
	public void testPoleInTheHoleRight() {
		Stone stone = new Stone(StoneForm.I.createBlocks());
		this.board.setCurrentStone(stone);

		Block block = new SingleColorBlock(Color.BLACK);
		for (int x = 0; x < Board.WIDTH_IN_BLOCKS - 1; x++) {
			for (int yPlus = 0; yPlus < 4; yPlus++) {
				this.board.setBlock(x, Board.HEIGHT_IN_BLOCKS - 1 - yPlus, block);
			}
		}
		this.positioner.position(new StonePositionerContext(new EventHandler(this.board)));

		// pole must be upright and on the side
		Assert.assertEquals(Board.WIDTH_IN_BLOCKS - 1, stone.getXInBlocks());
		Assert.assertEquals("X\nX\nX\nX\n", stone.stringify());
	}

	@Test
	public void testPoleInTheHoleLeft() {
		Stone stone = new Stone(StoneForm.I.createBlocks());
		this.board.setCurrentStone(stone);

		Block block = new SingleColorBlock(Color.BLACK);
		for (int x = 1; x < Board.WIDTH_IN_BLOCKS; x++) {
			for (int yPlus = 0; yPlus < 4; yPlus++) {
				this.board.setBlock(x, Board.HEIGHT_IN_BLOCKS - 1 - yPlus, block);
			}
		}
		this.positioner.position(new StonePositionerContext(new EventHandler(this.board)));

		// pole must be upright and on the side
		Assert.assertEquals(0, stone.getXInBlocks());
		Assert.assertEquals("X\nX\nX\nX\n", stone.stringify());
	}

	@Test
	public void testPoleInTheHoleMiddle() {
		Stone stone = new Stone(StoneForm.I.createBlocks());
		this.board.setCurrentStone(stone);

		Block block = new SingleColorBlock(Color.BLACK);
		for (int x = 0; x < Board.WIDTH_IN_BLOCKS; x++) {
			if (x != 4) {
				for (int yPlus = 0; yPlus < 4; yPlus++) {
					this.board.setBlock(x, Board.HEIGHT_IN_BLOCKS - 1 - yPlus, block);
				}
			}
		}
		this.positioner.position(new StonePositionerContext(new EventHandler(this.board)));

		// pole must be upright and on the side
		Assert.assertEquals(4, stone.getXInBlocks());
		Assert.assertEquals("X\nX\nX\nX\n", stone.stringify());
	}

}
