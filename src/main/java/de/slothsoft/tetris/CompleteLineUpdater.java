package de.slothsoft.tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Removes complete lines from the {@link Board}
 */

public final class CompleteLineUpdater {

	/**
	 * Returns the lines to be removed
	 */

	public static List<Integer> getLinesToBeRemoved(Board board) {
		List<Integer> removedLines = new ArrayList<>();

		for (int line = 0; line < Board.HEIGHT_IN_BLOCKS; line++) {
			if (shouldLineBeRemoved(board, line)) {
				removedLines.add(Integer.valueOf(line));
			}
		}
		return removedLines;
	}

	static boolean shouldLineBeRemoved(Board board, int line) {
		Block[][] blocks = board.getBlocks();
		for (int x = 0; x < blocks.length; x++) {
			if (blocks[x][line] == null) {
				return false;
			}
		}
		return true;
	}

	private Consumer<int[]> onLinesRemoved = i -> {
	};

	CompleteLineUpdater() {
		// hide
	}

	/**
	 * Updates the board (removes all lines that should be removed)
	 */

	public void update(Board board) {
		List<Integer> removedLines = getLinesToBeRemoved(board);
		removeLinesIfNecessary(board, removedLines);
	}

	private void removeLinesIfNecessary(Board board, List<Integer> removedLines) {
		if (!removedLines.isEmpty()) {
			removeLines(board, removedLines);
		}
	}

	private void removeLines(Board board, List<Integer> removedLines) {
		int[] lines = new int[removedLines.size()];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = removedLines.get(i).intValue();
		}
		doRemoveLines(board, lines);
		this.onLinesRemoved.accept(lines);
	}

	private void doRemoveLines(Board board, int[] lines) {
		for (int line : lines) {
			removeLine(board, line);
		}
	}

	void removeLine(Board board, int line) {
		Block[][] blocks = board.getBlocks();
		for (int y = line; y > 0; y--) {
			for (int x = 0; x < blocks.length; x++) {
				blocks[x][y] = blocks[x][y - 1];
			}
		}
		for (int x = 0; x < blocks.length; x++) {
			blocks[x][0] = null;
		}
	}

	public Consumer<int[]> getOnLinesRemoved() {
		return this.onLinesRemoved;
	}

	public void setOnLinesRemoved(Consumer<int[]> onLinesRemoved) {
		this.onLinesRemoved = Objects.requireNonNull(onLinesRemoved);
	}

}
