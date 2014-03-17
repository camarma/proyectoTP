package tp.pr4.mv.instructions.memory;

/**
 * Clase encargada de realizar las operaciones de load.
 * 
 * @author George y Alberto
 */

import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
import tp.pr4.mv.exceptions.memory.MemoryException;
import tp.pr4.mv.exceptions.stack.StackException;
import tp.pr4.mv.instructions.OneParamNoOperandInstruction;

public class Load extends OneParamNoOperandInstruction implements Instruction{

	/**
	 * Constructora por defecto.
	 */
	public Load() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción Load.
	 * 
	 * @param param- parametro de la instrucción
	 */
	public Load(int param){
		super ("LOAD", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Load.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto Load.
	 */
	protected Instruction createInstruction(int param){
		if (param >=0){
			return 	new Load(param);
		}
		else{
			return null;
		}
	}
	
	/**
	 * Realiza la operación Load apilando el valor obtenido de la memoria.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{	
		try {
			if (memory.canLoad(param)){
				try {
					return stack.push(memory.load(param));
				} catch (StackException se) {
					// TODO Auto-generated catch block
					throw new InstructionsException(se);
				}
			}else{
				return false;
			}
		} catch (MemoryException me) {
			// TODO Auto-generated catch block
			throw new InstructionsException(me);
		}

	}
}
