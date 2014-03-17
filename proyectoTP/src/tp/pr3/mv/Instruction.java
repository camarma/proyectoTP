package tp.pr3.mv;

import tp.pr3.mv.exceptions.MVException;

/**
 * Interfaz encargada de definir la funcionalidad de una instrucción.
 * Los métodos definidos permiten obtener un nuevo objeto instrucción si la cadena es correcta y ejecutar la instrucción.
 * @author George y Alberto
 *
 */

public interface Instruction {
	
	/**
	 * Analiza el texto de entrada y si coincide con la instrucción devuelve un nuevo objeto instrucción de ese mismo tipo.
	 * @param line -texto representando la instrucción.
	 * @return
	 * Devuelve null si el texto no representa a la instrucción.
	 */
	public Instruction parse (String line);
	
	/**
	 * Ejecuta la instrucción sobre la CPU. Este método debe dejar la CPU en el mismo estado si se produce un error.
	 * @param stack -es la pila.
	 * @param memory -es la memoria.
	 * @param control -es la unidad de control.
	 * @return
	 * si la ejecución fue correcta.
	 * @throws ArithmeticException 
	 */
	public boolean execute (OperandStack stack, Memory memory, ControlUnit control) throws MVException;
}
