package de.slothsoft.tetris;

import org.junit.Assert;
import org.junit.Test;

public class StoneFormTest {

	@Test
	public void testI() throws Exception {
		Assert.assertEquals("X\nX\nX\nX\n", new Stone(StoneForm.I).stringify());
	}

	@Test
	public void testJ() throws Exception {
		Assert.assertEquals("XX\nX \nX \n", new Stone(StoneForm.J).stringify());
	}

	@Test
	public void testL() throws Exception {
		Assert.assertEquals("X \nX \nXX\n", new Stone(StoneForm.L).stringify());
	}

	@Test
	public void testO() throws Exception {
		Assert.assertEquals("XX\nXX\n", new Stone(StoneForm.O).stringify());
	}

	@Test
	public void testS() throws Exception {
		Assert.assertEquals("X \nXX\n X\n", new Stone(StoneForm.S).stringify());
	}

	@Test
	public void testT() throws Exception {
		Assert.assertEquals("X \nXX\nX \n", new Stone(StoneForm.T).stringify());
	}

	@Test
	public void testZ() throws Exception {
		Assert.assertEquals(" X\nXX\nX \n", new Stone(StoneForm.Z).stringify());
	}
}
