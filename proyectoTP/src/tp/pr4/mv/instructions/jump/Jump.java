package tp.pr4.mv.instructions.jump;

/**
 * Clase encargada de realizar las operaciones de salto incondicional.
 * 
 * @author George y Alberto
 */

import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.exceptions.cp.CpException;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
import tp.pr4.mv.exceptions.instructions.JumpException;
import tp.pr4.mv.instructions.OneParamNoOperandInstruction;

public class Jump extends OneParamNoOperandInstruction implements Instruction{
	
	/**
	 * Constructora por defecto.
	 */
	public Jump() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción Jump.
	 * 
	 * @param param - parámetro de la instrucción.
	 */
	public Jump(int param){
		super("JUMP", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Jump.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto Jump.
	 */
	protected Instruction createInstruction(int param){
		return new Jump(param);
	}
	
	/**
	 * Realiza la operación de salto incondicional e indica la posición del contador del programa.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			return control.setCP(param);
		} catch (CpException cpe) {
			// TODO Auto-generated catch block
			throw new JumpException(cpe);
		}
	}
}


