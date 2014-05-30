package tp.pr5.mv.Model.instructions.jump;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.cp.CpException;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.instructions.JumpException;
import tp.pr5.mv.Model.instructions.OneParamNoOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de salto incondicional sumandole al cp el param.
 * 
 * @author George y Alberto
 */


public class RJump extends OneParamNoOperandInstruction implements Instruction{
	/**
	 * Constructora por defecto.
	 */
	public RJump() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción RJump.
	 * 
	 * @param param - parámetro de la instrucción.
	 */
	public RJump(int param){
		super("RJUMP", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción RJump.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto RJump.
	 */
	protected Instruction createInstruction(int param){
		return new RJump(param);
	}
	
	/**
	 * Realiza la operación de salto incondicional e indica la posición del contador del programa.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control)throws InstructionsException{
		if(param !=0){
			try {
				control.increaseCP(param);
			} catch (CpException cpe) {
				// TODO Auto-generated catch block
				throw new JumpException(cpe);
			}
		}
		else{
			throw new JumpException("EL valor de param es: "+param);
		}
	}
}



