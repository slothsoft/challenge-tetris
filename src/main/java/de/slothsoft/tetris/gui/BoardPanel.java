package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

import javax.swing.JPanel;

import de.slothsoft.tetris.Block;
import de.slothsoft.tetris.Board;

/**
 * A panel to paint a {@link Board} on
 * 
 * @since 1.0.0
 */

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = -4607180702570402004L;
	private static final long REPAINT_IN_MS = 1000 / 24; // 24 frames per second

	private Board board;
	private TetrisRenderer renderer = TetrisRenderer.DEFAULT;
	private int width;
	private int height;

	public BoardPanel() {
		this(null);
	}

	public BoardPanel(Board board) {
		setBoard(board);
		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics graphics) {
		if (getBackground() != null) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}

		if (this.board != null) {
			float ratio = (float) Math.min((double) getWidth() / this.width, (double) getHeight() / this.height);
			((Graphics2D) graphics).scale(ratio, ratio);
			((Graphics2D) graphics).translate(Block.WIDTH, Block.HEIGHT);
			this.renderer.paintBoard((Graphics2D) graphics, this.board);
		}
		repaint(REPAINT_IN_MS);
	}

	public Board getBoard() {
		return this.board;
	}

	public void setBoard(Board board) {
		this.board = board;
		this.width = board == null ? 0 : board.getWidthInPixels() + 2 * Block.WIDTH;
		this.height = board == null ? 0 : board.getHeightInPixels() + 2 * Block.HEIGHT;
		setPreferredSize(new Dimension(this.width, this.height));
		repaint();
	}

	public TetrisRenderer getRenderer() {
		return this.renderer;
	}

	public BoardPanel renderer(TetrisRenderer newRenderer) {
		setRenderer(newRenderer);
		return this;
	}

	public void setRenderer(TetrisRenderer renderer) {
		this.renderer = Objects.requireNonNull(renderer);

	}

}
