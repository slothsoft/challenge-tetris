package de.slothsoft.tetris;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.PositionBasedStonePositioner.Position;

public class PositionBuddyTest {

	private final Stone incomingStone = new Stone(StoneForm.O);

	@Test
	public void test() {
		PositionBuddy buddy = new PositionBuddy((stone, targetX) -> {
			if (this.incomingStone == stone && targetX == 7) {
				return 1;
			}
			return 0;
		});

		Position position = buddy.calculateTargetPosition(this.incomingStone);
		Assert.assertEquals(0, position.getLeftRotation());
		Assert.assertEquals(0, position.getRightRotation());
		Assert.assertEquals(7, position.getXInBlocks());
	}
}
