package tp.pr2.mv.instructions.stack;

/**
 * Clase encargada de realizar las operaciones pop.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamOneOperandInstruction;

public class Pop extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Pop.
	 */
	public Pop() {
		super("POP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Pop.
	 * 
	 * @return Devuelve el nuevo objeto Pop.
	 */
	protected Instruction createInstruction(){
		return new Pop();
	}
	
	/**
	 * Realiza la operación pop para desapilar de la pila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand){
		if(!stack.isEmpty()){
			stack.pop();
			return true;
		}
		return false;
	}
}
