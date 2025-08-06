package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.blocks.FunkyBlock;

/**
 * A {@link TetrisRenderer} that renders all stone as funky stones
 * 
 * @since 1.0.0
 */

public class FunkyTetrisRenderer implements TetrisRenderer {

	private final Map<Integer, FunkyBlock> colors = new HashMap<>();

	@Override
	public String getDisplayName() {
		return "Funky Renderer";
	}

	@Override
	public void paintBlock(Graphics2D graphics, Block block) {
		if (block instanceof FunkyBlock) {
			block.paint(graphics);
		} else {
			paintAsFunkyBlock(graphics, block);
		}
	}

	void paintAsFunkyBlock(Graphics2D graphics, Block block) {
		FunkyBlock singleColorBlock;

		Integer key = Integer.valueOf(block.hashCode());
		if (this.colors.containsKey(block.hashCode())) {
			singleColorBlock = this.colors.get(key);
		} else {
			singleColorBlock = new FunkyBlock(new Color(key.intValue()));
			this.colors.put(key, singleColorBlock);
		}
		singleColorBlock.paint(graphics);
	}
}
