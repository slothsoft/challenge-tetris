package de.slothsoft.tetris;

public interface PositionBasedStonePositioner extends StonePositioner {

	@Override
	default void position(Context context) {
		Position position = calculateTargetPosition(context);
		requestMovement(context, position);
	}

	Position calculateTargetPosition(Context context);

	default void requestMovement(Context context, Position position) {
		Stone stone = context.getCurrentStone();

		for (int i = 0; i < position.getRightRotation(); i++) {
			context.requestRotateRight();
		}
		for (int i = 0; i < position.getLeftRotation(); i++) {
			context.requestRotateLeft();
		}

		int diff = position.getXInBlocks() - stone.getXInBlocks();
		while (diff != 0) {
			if (diff < 0) {
				context.requestMoveLeft();
				diff++;
			} else {
				context.requestMoveRight();
				diff--;
			}
		}
	}

	/**
	 * The target position
	 */

	class Position {

		private int xInBlocks;
		private int leftRotation;
		private int rightRotation;

		public int getLeftRotation() {
			return leftRotation;
		}

		public Position leftRotation(int newLeftRotation) {
			setLeftRotation(newLeftRotation);
			return this;
		}

		public void setLeftRotation(int leftRotation) {
			this.leftRotation = leftRotation;
		}

		public int getRightRotation() {
			return rightRotation;
		}

		public Position rightRotation(int newRightRotation) {
			setRightRotation(newRightRotation);
			return this;
		}

		public void setRightRotation(int rightRotation) {
			this.rightRotation = rightRotation;
		}

		public int getXInBlocks() {
			return xInBlocks;
		}

		public Position xInBlocks(int newXInBlocks) {
			setXInBlocks(newXInBlocks);
			return this;
		}

		public void setXInBlocks(int xInBlocks) {
			this.xInBlocks = xInBlocks;
		}

		@Override
		public String toString() {
			return "Position [x=" + xInBlocks + ", left=" + leftRotation + ", right=" + rightRotation + "]";
		}

	}

}
