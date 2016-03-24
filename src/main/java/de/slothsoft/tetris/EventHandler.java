package de.slothsoft.tetris;

import java.util.Objects;
import java.util.function.Consumer;

import de.slothsoft.tetris.blocks.DefaultStoneFactory;

/**
 * The fundamental class for everything that happens in a Tetris game
 */

public final class EventHandler {

	private static final int[] SCORING_SYSTEM = { 40, 100, 300, 1200 };

	private final Board board;
	private final Score score = new Score();

	private final CollisionChecker collisionChecker;
	private final ClonkChecker clonkChecker;
	private final CompleteLineUpdater completeLineUpdater = new CompleteLineUpdater();

	private StoneFactory stoneFactory = DefaultStoneFactory.DEFAULT;
	private Consumer<Score> onGameFinish = i -> System.out.println("Game Over! Score: " + i.getScore());

	public EventHandler(Board board) {
		this.board = board;
		this.clonkChecker = new ClonkChecker(board);
		this.collisionChecker = new CollisionChecker(board);
		this.completeLineUpdater.setOnLinesRemoved(i -> addScore(i.length));
	}

	void addScore(int linesRemoved) {
		this.score.incrementLinesRemoved(linesRemoved);
		this.score.incrementScore(SCORING_SYSTEM[Math.min(SCORING_SYSTEM.length, linesRemoved - 1)]);
	}

	public void requestMoveRight() {
		if (this.collisionChecker.canMoveRight(this.board.getCurrentStone())) {
			this.board.getCurrentStone().incrementXInBlocks(1);
		}
	}

	public void requestMoveLeft() {
		if (this.collisionChecker.canMoveLeft(this.board.getCurrentStone())) {
			this.board.getCurrentStone().incrementXInBlocks(-1);
		}
	}

	private void clonkStone() {
		Stone stone = this.board.getCurrentStone();
		int blockX = stone.getXInBlocks();
		int blockY = stone.getYInBlocks();
		this.board.putBlocks(blockX, blockY, stone);
	}

	public void prepareNewStone() {
		prepareStone(createRandomStone());
	}

	private Stone createRandomStone() {
		return this.stoneFactory.createRandomStone();
	}

	public void prepareStone(Stone newStone) {
		this.board.setCurrentStone(newStone);
		newStone.setXInBlocks((this.board.getWidthInBlocks() - newStone.getWidthInBlocks()) / 2);
		this.score.incrementStoneCount(1);
	}

	public void startGame() {
		this.board.clear();
		this.score.clear();
		prepareNewStone();
	}

	void removeLine(int line) {
		Block[][] blocks = this.board.getBlocks();
		for (int y = line; y > 0; y--) {
			for (int x = 0; x < blocks.length; x++) {
				blocks[x][y] = blocks[x][y - 1];
			}
		}
		for (int x = 0; x < blocks.length; x++) {
			blocks[x][0] = null;
		}
	}

	public void moveCurrentStoneDown() {
		if (this.board.getCurrentStone() == null)
			return;
		this.board.getCurrentStone().incrementYInBlocks(1);
		clonkStoneIfNecessary();
	}

	private void clonkStoneIfNecessary() {
		if (this.clonkChecker.isClonked(this.board.getCurrentStone())) {
			clonkStone();
			this.completeLineUpdater.update(this.board);
			prepareNewStone();

			if (this.clonkChecker.isClonked(this.board.getCurrentStone())) {
				this.onGameFinish.accept(this.score);
				startGame();
			}
		}
	}

	public void requestRotateLeft() {
		Stone rotatedStone = this.board.getCurrentStone().createLeftRotation();
		if (this.collisionChecker.canRotate(rotatedStone)) {
			this.board.setCurrentStone(rotatedStone);
		}
	}

	public void requestRotateRight() {
		Stone rotatedStone = this.board.getCurrentStone().createRightRotation();
		if (this.collisionChecker.canRotate(rotatedStone)) {
			this.board.setCurrentStone(rotatedStone);
		}
	}

	public Consumer<Score> getOnGameFinish() {
		return this.onGameFinish;
	}

	public void setOnGameFinish(Consumer<Score> onGameFinish) {
		this.onGameFinish = Objects.requireNonNull(onGameFinish);
	}

	public StoneFactory getStoneFactory() {
		return this.stoneFactory;
	}

	public EventHandler stoneFactory(StoneFactory newStoneFactory) {
		setStoneFactory(newStoneFactory);
		return this;
	}

	public void setStoneFactory(StoneFactory stoneFactory) {
		this.stoneFactory = Objects.requireNonNull(stoneFactory);
	}

	public Board getBoard() {
		return this.board;
	}

	public Score getScore() {
		return this.score;
	}

}
