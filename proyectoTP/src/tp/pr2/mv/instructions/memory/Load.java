package tp.pr2.mv.instructions.memory;

/**
 * Clase encargada de realizar las operaciones de load.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.OneParamNoOperandInstruction;

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
		return 	new Load(param);
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
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control){
		if (memory.canLoad(param)){
			return stack.push(memory.load(param));
		}else{
			return false;
		}
	}
}
