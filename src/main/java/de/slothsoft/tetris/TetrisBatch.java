package de.slothsoft.tetris;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * This class starts a bunch of games without any GUI to see how the AI performs in the
 * long run
 */

public class TetrisBatch {

	private static final StonePositioner POSITIONER = Tetris.POSITIONER;
	private static final int THREADS = 4;
	private static final int NUMBER_OF_ROUNDS = 1000;

	public static void main(String[] args) throws Exception {
		TetrisBatch batch = new TetrisBatch(POSITIONER);
		batch.threads(THREADS).numberOfRounds(NUMBER_OF_ROUNDS);
		batch.setOnRoundFinish((game, score) -> onRoundFinished(game, score, batch.getFinishedRounds()));
		batch.setOnBatchFinish(totalScore -> onBatchFinished(totalScore, batch.getFinishedRounds()));
		batch.start();
	}

	private static void onRoundFinished(Game game, Score score, int finishedRounds) {
		final String STRING_FORMAT = "%0" + String.valueOf(NUMBER_OF_ROUNDS).length() + "d";
		System.out.println(String.format(STRING_FORMAT, finishedRounds) + ' ' + score.toString());
	}

	private static void onBatchFinished(Score totalScore, int finishedRounds) {
		System.out.println("============================================");
		System.out.println(totalScore.toString(true));
		System.out.println("--------------------------------------------");
		System.out.println("Average:");
		System.out.println("\tstoneCount\t= " + totalScore.getStoneCount() / finishedRounds);
		System.out.println("\tlinesRemoved\t= " + totalScore.getLinesRemoved() / finishedRounds);
		System.out.println("\tscore\t\t= " + totalScore.getScore() / finishedRounds);
		System.out.println("============================================");
	}

	private final StonePositioner positioner;

	private int threads = 4;
	private int numberOfRounds = 1000;
	private BiConsumer<Game, Score> onRoundFinish = (game, score) -> score.getClass();
	private Consumer<Score> onBatchFinish = (total) -> total.getClass();

	private int finishedRounds;
	private int stoppedGames;
	private final Score totalScore = new Score();

	public TetrisBatch(StonePositioner positioner) {
		this.positioner = Objects.requireNonNull(positioner);
	}

	public void start() {
		for (int i = 0; i < this.threads; i++) {
			try {
				Game game = new Game();
				game.setStonePositioner(this.positioner.getClass().newInstance());
				game.setTimePerStone(0);
				game.setOnGameFinish(score -> roundFinished(game, score));
				game.start();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void roundFinished(Game game, Score score) {
		this.onRoundFinish.accept(game, score);
		this.finishedRounds++;
		this.totalScore.add(score);

		if (this.finishedRounds > NUMBER_OF_ROUNDS) {
			stopGame(game);
		}
	}

	private void stopGame(Game game) {
		game.stopGame();
		this.stoppedGames++;

		if (this.stoppedGames >= this.threads) {
			this.onBatchFinish.accept(this.totalScore);
		}
	}

	public int getFinishedRounds() {
		return this.finishedRounds;
	}

	public Score getTotalScore() {
		return this.totalScore;
	}

	public int getNumberOfRounds() {
		return this.numberOfRounds;
	}

	public TetrisBatch numberOfRounds(int newNumberOfRounds) {
		setNumberOfRounds(newNumberOfRounds);
		return this;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public int getThreads() {
		return this.threads;
	}

	public TetrisBatch threads(int newThreads) {
		setThreads(newThreads);
		return this;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}

	public BiConsumer<Game, Score> getOnRoundFinish() {
		return this.onRoundFinish;
	}

	public TetrisBatch onRoundFinish(BiConsumer<Game, Score> newOnRoundFinish) {
		setOnRoundFinish(newOnRoundFinish);
		return this;
	}

	public void setOnRoundFinish(BiConsumer<Game, Score> onRoundFinish) {
		this.onRoundFinish = Objects.requireNonNull(onRoundFinish);
	}

	public Consumer<Score> getOnBatchFinish() {
		return this.onBatchFinish;
	}

	public TetrisBatch onBatchFinish(Consumer<Score> newOnBatchFinish) {
		setOnBatchFinish(newOnBatchFinish);
		return this;
	}

	public void setOnBatchFinish(Consumer<Score> onBatchFinish) {
		this.onBatchFinish = Objects.requireNonNull(onBatchFinish);
	}

	public StonePositioner getPositioner() {
		return this.positioner;
	}

}
