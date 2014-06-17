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
 * Clase encargada de realizar las operaciones de salto incondicional.
 * 
 * @author George y Alberto
 */



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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			control.setCP(param);
		} catch (CpException cpe) {
			// TODO Auto-generated catch block
			throw new JumpException(cpe);
		}
	}
}


