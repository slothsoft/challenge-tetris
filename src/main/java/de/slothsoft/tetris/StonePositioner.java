package de.slothsoft.tetris;

/**
 * Interface used for positioning a stone on the board
 */

public interface StonePositioner {

	void position(Context context);

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Context for the {@link StonePositioner} that should contain all relevant
	 * information
	 */

	interface Context {

		Board getBoard();

		default Stone getCurrentStone() {
			return getBoard().getCurrentStone();
		}

		void requestMoveRight();

		void requestMoveLeft();

		void requestMoveDown();

		void requestRotateRight();

		void requestRotateLeft();

	}
}
