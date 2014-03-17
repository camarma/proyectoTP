package tp.pr2.mv.instructions;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que no tienen parámetro y toman un operando de la pila.
 * 
 * @author George y Alberto
 */

import tp.pr2.mv.ControlUnit;
import tp.pr2.mv.Instruction;
import tp.pr2.mv.Memory; 
import tp.pr2.mv.OperandStack;

public abstract class NoParamOneOperandInstruction implements Instruction{
	//Clases que heredan de esta clase: Neg, Not, Out y Pop.
	
	protected String type;	//El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	 * 
	 * @param type - el tipo de la instrucción.
	 */
	protected NoParamOneOperandInstruction(String type){
		this.type = type;
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
		if (parts.length != 1){
			return null;
		}
		if(parts[0].toUpperCase().equals(type)){
			Instruction ins = createInstruction();
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
	 */
	public boolean execute(OperandStack stack, Memory memory, ControlUnit control){
		int cima;
		boolean exito = false;
		if(!stack.isEmpty()){
			cima = stack.top();
			exito = operate(stack, memory, control, cima);
			if(!exito){
				stack.push(cima);
				return false;
			}
			else{
				return true;
			}
		}	
		return false;
	}
	
	/**
	 * Devuelve una representación textual de la instrucción.
	 */
	public String toString(){
		String cadena="";
		cadena = type;
		return cadena;
	}
	
	/**
	 * Realiza la operación correspondiente. Este método es llamado por el método execute() una vez procesado un operando.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 */
	protected abstract boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand);
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente. Este método es llamado por el método parse() una vez preprocesada la cadena de entrada.
	 * 
	 * @return Devuelve el nuevo objeto del tipo de la instrucción.
	 */
	protected abstract Instruction createInstruction();
}
