package de.slothsoft.tetris;

import java.util.List;
import java.util.Objects;

/**
 * This class starts a bunch of games for every contribution without any GUI to see how
 * the AI performs compared to other AIs
 */

public class TetrisHighscore {

	private static final int THREADS = 4;
	private static final int NUMBER_OF_ROUNDS = 1000;

	public static void main(String[] args) throws Exception {
		TetrisHighscore batch = new TetrisHighscore();
		batch.threads(THREADS).numberOfRounds(NUMBER_OF_ROUNDS);
		batch.setOnGameFinish(TetrisHighscore::onBatchFinished);
		batch.start();
	}

	private static void onBatchFinished(StonePositioner positioner, Score totalScore, int finishedRounds) {
		System.out.print(positioner.getDisplayName() + ":");
		System.out.print("\t\tstoneCount = " + totalScore.getStoneCount() / finishedRounds);
		System.out.print("\t\tlinesRemoved = " + totalScore.getLinesRemoved() / finishedRounds);
		System.out.print("\t\tscore = " + totalScore.getScore() / finishedRounds);
		System.out.println();
	}

	private int threads = 4;
	private int numberOfRounds = 1000;
	private OnGameFinished onGameFinish = (positioner, total, finishedRounds) -> total.getClass();

	private TetrisBatch[] games;
	private int currentGame;

	public void start() throws Exception {
		List<StonePositioner> stonePositioners = StonePositioners.getStonePositioners();

		this.games = new TetrisBatch[stonePositioners.size()];
		for (int i = 0; i < this.games.length; i++) {
			int threads = Math.max(1, this.threads / this.games.length);
			StonePositioner stonePositioner = stonePositioners.get(i);
			TetrisBatch game = new TetrisBatch(stonePositioner);
			game.threads(threads).numberOfRounds(this.numberOfRounds);
			game.setOnBatchFinish(totalScore -> onGameFinished(stonePositioner, totalScore, game.getFinishedRounds()));

			this.games[i] = game;
		}
		System.out.println("Starting games for first " + this.threads + " positioners...");
		for (int i = 0; i < this.threads; i++) {
			startNextGame();
		}
	}

	private void onGameFinished(StonePositioner positioner, Score totalScore, Integer finishedRounds) {
		this.onGameFinish.onBatchFinished(positioner, totalScore, finishedRounds);

		if (positioner == this.games[this.games.length - 1].getPositioner()) {
			System.out.println("Finished.");
		}

		startNextGame();
	}

	private void startNextGame() {
		if (this.games != null) {
			if (this.currentGame < this.games.length) {
				this.games[this.currentGame].start();
				this.currentGame++;
			}
		}
	}

	public int getNumberOfRounds() {
		return this.numberOfRounds;
	}

	public TetrisHighscore numberOfRounds(int newNumberOfRounds) {
		setNumberOfRounds(newNumberOfRounds);
		return this;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public OnGameFinished getOnGameFinish() {
		return this.onGameFinish;
	}

	public TetrisHighscore onGameFinish(OnGameFinished newOnGameFinish) {
		setOnGameFinish(newOnGameFinish);
		return this;
	}

	public void setOnGameFinish(OnGameFinished onGameFinish) {
		this.onGameFinish = Objects.requireNonNull(onGameFinish);
	}

	public int getThreads() {
		return this.threads;
	}

	public TetrisHighscore threads(int newThreads) {
		setThreads(newThreads);
		return this;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	/*
	 *
	 */

	public static interface OnGameFinished {

		void onBatchFinished(StonePositioner positioner, Score totalScore, int finishedRounds);

	}
}
