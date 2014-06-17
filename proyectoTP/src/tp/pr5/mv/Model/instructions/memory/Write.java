package tp.pr5.mv.Model.instructions.memory;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.memory.MemoryException;
import tp.pr5.mv.Model.instructions.TwoParamNoOperandInstruction;



public class Write extends TwoParamNoOperandInstruction implements Instruction{
	/**
	 * Constructora por defecto.
	 */
	public Write() {
		this(0,0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción Write.
	 * 
	 * @param posi- posición de la memoria.
	 * @param value- valor a almacenar en dicha posición.
	 */
	public Write(int posi, int value) {
		super("WRITE", posi, value);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Write.
	 * 
	 * @param posi- posición de la memoria.
	 * @param value- valor a almacenar en dicha posición.
	 * 
	 * @return Devuelve el nuevo objeto Store.
	 */
	protected Instruction createInstruction(int posi, int value){
		return new Write(posi, value);
	}
	
	/**
	 * Realiza la operación write y guarda la cima de la pila en la posición de memoria indicada.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param posi- posición de la memoria.
	 * @param value- valor a almacenar en dicha posición.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int posi, int value) throws InstructionsException{
			try {
				memory.store(posi, value);
			} catch (MemoryException me) {
				// TODO Auto-generated catch block
				throw new InstructionsException(me);
			}
	}
}
