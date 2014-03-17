package tp.pr4.mv.instructions.jump;

/**
 * Clase encargada de realizar las operaciones de salto indirecto.
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
import tp.pr4.mv.instructions.NoParamOneOperandInstruction;

public class JumpIND extends NoParamOneOperandInstruction implements Instruction{


	/**
	 * Constructora con parámetro donde se indica la instrucción JumpIND.
	 * 
	 * @param param - parámetro de la instrucción.
	 */
	public JumpIND(){
		super("JUMPIND");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción JumpIND.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto JumpIND.
	 */
	protected Instruction createInstruction(){
		return new JumpIND();
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
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		try {
			return control.setCP(operand);
		} catch (CpException cpe) {
			// TODO Auto-generated catch block
			throw new JumpException(cpe);
		}
	}
}



