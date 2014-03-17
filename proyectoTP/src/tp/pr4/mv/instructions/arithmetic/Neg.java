package tp.pr4.mv.instructions.arithmetic;

/**
 * Clase encargada de realizar las operaciones de negación.
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

public class Neg extends NoParamOneOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción Neg.
	 * 
	 */
	public Neg() {
		super("NEG");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Neg.
	 * 
	 * @return Devuelve el nuevo objeto Neg.
	 */
	protected Instruction createInstruction(){
		return new Neg();
	}
	
	/**
	 * Realiza la operación de negación y apila el resultado.
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
		operand = operand * -1;
		stack.pop();
		try {
			return stack.push(operand);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
}
