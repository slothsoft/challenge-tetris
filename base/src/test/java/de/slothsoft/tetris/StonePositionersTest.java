package de.slothsoft.tetris;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.contrib.ExampleStonePositioner;

public class StonePositionersTest {

	@Test
	public void testGetDefaultPositioners() throws Exception {
		final List<StonePositioner> result = StonePositioners.getStonePositioners();
		Assert.assertTrue("The example is missing: " + result, result.contains(new ExampleStonePositioner()));
	}

}
