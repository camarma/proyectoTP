package tp.pr3.mv.instructions.arithmetic;

/**
 * Clase encargada de realizar las operaciones de resta.
 * 
 * @author George y Alberto
 */

import tp.pr3.mv.ControlUnit;
import tp.pr3.mv.Instruction;
import tp.pr3.mv.Memory;
import tp.pr3.mv.OperandStack;
import tp.pr3.mv.exceptions.instructions.InstructionsException;
import tp.pr3.mv.exceptions.stack.StackException;
import tp.pr3.mv.instructions.NoParamTwoOperandInstruction;

public class Sub extends NoParamTwoOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción Sub.
	 * 
	 */
	public Sub() {
		super("SUB");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Sub.
	 * 
	 * @return Devuelve el nuevo objeto Sub.
	 */
	protected Instruction createInstruction(){
		return new Sub();
	}
	
	/**
	 * Realiza la operación de resta y apila el resultado.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 * @throws StackException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand1,  int operand2) throws InstructionsException{
		try {
			return stack.push(operand2-operand1);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
}
