package de.slothsoft.tetris.gui;

import java.awt.Component;
import java.util.Objects;
import java.util.function.Function;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Just a multi-purpose {@link ListCellRenderer}
 * 
 * @since 1.0.0
 * @param <T> - type of object to be displayed
 */

class DisplayableListCellRenderer<T> extends DefaultListCellRenderer {

	private static final long serialVersionUID = -3711002482821614930L;

	private Function<T, String> toString;

	public DisplayableListCellRenderer(Function<T, String> toString) {
		this.toString = Objects.requireNonNull(toString);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Component result = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		setText(this.toString.apply((T) value));
		return result;
	}
}
