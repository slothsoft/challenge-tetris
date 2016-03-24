package de.slothsoft.tetris;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.tetris.contrib.ExampleStonePositioner;

public class TetrisArgumentsTest {

	@Test
	public void testGetStonePositionerEmpty() {
		StonePositioner positioner = context -> System.out.println();
		StonePositioner result = TetrisArguments.getStonePositioner(new String[0], positioner);

		Assert.assertSame(positioner, result);
	}

	@Test
	public void testGetStonePositionerNotSet() {
		StonePositioner positioner = context -> System.out.println();
		String[] arguments = { "one", "two", "three" };
		StonePositioner result = TetrisArguments.getStonePositioner(arguments, positioner);

		Assert.assertSame(positioner, result);
	}

	@Test
	public void testGetStonePositionerSet() {
		StonePositioner positioner = context -> System.out.println();
		String[] arguments = { "-stonePositioner=ExampleStonePositioner" };
		StonePositioner result = TetrisArguments.getStonePositioner(arguments, positioner);

		Assert.assertEquals(new ExampleStonePositioner(), result);
	}

	@Test
	public void testGetStonePositionerSetButEmpty() {
		StonePositioner positioner = context -> System.out.println();
		String[] arguments = { "-stonePositioner" };
		StonePositioner result = TetrisArguments.getStonePositioner(arguments, positioner);

		Assert.assertSame(positioner, result);
	}

	@Test
	public void testGetStonePositionerSetButEmpty2() {
		StonePositioner positioner = context -> System.out.println();
		String[] arguments = { "-stonePositioner=" };
		StonePositioner result = TetrisArguments.getStonePositioner(arguments, positioner);

		Assert.assertSame(positioner, result);
	}

	@Test
	public void testIsShowSettingsEmpty() {
		Assert.assertEquals(true, TetrisArguments.isShowSettings(new String[0], true));
	}

	@Test
	public void testIsShowSettingsNotSet() {
		String[] arguments = { "alpha", "beta", "gamma" };
		Assert.assertEquals(false, TetrisArguments.isShowSettings(arguments, false));
	}

	@Test
	public void testIsShowSettingsSet() {
		String[] arguments = { "-showSettings=false" };
		Assert.assertEquals(false, TetrisArguments.isShowSettings(arguments, true));
	}

	@Test
	public void testIsShowSettingsSetButEmpty() {
		String[] arguments = { "-showSettings" };
		Assert.assertEquals(true, TetrisArguments.isShowSettings(arguments, false));
	}

	@Test
	public void testIsShowSettingsSetButEmpty2() {
		String[] arguments = { "-showSettings=" };
		Assert.assertEquals(true, TetrisArguments.isShowSettings(arguments, true));
	}

	@Test
	public void testGetTimePerStoneEmpty() {
		Assert.assertEquals(0, TetrisArguments.getTimePerStone(new String[0], 0));
	}

	@Test
	public void testGetTimePerStoneNotSet() {
		String[] arguments = { "one", "two", "three" };
		Assert.assertSame(1, TetrisArguments.getTimePerStone(arguments, 1));
	}

	@Test
	public void testGetTimePerStoneSet() {
		String[] arguments = { "-timePerStone=300" };
		Assert.assertEquals(300, TetrisArguments.getTimePerStone(arguments, 3));
	}

	@Test
	public void testGetTimePerStoneSetButEmpty() {
		String[] arguments = { "-timePerStone" };
		Assert.assertSame(4, TetrisArguments.getTimePerStone(arguments, 4));
	}

	@Test
	public void testGetTimePerStoneSetButEmpty2() {
		String[] arguments = { "-timePerStone=" };
		Assert.assertSame(5, TetrisArguments.getTimePerStone(arguments, 5));
	}
}
