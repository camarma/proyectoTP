package tp.pr2.mv.instructions.stack;

/**
 * Clase encargada de realizar las operaciones dup.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamOneOperandInstruction;

public class Dup extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Dup.
	 */
	public Dup() {
		super("DUP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Dup.
	 * 
	 * @return Devuelve el nuevo objeto Dup.
	 */
	protected Instruction createInstruction(){
		return new Dup();
	}
	
	/**
	 * Realiza la operación dup, duplica el contenido de la cima y lo apila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand){
		return stack.push(operand);
	}
}

