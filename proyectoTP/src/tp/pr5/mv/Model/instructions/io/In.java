package tp.pr5.mv.Model.instructions.io;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.IO.InMethod;
import tp.pr5.mv.Model.instructions.NoParamNoOperandInstruction;

/**
 * Clase encargada de convertir el valor alfanumérico en codigo ASCII obtenido de un fichero o de la entrada de teclado y apilarlo en la cima de la pila.
 * 
 * @author George y Alberto
 */



public class In extends NoParamNoOperandInstruction implements Instruction{
	
	private static InMethod entrada;
	
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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		int valor = entrada.read();
		try {
			stack.push(valor);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}
	
	/**
	 * Método encargado de inicializar el valor del escaner de entrada ya sea un fichero o entrada de teclado.
	 * @param in
	 */
	public static void setEntrada(InMethod in){
		 entrada = in;
	}
}
