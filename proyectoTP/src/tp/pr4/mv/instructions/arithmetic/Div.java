package tp.pr4.mv.instructions.arithmetic;

/**
 * Clase encargada de realizar las operaciones de división.
 * 
 * @author George y Alberto
 */

import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.instructions.NoParamTwoOperandInstruction;
import tp.pr4.mv.exceptions.instructions.ArithmeticException;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
import tp.pr4.mv.exceptions.stack.StackException;

public class Div extends NoParamTwoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Div.
	 * 
	 */
	public Div() {
		super("DIV");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Div.
	 * 
	 * @return Devuelve el nuevo objeto Div.
	 */
	protected Instruction createInstruction(){
		return new Div();
	}
	
	/**
	 * Realiza la operación de división y apila el resultado.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException{
		if(operand1 != 0){
			try {
				return stack.push(operand2/operand1);
			} catch (StackException se) {
				// TODO Auto-generated catch block
				throw new InstructionsException(se);
			}
		}else{
			//return false;
			throw new ArithmeticException("No se puede dividir por 0:",operand2,operand1);
		}
	}
}
