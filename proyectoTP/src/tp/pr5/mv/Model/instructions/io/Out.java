package tp.pr5.mv.Model.instructions.io;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.IOException;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;
import tp.pr5.mv.Model.IO.OutMethod;

/**
 * Clase encargada de convertir el valor numérico de la cima en su representacion ASCII.
 * 
 * @author George y Alberto
 */




public class Out extends NoParamOneOperandInstruction implements Instruction{
	private static OutMethod salida;

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
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		if(operand != 0){
			stack.pop();
			try {
				salida.write((char)operand);
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new IOException("El valor 0 no es convertible a ASCII");
		}
	}
	
	/**
	 * Método encargado de inicializar el valor del PrintStream de salida.
	 * @param sal
	 */
	public static void setSalida(OutMethod sal){
		salida = sal;
	}
}
