package tp.pr5.mv.Model.instructions.comparison;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.NoParamTwoOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de comparación mayor que.
 * 
 * @author George y Alberto
 */




public class GT extends NoParamTwoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción GT.
	 * 
	 */
	public GT() {
		super("GT");
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción GT.
	 * 
	 * @return Devuelve el nuevo objeto GT.
	 */
	protected Instruction createInstruction(){
		return new GT();
	}
	
	/**
	 * Realiza la operación de comparación mayor que y apila 1 si la condición se cumple, 0 c.c.
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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException{
		if(operand2>operand1){
			try {
				stack.push(1);
			} catch (StackException se) {
				// TODO Auto-generated catch block
				throw new InstructionsException(se);
			}
		}
		else{
			try {
				stack.push(0);
			} catch (StackException se) {
				// TODO Auto-generated catch block
				throw new InstructionsException(se);
			}
		}
	}
}
