package tp.pr2.mv.instructions.misc;

/**
 * Clase encargada de parar el programa.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamNoOperandInstruction;

public class Halt extends NoParamNoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Halt.
	 */
	public Halt() {
		super("HALT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Halt.
	 * 
	 * @return Devuelve el nuevo objeto Halt.
	 */
	protected Instruction createInstruction(){
		return new Halt();
	}
	
	/**
	 * Realiza la operación halt que detien el programa.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control){
		control.halt();
		return true;
	}
}
