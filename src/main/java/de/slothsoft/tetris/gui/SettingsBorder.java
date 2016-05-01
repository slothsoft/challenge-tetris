package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

import de.slothsoft.tetris.Block;

/**
 * The border of the settings depends on the board and how (and where) it is drawn
 * 
 * @since 1.0.0
 */

class SettingsBorder implements Border {

	private final BoardPanel boardPanel;

	public SettingsBorder(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		double ratio = this.boardPanel.getRatio();
		if (ratio == 0.0) ratio = 1;
		int top = (int) (ratio * Block.HEIGHT_IN_PIXELS);
		int left = top;
		int bottom = (int) (top + (this.boardPanel.getHeight() - this.boardPanel.getPreferredHeight() * ratio));
		int right = 0;
		return new Insets(top, left, bottom, right);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

}
