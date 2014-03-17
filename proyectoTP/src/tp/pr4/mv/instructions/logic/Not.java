package tp.pr4.mv.instructions.logic;

/**
 * Clase encargada de realizar las operaciones not.
 * 
 * @author George y Alberto
 */

import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
import tp.pr4.mv.exceptions.stack.StackException;
import tp.pr4.mv.instructions.NoParamOneOperandInstruction;

public class Not extends NoParamOneOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción Not.
	 */
	public Not() {
		super("NOT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Not.
	 * 
	 * @return Devuelve el nuevo objeto Not.
	 */
	protected Instruction createInstruction(){
		return new Not();
	}
	
	/**
	 * Realiza la operación not y apila 1 si se cumple la condición, 0 c.c.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		stack.pop();
		if(operand == 0){
			try {
				return stack.push(1);
			} catch (StackException se) {
				// TODO Auto-generated catch block
				throw new InstructionsException(se);
			}
		}
		else{
			try {
				return stack.push(0);
			} catch (StackException se) {
				// TODO Auto-generated catch block
				throw new InstructionsException(se);
			}
		}
	}
}
