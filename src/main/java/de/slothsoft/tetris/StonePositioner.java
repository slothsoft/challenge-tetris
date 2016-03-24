package de.slothsoft.tetris;

/**
 * Interface used for positioning a stone on the board
 */

public interface StonePositioner {

	/**
	 * Positions the stone using the context:
	 * 
	 * <pre>
	 * <code>
	 * public void position(Context context) {
	 * 	context.requestMoveRight();
	 * 	context.requestRotateLeft();
	 * }
	 * </code>
	 * </pre>
	 * 
	 * Requests to the context might get granted, or they might not if another
	 * {@link Block} or the border of the {@link Board} is in the way.
	 * 
	 * @param context
	 *            - the context to get information about the board and request
	 *            stone moves
	 */

	void position(Context context);

	/**
	 * Returns the display name of the stone positioner. Should be unique.
	 * 
	 * @return a unique display name
	 */

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Context for the {@link StonePositioner} that should contain all relevant
	 * information
	 */

	interface Context {

		/**
		 * Returns the current {@link Board}
		 * 
		 * @return the board
		 */

		Board getBoard();

		/**
		 * Returns the {@link Stone} that is in the process of falling down the
		 * {@link Board}
		 * 
		 * @return the current stone
		 */

		default Stone getCurrentStone() {
			return getBoard().getCurrentStone();
		}

		/**
		 * Requests that the current stone is moved right. Requests might not
		 * get granted if the movement is not possible.
		 */

		void requestMoveRight();

		/**
		 * Requests that the current stone is moved left. Requests might not get
		 * granted if the movement is not possible.
		 */

		void requestMoveLeft();

		/**
		 * Requests that the current stone is moved down. Requests might not get
		 * granted if the movement is not possible.
		 */

		void requestMoveDown();

		/**
		 * Requests that the current stone is rotated right. Requests might not
		 * get granted if the movement is not possible.
		 */

		void requestRotateRight();

		/**
		 * Requests that the current stone is rotated left. Requests might not
		 * get granted if the movement is not possible.
		 */

		void requestRotateLeft();

	}
}
