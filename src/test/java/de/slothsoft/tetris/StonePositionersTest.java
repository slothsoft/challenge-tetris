package de.slothsoft.tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.contrib.ExampleStonePositioner;
import de.slothsoft.tetris.contrib1.A;
import de.slothsoft.tetris.contrib1.B;
import de.slothsoft.tetris.contrib1.C;
import de.slothsoft.tetris.contrib2.Apple;
import de.slothsoft.tetris.contrib2.Banana;
import de.slothsoft.tetris.contrib2.Orange;
import de.slothsoft.tetris.contrib2.Pear;
import de.slothsoft.tetris.contrib3.One;
import de.slothsoft.tetris.contrib3.Three;
import de.slothsoft.tetris.contrib3.Two;

public class StonePositionersTest {

	@Test
	public void testGetClasses() throws Exception {
		List<Class<?>> result = StonePositioners.getClasses("de.slothsoft.tetris.contrib1");
		Assert.assertEquals(Arrays.asList(A.class, B.class, C.class), result);
	}

	@Test
	public void testGetClassesUnknownPackage() throws Exception {
		List<Class<?>> result = StonePositioners.getClasses("de.slothsoft.unknown");
		Assert.assertEquals(new ArrayList<>(), result);
	}

	@Test
	public void testGetPositioners() throws Exception {
		List<StonePositioner> result = StonePositioners.getStonePositioners(Apple.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Banana(), new Orange(), new Pear()), result);
	}

	@Test
	public void testGetPositionersIgnoreAbstractClasses() throws Exception {
		List<StonePositioner> result = StonePositioners.getStonePositioners(One.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Three(), new Two()), result);
	}

	@Test
	public void testGetDefaultPositioners() throws Exception {
		List<StonePositioner> result = StonePositioners.getStonePositioners();
		Assert.assertTrue("The example is missing: " + result, result.contains(new ExampleStonePositioner()));
	}

}
