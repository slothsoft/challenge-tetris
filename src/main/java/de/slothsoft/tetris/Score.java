package de.slothsoft.tetris;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The score of the {@link Game}
 */

public final class Score implements Cloneable {

	public static final String PROP_STONE_COUNT = "stoneCount";
	public static final String PROP_LINES_REMOVED = "linesRemoved";
	public static final String PROP_SCORE = "score";

	private PropertyChangeSupport propertyChangeSupport;

	private long stoneCount;
	private long linesRemoved;
	private long score;

	public Score() {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public long getStoneCount() {
		return this.stoneCount;
	}

	void incrementStoneCount(long increment) {
		setStoneCount(this.stoneCount + increment);
	}

	private void setStoneCount(long stoneCount) {
		long oldValue = this.stoneCount;
		this.stoneCount = stoneCount;
		this.propertyChangeSupport.firePropertyChange(PROP_STONE_COUNT, oldValue, this.stoneCount);
	}

	void incrementLinesRemoved(long increment) {
		setLinesRemoved(this.linesRemoved + increment);
	}

	private void setLinesRemoved(long linesRemoved) {
		long oldValue = this.linesRemoved;
		this.linesRemoved = linesRemoved;
		this.propertyChangeSupport.firePropertyChange(PROP_LINES_REMOVED, oldValue, this.linesRemoved);
	}

	public long getLinesRemoved() {
		return this.linesRemoved;
	}

	void incrementScore(long increment) {
		setScore(this.score + increment);
	}

	private void setScore(long score) {
		long oldValue = this.score;
		this.score = score;
		this.propertyChangeSupport.firePropertyChange(PROP_SCORE, oldValue, this.score);
	}

	public long getScore() {
		return this.score;
	}

	public void add(Score score) {
		incrementStoneCount(score.stoneCount);
		incrementLinesRemoved(score.linesRemoved);
		incrementScore(score.score);
	}

	public void clear() {
		setStoneCount(0);
		setLinesRemoved(0);
		setScore(0);
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean verbos) {
		if (verbos)
			return "Score: \n\tstoneCount\t= " + this.stoneCount + "\n\tlinesRemoved\t= " + this.linesRemoved
					+ "\n\tscore\t\t= " + this.score;
		return "Scoret=" + this.stoneCount + ", linesRemoved=" + this.linesRemoved + ", score=" + this.score + "]";
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

}
