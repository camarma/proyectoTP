package tp.pr5.mv.Model.instructions.jump;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.cp.CpException;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.instructions.JumpException;
import tp.pr5.mv.Model.instructions.OneParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de salto condicional false.
 * 
 * @author George y Alberto
 */


public class BF extends OneParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora por defecto.
	 */
	public BF() {
		this(0);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción BF.
	 * 
	 * @param param- parametro de la instrucción
	 */
	public BF (int param){
		super("BF",param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción BF.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto BF.
	 */
	protected Instruction createInstruction(int param){
		return new BF(param);
	}
	
	/**
	 * Realiza la operación de salto condicional e indica la posición del contador del programa.
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
		if(operand == 0){
			try {
				control.setCP(param);
			} catch (CpException cpe) {
				throw new JumpException(cpe);
			}
		}
		else{
			try {
				control.increaseCP(1);
			} catch (CpException cpe) {
				// TODO Auto-generated catch block
				throw new JumpException(cpe);
			}
		}
	}
}
