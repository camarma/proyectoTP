package tp.pr2.mv.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tp.pr2.mv.ControlUnit;

public class ControlUnitTest {


	
	@Test
	public void testNext() {
		ControlUnit cu = new ControlUnit();
		assertEquals(0,cu.getCP());
		cu.next();
		assertEquals(1,cu.getCP());
		
		
		cu.setCP(5);
		assertEquals(5,cu.getCP());
		cu.next();
		assertEquals(5,cu.getCP());
		cu.next();
		assertEquals(6,cu.getCP());

		cu.increaseCP(4);
		assertEquals(10,cu.getCP());
		cu.next();
		assertEquals(10,cu.getCP());
		cu.next();
		assertEquals(11,cu.getCP());
	}
	
	@Test
	public void testHalt()
	{
		ControlUnit cu = new ControlUnit();
		assertFalse(cu.isHalted());
		assertTrue(cu.setCP(0));
		assertTrue(cu.increaseCP(3));
		cu.halt();
		assertTrue(cu.isHalted());
		assertFalse(cu.setCP(0));
		assertFalse(cu.increaseCP(3));

		cu.next();
		assertEquals(3,cu.getCP());
		cu.next();
		assertEquals(3,cu.getCP());

	}
	
	@Test
	public void testSetCP()
	{
		ControlUnit cu = new ControlUnit();
		assertTrue(cu.setCP(5));
		assertFalse(cu.setCP(-5));
		cu.halt();
		assertFalse(cu.setCP(5));
	}
	
	@Test
	public void testIncreaseCP()
	{
		ControlUnit cu = new ControlUnit();
		assertTrue(cu.increaseCP(5));
		cu.halt();
		assertFalse(cu.increaseCP(5));
	}



}
