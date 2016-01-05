package de.slothsoft.tetris;

public class StonePosition {

	private final int targetXInBlocks;
	private final int rotationCount;

	public StonePosition(int targetXInBlocks, int rotationCount) {
		this.targetXInBlocks = targetXInBlocks;
		this.rotationCount = rotationCount;
	}

	public int getTargetXInBlocks() {
		return this.targetXInBlocks;
	}

	public int getRotationCount() {
		return this.rotationCount;
	}

}
