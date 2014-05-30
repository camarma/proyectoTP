package tp.pr5.mv.Model.instructions.jump;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.cp.CpException;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.instructions.JumpException;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de salto indirecto.
 * 
 * @author George y Alberto
 */



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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		try {
			control.setCP(operand);
		} catch (CpException cpe) {
			// TODO Auto-generated catch block
			throw new JumpException(cpe);
		}
	}
}



