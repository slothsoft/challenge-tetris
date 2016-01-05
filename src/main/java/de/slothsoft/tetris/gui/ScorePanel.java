package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.slothsoft.tetris.Score;

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final NumberFormat format = NumberFormat.getInstance();

	public ScorePanel(Score score) {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(3, 2));

		add(createSmallLabel("Stone count:"));
		JLabel stoneCount = createSmallLabel("0");
		add(stoneCount);

		add(createSmallLabel("Lines removed:    "));
		JLabel linesRemoved = createSmallLabel("0");
		add(linesRemoved);

		add(createBigLabel("Score"));
		JLabel totalScore = createBigLabel("0");
		add(totalScore);

		score.addPropertyChangeListener(Score.PROP_LINES_REMOVED,
				e -> linesRemoved.setText(this.format.format(e.getNewValue())));
		score.addPropertyChangeListener(Score.PROP_SCORE, e -> totalScore.setText(this.format.format(e.getNewValue())));
		score.addPropertyChangeListener(Score.PROP_STONE_COUNT,
				e -> stoneCount.setText(this.format.format(e.getNewValue())));
	}

	private JLabel createSmallLabel(String string) {
		JLabel label = new JLabel(string);
		label.setForeground(Color.WHITE);
		return label;
	}

	private JLabel createBigLabel(String string) {
		JLabel label = new JLabel(string);
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 15f));
		return label;
	}

}
