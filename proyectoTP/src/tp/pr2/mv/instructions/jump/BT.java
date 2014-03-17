package tp.pr2.mv.instructions.jump;

/**
 * Clase encargada de realizar las operaciones de salto condicional true.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.OneParamOneOperandInstruction;

public class BT extends OneParamOneOperandInstruction implements Instruction{

	/**
	 * Constructora por defecto.
	 */
	public BT() {
		this(0);
	}
	
	/**
	 * Constructora con parámetro donde se indica la instrucción BT.
	 * 
	 * @param param - parámetro de la instrucción.
	 */
	public BT (int param){
		super("BT", param);
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción BT.
	 * 
	 * @param param - parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto BT.
	 */
	protected Instruction createInstruction(int param){
		return new BT(param);
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
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control,  int operand){
		if(operand != 0){
			return control.setCP(param);
		}
		else{
			return true;
		}
	}
}
