package tp.pr2.mv.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.arithmetic.Add;
import tp.pr2.mv.instructions.arithmetic.Div;
import tp.pr2.mv.instructions.arithmetic.Mult;
import tp.pr2.mv.instructions.arithmetic.Sub;
import tp.pr2.mv.instructions.comparison.EQ;
import tp.pr2.mv.instructions.comparison.GT;
import tp.pr2.mv.instructions.comparison.LE;
import tp.pr2.mv.instructions.comparison.LT;
import tp.pr2.mv.instructions.io.Out;
import tp.pr2.mv.instructions.jump.BF;
import tp.pr2.mv.instructions.jump.BT;
import tp.pr2.mv.instructions.jump.Jump;
import tp.pr2.mv.instructions.logic.And;
import tp.pr2.mv.instructions.logic.Not;
import tp.pr2.mv.instructions.logic.Or;
import tp.pr2.mv.instructions.memory.Load;
import tp.pr2.mv.instructions.memory.Store;
import tp.pr2.mv.instructions.misc.Halt;
import tp.pr2.mv.instructions.stack.Dup;
import tp.pr2.mv.instructions.stack.Flip;
import tp.pr2.mv.instructions.stack.Pop;
import tp.pr2.mv.instructions.stack.Push;

public class InstructionsTest {

	Instruction out = new Out();
	Instruction push1 = new Push(1);
	Instruction push2 = new Push(2);
	Instruction push3 = new Push(3);
	Instruction push4 = new Push(4);
	OperandStack stack = null;
	Memory memory = null;
	ControlUnit control = null;
	
	@Test
	public void testArithmetic(){
		test2operands(new Add(),1,2,3);
		test2operands(new Sub(),2,1,-1);
		test2operands(new Mult(),3,2,6);
		test2operands(new Div(),2,12,6);
		
		stack = new OperandStack(3);
		stack.push(3);
		stack.push(0);
		Instruction ins = new Div();
		assertFalse(ins.execute(stack, memory, control));
		checkStack("3 0");
	}
	
	@Test
	public void testLogic(){
		test2operands(new And(),1,0,0);
		test2operands(new And(),1,1,1);
		test2operands(new Or(),1,0,1);
		test2operands(new Or(),0,0,0);
		test1operands(new Not(),1,0);
		test1operands(new Not(),0,1);
	}
	
	@Test
	public void testComparison(){
		test2operands(new EQ(),1,0,0);
		test2operands(new EQ(),1,1,1);
		test2operands(new GT(),3,10,1);
		test2operands(new GT(),3,0,0);
		test2operands(new LT(),3,10,0);
		test2operands(new LT(),3,0,1);
		test2operands(new LE(),3,10,0);
		test2operands(new LE(),3,3,1);
	}
	
	@Test
	public void testJump(){
		control = new ControlUnit();
		Instruction ins = new Jump(3);
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(3,control.getCP());
		ins = new Jump(6);
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(6,control.getCP());

		Instruction wrongIns = new Jump(-10);
		assertFalse(wrongIns.execute(stack, memory, control));

		
		stack=new OperandStack(3);
		wrongIns = new BF(-10);
		ins = new BF(10);
		assertFalse(ins.execute(stack, memory, control));
		checkStack("<vacia>");
		stack.push(1);
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(6,control.getCP());
		checkStack("<vacia>");
		stack.push(0);
		assertFalse(wrongIns.execute(stack, memory, control));
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(10,control.getCP());
		checkStack("<vacia>");

		ins = new BT(20);
		wrongIns = new BT(-10);
		assertFalse(ins.execute(stack, memory, control));
		checkStack("<vacia>");
		stack.push(0);
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(10,control.getCP());
		checkStack("<vacia>");
		stack.push(1);
		assertFalse(wrongIns.execute(stack, memory, control));
		assertTrue(ins.execute(stack, memory, control));
		assertEquals(20,control.getCP());
		checkStack("<vacia>");		
	}
	
	
	@Test
	public void testStore(){
		stack = new OperandStack(3);
		memory = new Memory(2);
		Instruction ins = new Store(-10);
		assertFalse("No se puede ejecutar STORE cuando la pila está vacía", ins.execute(stack, memory, control));
		assertTrue(push1.execute(stack, memory, control));
		assertFalse("No se puede ejecutar STORE con una dirección negativa", ins.execute(stack, memory, control));
		ins = new Store(0);
		assertTrue(ins.execute(stack, memory, control));
		checkMemory("[0]:1");
		assertTrue(push2.execute(stack, memory, control));
		ins = new Store(1);
		assertTrue(ins.execute(stack, memory, control));
		checkMemory("[0]:1 [1]:2");
		assertTrue(push3.execute(stack, memory, control));
		ins = new Store(2);
		assertFalse("No se puede ejecutar store cuando la memoria está llena", ins.execute(stack, memory, control));
		checkStack("3");
		ins = new Store(1);
		assertTrue("Debe ser posible reescribir posiciones de memoria aunque esté llena",ins.execute(stack, memory, control));
		checkMemory("[0]:1 [1]:3");
	}
	
	@Test
	public void testLoad(){
		stack = new OperandStack(1);
		memory = new Memory(2);
		memory.store(0, 5);
		Instruction ins = new Load(2);
		assertFalse("Dirección incorrecta",ins.execute(stack, memory, control));
		ins = new Load(0);
		assertTrue(ins.execute(stack, memory, control));
		checkStack("5");
		assertFalse("Pila llena", ins.execute(stack, memory, control));

	}

	@Test
	public void testOut() {
		stack = new OperandStack(3);
		assertFalse("No se puede ejecutar OUT cuando la pila está vacía", out.execute(stack, null, null));
		push1.execute(stack, memory, control);
		assertTrue(out.execute(stack, memory, control));
	}
	
	@Test
	public void testHalt(){
		control = new ControlUnit();
		assertFalse(control.isHalted());
		Instruction ins = new Halt();
		ins.execute(stack, memory, control);
		assertTrue(control.isHalted());
	}
	
	@Test
	public void testPush() {
		stack = new OperandStack(3);
		assertTrue(push1.execute(stack, memory, control));
		assertTrue(push2.execute(stack, memory, control));
		checkStack("1 2");
		assertTrue(push3.execute(stack, memory, control));
		assertFalse("No es posible ejecutar PUSH cuando la pila está llena", push4.execute(stack, memory, control));
		checkStack("1 2 3");
	}
	
	@Test
	public void testPop(){
		stack = new OperandStack(3);
		Instruction ins = new Pop();
		assertFalse(ins.execute(stack, memory, control));
		push1.execute(stack, memory, control);
		assertTrue(ins.execute(stack, memory, control));
		checkStack("<vacia>");
	}
	
	@Test
	public void testDup(){
		stack = new OperandStack(2);
		Instruction ins = new Dup();
		assertFalse(ins.execute(stack, memory, control));
		push1.execute(stack, memory, control);
		assertTrue(ins.execute(stack, memory, control));
		checkStack("1 1");
		assertFalse(ins.execute(stack, memory, control));
		checkStack("1 1");
	}
	
	@Test
	public void testFlip(){
		stack = new OperandStack(2);
		Instruction ins = new Flip();
		assertFalse(ins.execute(stack, memory, control));
		push1.execute(stack, memory, control);
		assertFalse(ins.execute(stack, memory, control));
		push2.execute(stack, memory, control);
		checkStack("1 2");
		assertTrue(ins.execute(stack, memory, control));
		checkStack("2 1");
	}	


	private void test1operands(Instruction ins, int cima, int resultado) {
		stack = new OperandStack(3);
		assertFalse(ins.execute(stack, memory, control));
		checkStack("<vacia>");
		stack.push(cima);
		assertTrue(ins.execute(stack, memory, control));
		checkStack(String.valueOf(resultado));
	}
	
	private void test2operands(Instruction ins, int cima, int subcima, int resultado) {
		stack = new OperandStack(3);
		assertFalse(ins.execute(stack, memory, control));
		checkStack("<vacia>");
		stack.push(subcima);
		assertFalse(ins.execute(stack, memory, control));
		checkStack(String.valueOf(subcima));
		stack.push(cima);
		assertTrue(ins.execute(stack, memory, control));
		checkStack(String.valueOf(resultado));
	}
	
	private void checkStack(String expected) {
		String sinfo = stack.toString();
		assertEquals("El estado de la pila debe ser: "+stack, expected, sinfo);
	}
	
	private void checkMemory(String memory) {
		String sinfo = memory.toString();
		assertEquals("El estado de la memoria debe ser: "+memory, memory, sinfo);
	}

}
