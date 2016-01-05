package de.slothsoft.tetris.gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import de.slothsoft.tetris.StonePositioner;

public class StonePositionerListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -3711002482821614930L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Component result = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setText(((StonePositioner) value).getDisplayName());
		return result;
	}
}
