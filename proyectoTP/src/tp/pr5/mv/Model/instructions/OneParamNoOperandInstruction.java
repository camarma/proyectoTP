package tp.pr5.mv.Model.instructions;

import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que tienen un parámetro y no toman ningún operando de la pila.
 * 
 * @author George y Alberto
 */	



public abstract class OneParamNoOperandInstruction implements Instruction{
	//Clases que heredan de esta clase: Jump, RJump, Load y Push.
	
	protected int param;	//El parámetro de la instrucción.
	protected String type;  //El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario y el valor del parámetro.
	 * 
	 * @param type - el tipo de la instrucción.
	 * @param param - el parámetro de la instrucción.
	 */
	protected OneParamNoOperandInstruction(String type, int param){
		this.type = type;
		this.param = param;
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
		if (parts.length != 2){
			return null;
		}
		if(parts[0].toUpperCase().equals(type)){
			Instruction ins = createInstruction(Integer.parseInt(parts[1]));
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
	 * @throws StackException 
	 */
	public void execute(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		try {
			operate(stack, memory, control);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
	
	/**
	 * Devuelve una representación textual de la instrucción.
	 */
	public String toString (){
		String cadena="";
		cadena = type + " "+param;
		return cadena;
	}
	
	/**
	 * Realiza la operación correspondiente. Este método es llamado por el método execute() directamente.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws StackException 
	 * @throws InstructionsException 
	 */
	protected abstract void operate(OperandStack stack, Memory memory, ControlUnit control) throws StackException, InstructionsException;
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente. Este método es llamado por el método parse() una vez preprocesada la cadena de entrada.
	 * 
	 * @param param - es el parámetro de la instrucción.
	 * 
	 * @return Devuelve el nuevo objeto del tipo de la instrucción.
	 */
	protected abstract Instruction createInstruction(int param);
}
