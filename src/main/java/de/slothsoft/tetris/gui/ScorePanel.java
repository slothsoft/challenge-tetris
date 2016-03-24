package de.slothsoft.tetris.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.slothsoft.tetris.Score;

/**
 * A panel to display the {@link Score}
 * 
 * @since 1.0.0
 */

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final NumberFormat format = NumberFormat.getInstance();

	public ScorePanel(Score score) {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(3, 2));

		add(createSmallLabel("Stone count:"));
		JLabel stoneCount = createSmallLabel("0", SwingConstants.RIGHT);
		add(stoneCount);

		add(createSmallLabel("Lines removed:    "));
		JLabel linesRemoved = createSmallLabel("0", SwingConstants.RIGHT);
		add(linesRemoved);

		add(createBigLabel("Score"));
		JLabel totalScore = createBigLabel("0", SwingConstants.RIGHT);
		add(totalScore);

		score.addPropertyChangeListener(Score.PROP_LINES_REMOVED,
				e -> linesRemoved.setText(this.format.format(e.getNewValue())));
		score.addPropertyChangeListener(Score.PROP_SCORE, e -> totalScore.setText(this.format.format(e.getNewValue())));
		score.addPropertyChangeListener(Score.PROP_STONE_COUNT,
				e -> stoneCount.setText(this.format.format(e.getNewValue())));
	}

	private JLabel createSmallLabel(String string) {
		return createSmallLabel(string, SwingConstants.LEFT);
	}

	private JLabel createSmallLabel(String string, int alignment) {
		JLabel label = new JLabel(string, alignment);
		label.setForeground(Color.WHITE);
		return label;
	}

	private JLabel createBigLabel(String string) {
		return createBigLabel(string, SwingConstants.LEFT);
	}

	private JLabel createBigLabel(String string, int alignment) {
		JLabel label = new JLabel(string, alignment);
		label.setForeground(Color.WHITE);
		label.setFont(label.getFont().deriveFont(Font.BOLD, 17f));
		return label;
	}

}
