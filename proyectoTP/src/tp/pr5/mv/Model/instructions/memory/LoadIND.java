package tp.pr5.mv.Model.instructions.memory;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.memory.MemoryException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de LoadIND.
 * 
 * @author George y Alberto
 */



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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand)throws InstructionsException{
		try {
			memory.canLoad(operand);
				try {
					try {
						stack.push(memory.load(operand));
					} catch (StackException se) {
						// TODO Auto-generated catch block
						throw new InstructionsException(se);
					}
				} catch (MemoryException me) {
					// TODO Auto-generated catch block
					throw new InstructionsException(me);
				}
		} catch (MemoryException me) {
			// TODO Auto-generated catch block
			throw new InstructionsException(me);
		}

	}

}
