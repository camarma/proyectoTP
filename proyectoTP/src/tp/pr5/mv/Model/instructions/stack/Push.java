package tp.pr5.mv.Model.instructions.stack;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.OneParamNoOperandInstruction;

/**
 * Clase encargada de realizar las operaciones push.
 * 
 * @author George y Alberto
 */



public class Push extends OneParamNoOperandInstruction implements Instruction{
	
	/**
	 * Constructora por defecto.
	 */
	public Push() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción Push.
	 * 
	 * @param param - parámetro de la instrucción.
	 */
	public Push(int param) {
		super("PUSH",param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Push.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto Push.
	 */
	protected Instruction createInstruction(int param){
		return new Push(param);
	}
	
	/**
	 * Realiza la operación push apilando el parametro en la pila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws StackException 
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			stack.push(param);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
}
