package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.blocks.SingleColorBlock;

public class SingleColorTetrisRenderer implements TetrisRenderer {

	private final Map<Integer, SingleColorBlock> colors = new HashMap<>();

	@Override
	public void paintBlock(Graphics2D graphics, Block block) {
		if (block instanceof SingleColorBlock) {
			block.paint(graphics);
		} else {
			paintAsSingleColorBlock(graphics, block);
		}
	}

	void paintAsSingleColorBlock(Graphics2D graphics, Block block) {
		SingleColorBlock singleColorBlock;

		Integer key = Integer.valueOf(block.hashCode());
		if (this.colors.containsKey(block.hashCode())) {
			singleColorBlock = this.colors.get(key);
		} else {
			singleColorBlock = new SingleColorBlock(new Color(key.intValue()));
			this.colors.put(key, singleColorBlock);
		}
		singleColorBlock.paint(graphics);
	}
}
