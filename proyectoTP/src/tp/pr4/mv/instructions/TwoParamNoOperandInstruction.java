package tp.pr4.mv.instructions;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que tienen dos parámetros y no toman ningun operando de la pila.
 * 
 * @author George y Alberto
 */	

import tp.pr4.mv.ControlUnit;
import tp.pr4.mv.Instruction;
import tp.pr4.mv.Memory;
import tp.pr4.mv.OperandStack;
import tp.pr4.mv.exceptions.instructions.InstructionsException;

public abstract class TwoParamNoOperandInstruction implements Instruction{
	//Clases que heredan de esta clase: Write.
	
	protected int param1;	//El parámetro 1 de la instrucción.
	protected int param2;	//El parámetro 2 de la instrucción.
	protected String type;  //El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario y el valor del parámetro.
	 * 
	 * @param type - el tipo de la instrucción.
	 * @param param1 - el parámetro 1 de la instrucción(pos).
	 * @param param2 - el parámetro 2 de la instrucción(value).
	 */
	protected TwoParamNoOperandInstruction(String type, int param1, int param2){
		this.type = type;
		this.param1 = param1;			
		this.param2 = param2;
	}
		
	/**
	 * Analiza el texto de entrada y si coincide con la instrucción devuelve un nuevo objeto instrucción de ese mismo tipo. Devuelve null si el texto no representa a la instrucción.
	 * 
	 * @param line - texto representando la instrucción.
	 * 
	 * @return Devolvemos la instrucción correspondiente o null en caso contrario.
	 */
	public Instruction parse(String line){
		line = line.trim();
		String [] parts = line.split(" +");
		if (parts.length != 3){
			return null;
		}
		if(parts[0].toUpperCase().equals(type)){
			Instruction ins = createInstruction(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
			return ins;
		}
		return null;
	}
		
	/**
	 * Ejecuta la instrucción sobre la CPU. Este método debe dejar la CPU en el mismo estado si se produce un error.
	 * 
	 * @param stack -es la pila .
	 * @param memory -es la memoria.
	 * @param controlo - es la unidad de control.
	 * 
	 * @return devuelve si la instruccón fue correcta.
	 * @throws InstructionsException 
	 */
	public boolean execute(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			return operate(stack, memory, control, param1, param2);
		} catch (InstructionsException ie) {
			// TODO Auto-generated catch block
			throw new InstructionsException(ie);
		}
	}

	/**
	 * Devuelve una representación textual de la instrucción.
	 */
	public String toString (){
		String cadena="";
		cadena = type+" "+param1+" "+param2;
		return cadena;
	}
		
	/**
	 * Realiza la operación correspondiente. Este método es llamado por el método execute() una vez procesado un operando.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param param1 - parametro que indica la posicion d ela memoria donde se insertara el dato.
	 * @param param2 - parametro con el valor a insertar.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected abstract boolean operate(OperandStack stack, Memory memory, ControlUnit control, int param1, int param2) throws InstructionsException;
		
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente. Este método es llamado por el método parse() una vez preprocesada la cadena de entrada.
	 * 
	 * @param param1 - es el parámetro 1 de la instrucción(pos).
	 * @param param2 - es el parámetro 2 de la instrucción(value).
	 * 
	 * @return Devuelve el nuevo objeto del tipo de la instrucción.
	 */
	protected abstract Instruction createInstruction(int param1, int param2);
}
