package tp.pr3.mv.instructions.logic;

/**
 * Clase encargada de realizar las operaciones and.
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

public class And extends NoParamTwoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción And.
	 */
	public And() {
		super("AND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción And.
	 * 
	 * @return Devuelve el nuevo objeto And.
	 */
	protected Instruction createInstruction(){
		return new And();
	}
	
	/**
	 * Realiza la operación and y apila 1 si se cumple la condición, 0 c.c.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException{
		if(operand1 == 1 && operand2 ==1){
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
