package tp.pr5.mv.Model.instructions.arithmetic;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.NoParamTwoOperandInstruction;
import tp.pr5.mv.Model.exceptions.instructions.ArithmeticException;

/**
 * Clase encargada de realizar las operaciones de suma.
 * 
 * @author George y Alberto
 */



public class Add extends NoParamTwoOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción Add.
	 */
	public Add() {
		super("ADD");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Add.
	 * 
	 * @return Devuelve el nuevo objeto Add.
	 */
	protected Instruction createInstruction(){
		return new Add();
	}
	
	/**
	 * Realiza la operación de suma y apila el resultado.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException{
		try {
			stack.push(operand2+operand1);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new ArithmeticException(se);
		}
	}

}
