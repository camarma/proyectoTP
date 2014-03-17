package tp.pr2.mv.instructions.stack;

/**
 * Clase encargada de realizar las operaciones flip.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamTwoOperandInstruction;

public class Flip extends NoParamTwoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Flip.
	 */
	public Flip() {
		super("FLIP");
	}

	/**
	 * Crea un objeto nuevo de la instrucción Flip.
	 * 
	 * @return Devuelve el nuevo objeto Flip.
	 */
	protected Instruction createInstruction(){
		return new Flip();
	}
	
	/**
	 * Realiza la operación flip que invierta ls posiscines de la cima y la subcima y los apila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2){
		if(stack.push(operand1)){
			return stack.push(operand2);
		}
		else{
			return false;
		}

	}
}

