package tp.pr2.mv.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

import tp.pr2.mv.CPU;
import tp.pr2.mv.ProgramMV;

public class CPUTest {


	
	@Test
	public void testStep() {
		CPU cpu = new CPU();
		assertFalse("No es posible ejecutar sin haber cargado el programa", cpu.step());
		ProgramMV program = new ProgramMV();
		program.readProgram(new Scanner("push 4\nhalt\nend"));
		cpu.loadProgram(program);
		assertEquals("Instrucción a ejecutar", "PUSH 4", cpu.getNextInstructionName());
		assertTrue(cpu.step());
		assertEquals("Instrucción a ejecutar", "HALT", cpu.getNextInstructionName());
		assertTrue(cpu.step());
		assertFalse("No es posible ejecutar una MV parada", cpu.step());
		assertFalse("No es posible ejecutar una MV parada", cpu.step());

		cpu = new CPU();
		program = new ProgramMV();
		program.readProgram(new Scanner("push 4\nadd\nhalt\nend"));
		cpu.loadProgram(program);
		assertTrue(cpu.step());
		//Comprobamos que si se produce un error de ejecución de la instrucción, el CP no avanza
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		assertFalse("Ejecución de instrucción incorrecta",cpu.step());
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		assertFalse("No es posible ejecutar una MV parada", cpu.step());
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		
	}
	
	@Test
	public void testError() {
		CPU cpu = new CPU();
		ProgramMV program = new ProgramMV();
		program.readProgram(new Scanner("push 4\nadd\nhalt\nend"));
		cpu.loadProgram(program);
		assertTrue(cpu.step());
		//Comprobamos que si se produce un error de ejecución de la instrucción, el CP no avanza
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		assertFalse("Ejecución de instrucción incorrecta",cpu.step());
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		assertFalse("No es posible ejecutar una MV parada", cpu.step());
		assertEquals("Instrucción a ejecutar es add", "ADD", cpu.getNextInstructionName());
		
	}


	@Test
	public void testIsHalted(){
		CPU cpu = new CPU();
		assertFalse(cpu.isHalted());
		ProgramMV program = new ProgramMV();
		program.readProgram(new Scanner("halt\nend"));
		cpu.loadProgram(program);
		cpu.step();
		assertTrue(cpu.isHalted());
	}

}
