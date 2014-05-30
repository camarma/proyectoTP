package tp.pr5.mv.Model.instructions.memory;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.memory.MemoryException;
import tp.pr5.mv.Model.instructions.NoParamTwoOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de StoreIND.
 * 
 * @author George y Alberto
 */



public class StoreIND extends NoParamTwoOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción StoreInd.
	 * 
	 */
	public StoreIND() {
		super("STOREIND");
		// TODO Auto-generated constructor stub
	}

	/**
	 * Crea un objeto nuevo de la instrucción StoreInd.
	 * 
	 * @return Devuelve el nuevo objeto StoreInd.
	 */
	@Override
	protected Instruction createInstruction() {
		return new StoreIND();
	}

	/**
	 * Realiza la operación storeind, guargando en memoria el valor de la subcima en la posicion indicada por la cima.
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
	@Override
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException {
			try {
				memory.store(operand2, operand1);
			} catch (MemoryException me) {
				// TODO Auto-generated catch block
				throw new InstructionsException(me);
			}
	}

}
