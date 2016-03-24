package de.slothsoft.tetris.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.slothsoft.tetris.Board;
import de.slothsoft.tetris.Game;
import de.slothsoft.tetris.Score;
import de.slothsoft.tetris.StonePositioner;

/**
 * The frame that holds the entire Tetris game: the {@link Board}, the
 * {@link Score} and the settings
 * 
 * @since 1.0.0
 */

public class TetrisFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final Game game = new Game();
	private final SettingsPanel settingsPanel = new SettingsPanel(this.game);
	private final BoardPanel gamePanel = new BoardPanel(this.game.getBoard());

	public TetrisFrame() {
		setTitle("Tetris");
		this.settingsPanel.onRendererChanged(r -> this.gamePanel.setRenderer(r));
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());
		add(this.settingsPanel, BorderLayout.WEST);
		add(this.gamePanel, BorderLayout.CENTER);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		centerWindow();
	}

	private void centerWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		Dimension windowSize = getSize();
		setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
	}

	public void start() {
		createMainPanel();
		setVisible(true);
		this.gamePanel.repaint();
		this.game.start();
	}

	public StonePositioner getStonePositioner() {
		return this.game.getStonePositioner();
	}

	public TetrisFrame stonePositioner(StonePositioner newStonePositioner) {
		setStonePositioner(newStonePositioner);
		return this;
	}

	public void setStonePositioner(StonePositioner stonePositioner) {
		game.setStonePositioner(stonePositioner);
	}

	public boolean isShowSettings() {
		return settingsPanel.isShowSettings();
	}

	public TetrisFrame showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setShowSettings(showSettings);
	}

	public int getTimePerStone() {
		return this.settingsPanel.getTimePerStone();
	}

	public TetrisFrame timePerStone(int newTimePerStone) {
		setTimePerStone(newTimePerStone);
		return this;
	}

	public void setTimePerStone(int waitTime) {
		this.settingsPanel.setTimePerStone(waitTime);
	}

}
