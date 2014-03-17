package tp.pr4.mv.instructions.comparison;

/**
 * Clase encargada de realizar las operaciones de comparación menor que.
 * 
 * @author George y Alberto
 */


import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
import tp.pr4.mv.exceptions.stack.StackException;
import tp.pr4.mv.instructions.NoParamTwoOperandInstruction;

public class LT extends NoParamTwoOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción LT.
	 * 
	 */
	public LT() {
		super("LT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción LT.
	 * 
	 * @return Devuelve el nuevo objeto LT.
	 */
	protected Instruction createInstruction(){
		return new LT();
	}
	
	/**
	 * Realiza la operación de comparación menor que y apila 1 si la condicion se cumple, 0 en c.c.
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
		if(operand2<operand1){
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
