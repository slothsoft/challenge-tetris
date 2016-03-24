package de.slothsoft.tetris;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * The game class that holds everything together.
 */

public class Game {

	private final Thread thread = new Thread(this::run);

	private final Board board = new Board();
	private final EventHandler eventHandler = new EventHandler(this.board);

	private StonePositioner stonePositioner = Tetris.POSITIONER;
	private boolean stop;
	private int timePerStone = 500;
	private int sleepTime = this.timePerStone / Board.WIDTH_IN_BLOCKS;

	/**
	 * Starts the current game
	 */

	public void start() {
		this.stop = false;
		this.eventHandler.startGame();
		this.thread.start();
	}

	private void run() {
		while (!this.stop) {
			this.stonePositioner.position(new StonePositionerContext(this.eventHandler));
			this.eventHandler.moveCurrentStoneDown();
			sleep();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Stops the current game
	 */

	public void stopGame() {
		this.stop = true;
	}

	public int getTimePerStone() {
		return this.timePerStone;
	}

	public Game timePerStone(int newTimePerStone) {
		setTimePerStone(newTimePerStone);
		return this;
	}

	public void setTimePerStone(int waitTime) {
		this.timePerStone = waitTime;
		this.sleepTime = this.timePerStone / Board.WIDTH_IN_BLOCKS;
	}

	public Consumer<Score> getOnGameFinish() {
		return this.eventHandler.getOnGameFinish();
	}

	public Game onGameFinish(Consumer<Score> onGameFinish) {
		setOnGameFinish(onGameFinish);
		return this;
	}

	public void setOnGameFinish(Consumer<Score> onGameFinish) {
		this.eventHandler.setOnGameFinish(onGameFinish);
	}

	public Board getBoard() {
		return this.board;
	}

	public StonePositioner getStonePositioner() {
		return this.stonePositioner;
	}

	public Game stonePositioner(StonePositioner newStonePositioner) {
		setStonePositioner(newStonePositioner);
		return this;
	}

	public void setStonePositioner(StonePositioner stonePositioner) {
		this.stonePositioner = Objects.requireNonNull(stonePositioner);
	}

	public StoneFactory getStoneFactory() {
		return this.eventHandler.getStoneFactory();
	}

	public Game stoneFactory(StoneFactory newStoneFactory) {
		setStoneFactory(newStoneFactory);
		return this;
	}

	public void setStoneFactory(StoneFactory stoneFactory) {
		this.eventHandler.setStoneFactory(stoneFactory);
	}

	public Score getScore() {
		return this.eventHandler.getScore();
	}

	/*
	 *
	 */

	public static class StonePositionerContext implements StonePositioner.Context {

		private final EventHandler eventHandler;
		private final Board board;

		public StonePositionerContext(EventHandler eventHandler) {
			this.board = eventHandler.getBoard().clone();
			this.eventHandler = eventHandler;
		}

		@Override
		public Board getBoard() {
			return this.board;
		}

		@Override
		public void requestMoveRight() {
			this.eventHandler.requestMoveRight();
		}

		@Override
		public void requestMoveDown() {
			this.eventHandler.moveCurrentStoneDown();
		}

		@Override
		public void requestMoveLeft() {
			this.eventHandler.requestMoveLeft();
		}

		@Override
		public void requestRotateRight() {
			this.eventHandler.requestRotateRight();
		}

		@Override
		public void requestRotateLeft() {
			this.eventHandler.requestRotateLeft();
		}

	}
}
