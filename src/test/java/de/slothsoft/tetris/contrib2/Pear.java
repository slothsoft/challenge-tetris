package de.slothsoft.tetris.contrib2;

import de.slothsoft.tetris.StonePositioner;

public class Pear implements StonePositioner {

	@Override
	public void position(Context context) {
		// nothing to do
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Pear;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "PearPositioner";
	}

}
