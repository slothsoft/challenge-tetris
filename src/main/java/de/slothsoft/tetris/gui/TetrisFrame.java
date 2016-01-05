package de.slothsoft.tetris.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.slothsoft.tetris.Game;
import de.slothsoft.tetris.Tetris;

public class TetrisFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final Game game = new Game().stonePositioner(Tetris.POSITIONER);
	private final SettingsPanel settingsPanel = new SettingsPanel(this.game);
	private final BlockArrayPanel gamePanel = new BlockArrayPanel(this.game.getBoard());

	public TetrisFrame() {
		setTitle("Tetris");
		this.game.start();
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
	}

}
