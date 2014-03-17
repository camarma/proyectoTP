package tp.pr2.mv.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tp.pr2.mv.OperandStack;

public class OperandStackTest {

	@Before
	public void setUp() throws Exception {
		
	}


	@Test
	public void testIsEmpty() {
		OperandStack stack = new OperandStack(5);
		assertTrue("Una pila recién creada debe estar vacia", stack.isEmpty());
		stack.push(1);
		stack.pop();
		assertTrue("La pila debe estar vacia", stack.isEmpty());
	}


	@Test
	public void testPush() {
		OperandStack stack = new OperandStack(1);
		stack.push(4);
		assertFalse("La pila está llena y no se debe poder apilar", stack.push(5));
	}

	@Test
	public void testPop() {
		OperandStack stack = new OperandStack(2);
		stack.push(4);
		stack.push(5);
		assertEquals(5,stack.top());
		stack.pop();
		assertEquals(4,stack.top());
	}
	
	@Test 
	public void testToString(){
		OperandStack stack = new OperandStack(2);
		assertEquals(stack.toString(),"<vacia>");
		
		stack.push(2);
		stack.push(1);
		assertEquals("2 1",stack.toString());
		
	}

}
