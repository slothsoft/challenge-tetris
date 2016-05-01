package de.slothsoft.tetris.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import de.slothsoft.tetris.Game;
import de.slothsoft.tetris.Score;
import de.slothsoft.tetris.StoneFactory;
import de.slothsoft.tetris.StonePositioner;
import de.slothsoft.tetris.StonePositioners;
import de.slothsoft.tetris.blocks.DefaultStoneFactory;

/**
 * A panel to display game settings and {@link Score}
 * 
 * @since 1.0.0
 */

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;
	private static final Insets INSETS = new Insets(5, 5, 5, 5);

	private final Game game;

	private final Component settingsPanel;
	private JSpinner waitTime;

	private Consumer<TetrisRenderer> onRendererChanged = r -> System.out.print("");

	public SettingsPanel(Game game) {
		this.game = game;

		this.settingsPanel = createSettings();

		setLayout(new BorderLayout());
		add(createScorePanel(), BorderLayout.NORTH);
		add(createSpanner(), BorderLayout.CENTER);
		add(settingsPanel, BorderLayout.SOUTH);
	}

	private Component createScorePanel() {
		return new ScorePanel(this.game.getScore());
	}

	private Component createSpanner() {
		JLabel spanner = new JLabel();
		spanner.setOpaque(true);
		spanner.setBackground(Color.BLACK);
		return spanner;
	}

	private Component createSettings() {
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		titleBorder.setTitleColor(Color.WHITE);

		JPanel parent = new JPanel();
		parent.setBorder(titleBorder);
		parent.setLayout(new GridBagLayout());
		parent.setBackground(Color.BLACK);

		int y = 0;
		JComboBox<StonePositioner> stonePositioner = new JComboBox<>();
		stonePositioner.setModel(
				new DefaultComboBoxModel<StonePositioner>(new Vector<>(StonePositioners.getStonePositioners())));
		stonePositioner.setRenderer(new DisplayableListCellRenderer<StonePositioner>(p -> p.getDisplayName()));
		stonePositioner.addActionListener(
				e -> this.game.setStonePositioner((StonePositioner) stonePositioner.getSelectedItem()));
		stonePositioner.setSelectedItem(this.game.getStonePositioner());

		parent.add(createLabel("Positioner"), createLabelConstraints(0, y));
		parent.add(stonePositioner, createControlConstraints(1, y));
		y++;

		this.waitTime = new JSpinner();
		this.waitTime.setModel(new SpinnerNumberModel(this.game.getTimePerStone(), 0, 10000, 100));
		this.waitTime.addChangeListener(e -> this.game.setTimePerStone(((Integer) waitTime.getValue())));

		parent.add(createLabel("Delay"), createLabelConstraints(0, y));
		parent.add(waitTime, createControlConstraints(1, y));
		y++;

		JComboBox<StoneFactory> stoneFactory = new JComboBox<>();
		stoneFactory.setModel(
				new DefaultComboBoxModel<StoneFactory>(new Vector<>(Arrays.asList(DefaultStoneFactory.values()))));
		stoneFactory.setRenderer(new DisplayableListCellRenderer<StoneFactory>(f -> f.getDisplayName()));
		stoneFactory.addActionListener(e -> this.game.setStoneFactory((StoneFactory) stoneFactory.getSelectedItem()));
		stoneFactory.setSelectedItem(this.game.getStoneFactory());

		parent.add(createLabel("Stones"), createLabelConstraints(0, y));
		parent.add(stoneFactory, createControlConstraints(1, y));
		y++;

		JComboBox<TetrisRenderer> renderer = new JComboBox<>();
		renderer.setModel(new DefaultComboBoxModel<TetrisRenderer>(new Vector<>(TetrisRenderer.getTetrisRenderers())));
		renderer.setRenderer(new DisplayableListCellRenderer<TetrisRenderer>(f -> f.getDisplayName()));
		renderer.addActionListener(e -> this.onRendererChanged.accept((TetrisRenderer) renderer.getSelectedItem()));
		renderer.setSelectedItem(TetrisRenderer.DEFAULT);

		parent.add(createLabel("Renderer"), createLabelConstraints(0, y));
		parent.add(renderer, createControlConstraints(1, y));
		y++;

		return parent;
	}

	private Component createLabel(String text) {
		JLabel label = new JLabel(text + ':');
		label.setForeground(Color.WHITE);
		return label;
	}

	private static GridBagConstraints createLabelConstraints(int x, int y) {
		return new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, INSETS,
				0, 0);
	}

	private static GridBagConstraints createControlConstraints(int x, int y) {
		return new GridBagConstraints(x, y, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				INSETS, 0, 0);
	}

	public Consumer<TetrisRenderer> getOnRendererChanged() {
		return this.onRendererChanged;
	}

	public SettingsPanel onRendererChanged(Consumer<TetrisRenderer> newOnRendererChanged) {
		setOnRendererChanged(newOnRendererChanged);
		return this;
	}

	public void setOnRendererChanged(Consumer<TetrisRenderer> onRendererChanged) {
		this.onRendererChanged = Objects.requireNonNull(onRendererChanged);
	}

	public boolean isShowSettings() {
		return settingsPanel.isVisible();
	}

	public SettingsPanel showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setVisible(showSettings);
	}

	public int getTimePerStone() {
		return this.game.getTimePerStone();
	}

	public SettingsPanel timePerStone(int newTimePerStone) {
		setTimePerStone(newTimePerStone);
		return this;
	}

	public void setTimePerStone(int waitTime) {
		this.game.setTimePerStone(waitTime);
		this.waitTime.setValue(waitTime);
	}

}
