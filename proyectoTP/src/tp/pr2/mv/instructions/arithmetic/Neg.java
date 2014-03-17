package tp.pr2.mv.instructions.arithmetic;

/**
 * Clase encargada de realizar las operaciones de negación.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamOneOperandInstruction;

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
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand){
		operand = operand * -1;
		stack.pop();
		return stack.push(operand);
	}
}
