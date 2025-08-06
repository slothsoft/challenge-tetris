package de.slothsoft.tetris.contrib;

import java.util.Objects;

import de.slothsoft.tetris.PositionBasedStonePositioner;
import de.slothsoft.tetris.StonePositioner;

/**
 * Base class for a {@link StonePositioner} or {@link PositionBasedStonePositioner} that
 * already implements <code>hashCode()</code>, <code>equals(Object)</code> and
 * <code>toString()</code>
 * 
 * @since 1.0.0
 */

public abstract class AbstractStonePositioner implements StonePositioner {

	@Override
	public int hashCode() {
		return 37 * (getDisplayName() == null ? 3 : getDisplayName().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractStonePositioner that = (AbstractStonePositioner) obj;
		if (!Objects.equals(getDisplayName(), that.getDisplayName())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
