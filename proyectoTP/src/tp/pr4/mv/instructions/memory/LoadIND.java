package tp.pr4.mv.instructions.memory;

/**
 * Clase encargada de realizar las operaciones de LoadIND.
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
import tp.pr4.mv.instructions.NoParamOneOperandInstruction;

public class LoadIND extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción LoadInd.
	 * 
	 */
	public LoadIND() {
		super("LOADIND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción LoadInd.
	 * 
	 * @return Devuelve el nuevo objeto LoadInd.
	 */
	@Override
	protected Instruction createInstruction() {
		return new LoadIND();
	}
	
	/**
	 * Realiza la operación LoadInd apilando en la pila el valor de la memoria en la posicion indicada por el contenido de la cima de la pila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	@Override
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand)throws InstructionsException{
		try {
			if (memory.canLoad(operand)){
				try {
					try {
						return stack.push(memory.load(operand));
					} catch (StackException se) {
						// TODO Auto-generated catch block
						throw new InstructionsException(se);
					}
				} catch (MemoryException me) {
					// TODO Auto-generated catch block
					throw new InstructionsException(me);
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
