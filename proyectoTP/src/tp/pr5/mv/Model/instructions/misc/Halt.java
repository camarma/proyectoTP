package tp.pr5.mv.Model.instructions.misc;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.instructions.NoParamNoOperandInstruction;

/**
 * Clase encargada de parar el programa.
 * 
 * @author George y Alberto
 */



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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control){
		control.halt();
	}
}
