package tp.pr2.mv.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tp.pr2.mv.Memory;

public class MemoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testStore() {
		Memory mem = new Memory(2);
		assertFalse("Las direcciones de memoria deben ser positivas", mem.store(-1, 0));
		assertTrue("El rango de direcciones es infinito", mem.store(Integer.MAX_VALUE, 0));
		assertTrue(mem.store(2,2));
		assertFalse("No se puede superar la capacidad de la memoria", mem.store(3, 3));
		assertTrue("Debe ser posible reescribir posiciones de memoria aunque esté llena", mem.store(2, 5));
		assertEquals("Deben reescribirse las posiciones de memoria", 5, mem.load(2));
	}

	@Test
	public void testLoad() {
		Memory mem = new Memory(2);
		mem.store(1, 1);
		assertEquals(1,mem.load(1));
		assertEquals("Al acceder a una posición incorrecta debe devolverse 0", 0,mem.load(-1));
		assertEquals("Al acceder a una posición incorrecta debe devolverse 0", 0,mem.load(25));
		mem.store(1,2);
		assertEquals("Deben reescribirse las posiciones de memoria", 2, mem.load(1));
	}

	@Test
	public void testCanLoad() {
		Memory mem = new Memory(2);
		mem.store(1, 1);
		assertTrue(mem.canLoad(1));
		assertFalse("No puede accederse a direcciones negativas", mem.canLoad(-1));
		assertFalse("No puede accederse a direcciones no insertadas previamente", mem.canLoad(25));
	}

	@Test 
	public void testToString(){
		Memory mem = new Memory(2);
		assertEquals("<vacia>", mem.toString());
		
		mem.store(1, 2);
		mem.store(3, 4);
		assertEquals("[1]:2 [3]:4", mem.toString());
		
	}
	
	

}
