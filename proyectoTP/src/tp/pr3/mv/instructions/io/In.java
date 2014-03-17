package tp.pr3.mv.instructions.io;

/**
 * Clase encargada de convertir el valor alfanumérico en codigo ASCII obtenido de un fichero o de la entrada de teclado y apilarlo en la cima de la pila.
 * 
 * @author George y Alberto
 */

import java.util.Scanner;
import tp.pr3.mv.ControlUnit;
import tp.pr3.mv.Instruction;
import tp.pr3.mv.Memory;
import tp.pr3.mv.OperandStack;
import tp.pr3.mv.exceptions.instructions.InstructionsException;
import tp.pr3.mv.exceptions.stack.StackException;
import tp.pr3.mv.instructions.NoParamNoOperandInstruction;

public class In extends NoParamNoOperandInstruction implements Instruction{
	
	private static Scanner entrada;
	private int valor;
	
	/**
	 * Constructora donde se indica la instrucción In.
	 * 
	 */
	public In() {
		super("IN");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción In.
	 * 
	 * @return Devuelve el nuevo objeto In.
	 */
	protected Instruction createInstruction(){
		return new In();
	}
	
	/**
	 * Realiza la operación in que apila la representación alfanumérica de un caracter obtenido de fichero o entrada de teclado en código ASCII.
	 * 
	 * @param stack -es la pila.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected boolean operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			char c =' ';
			entrada.useDelimiter("");		   
			try{  
				c = entrada.next().charAt(0);
				valor = (char) c;
			}catch(Exception e){
				valor = 0;
			}
			return stack.push(valor);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
	
	/**
	 * Método encargado de inicializar el valor del escaner de entrada ya sea un fichero o entrada de teclado.
	 * @param in
	 */
	public static void setEntrada(Scanner in){
		entrada = in;
	}
}
