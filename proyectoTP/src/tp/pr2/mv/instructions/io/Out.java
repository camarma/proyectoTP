package tp.pr2.mv.instructions.io;

/**
 * Clase encargada de convertir el valor numérico de la cima en su representacion ASCII.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory;
import tp.pr2.mv.OperandStack;
import tp.pr2.mv.instructions.NoParamOneOperandInstruction;

public class Out extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Out.
	 * 
	 */
	public Out() {
		super("OUT");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Out.
	 * 
	 * @return Devuelve el nuevo objeto Out.
	 */
	protected Instruction createInstruction(){
		return new Out();
	}
	
	/**
	 * Realiza la operación out que muestra la representacion numérica de la cima en codigo ASCII.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand){
		if(operand != 0){
			System.out.println((char) operand);
			stack.pop();
			return true;
		}
		else{
			return false;
		}
	}

}
