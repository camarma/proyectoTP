package tp.pr5.mv.Model.instructions.stack;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones dup.
 * 
 * @author George y Alberto
 */



public class Dup extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Dup.
	 */
	public Dup() {
		super("DUP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Dup.
	 * 
	 * @return Devuelve el nuevo objeto Dup.
	 */
	protected Instruction createInstruction(){
		return new Dup();
	}
	
	/**
	 * Realiza la operación dup, duplica el contenido de la cima y lo apila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		try {
			stack.push(operand);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
}

