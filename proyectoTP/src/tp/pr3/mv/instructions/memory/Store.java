package tp.pr3.mv.instructions.memory;

/**
 * Clase encargada de realizar las operaciones de store.
 * 
 * @author George y Alberto
 */

import tp.pr3.mv.ControlUnit;
import tp.pr3.mv.Instruction;
import tp.pr3.mv.Memory;
import tp.pr3.mv.OperandStack;
import tp.pr3.mv.exceptions.instructions.InstructionsException;
import tp.pr3.mv.exceptions.memory.MemoryException;
import tp.pr3.mv.instructions.OneParamOneOperandInstruction;

public class Store extends OneParamOneOperandInstruction implements Instruction{

	/**
	 * Constructora por defecto.
	 */
	public Store() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción Store.
	 * 
	 * @param param- parametro de la instrucción
	 */
	public Store(int param) {
		super("STORE", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Store.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto Store.
	 */
	protected Instruction createInstruction(int param){
		return new Store(param);
	}
	
	/**
	 * Realiza la operación store y guarda la cima de la pila en la posición de memoria indicada.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
			try {
				return memory.store(param, operand);
			} catch (MemoryException me) {
				// TODO Auto-generated catch block
				throw new InstructionsException(me);
			}
	}
}
