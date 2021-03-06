package tp.pr3.mv.instructions.io;

/**
 * Clase encargada de convertir el valor numérico de la cima en su representacion ASCII.
 * 
 * @author George y Alberto
 */

import java.io.PrintStream;

import tp.pr3.mv.ControlUnit;
import tp.pr3.mv.Instruction;
import tp.pr3.mv.Memory;
import tp.pr3.mv.OperandStack;
import tp.pr3.mv.exceptions.instructions.IOException;
import tp.pr3.mv.exceptions.instructions.InstructionsException;
import tp.pr3.mv.instructions.NoParamOneOperandInstruction;

public class Out extends NoParamOneOperandInstruction implements Instruction{
	private static PrintStream salida;

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
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		if(operand != 0){
			salida.print((char)operand);
			stack.pop();
			return true;
		}
		else{
			throw new IOException("El valor 0 no es convertible a ASCII");
		}
	}
	
	/**
	 * Método encargado de inicializar el valor del PrintStream de salida.
	 * @param sal
	 */
	public static void setSalida(PrintStream sal){
		salida = sal;
	}
}
