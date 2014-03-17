package tp.pr3.mv.instructions;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que no tienen parámetro y toman dos operandos de la pila.
 * 
 * @author George y Alberto
 */

import tp.pr3.mv.ControlUnit;
import tp.pr3.mv.Instruction;
import tp.pr3.mv.Memory;
import tp.pr3.mv.OperandStack;
import tp.pr3.mv.exceptions.instructions.ArithmeticException;
import tp.pr3.mv.exceptions.instructions.InstructionsException;
import tp.pr3.mv.exceptions.stack.StackException;

public abstract class NoParamTwoOperandInstruction implements Instruction{
	//Clases que heredan de esta clase: Add, And, Div, EQ, GT, LE, LT, Mult, Or y Sub.
	
	protected String type;	//El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	 * 
	 * @param type - el tipo de la instrucción.
	 */
	protected NoParamTwoOperandInstruction(String type){
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
	 * @throws StackException 
	 * @throws ArithmeticException 
	 */
	public boolean execute(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException{
		
		int cima = 0, subcima = 0;
			
		try {
			cima = stack.top();
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se+" Esta totalmente vacia");
		}
		stack.pop();
		try {
			subcima = stack.top();
		} catch (StackException se) {
			// TODO Auto-generated catch block
			try {
				stack.push(cima);
				throw new InstructionsException(se+ " Contiene: ",cima);
			} catch (StackException e) {
				// TODO Auto-generated catch block
				throw new InstructionsException(e);
			}
		}
		stack.pop();
		try {
			operate(stack, memory, control, cima, subcima);
			return true;
		} catch (InstructionsException e) {
			try {
				stack.push(subcima);
				stack.push(cima);
			} catch (StackException e1) {
				// TODO Auto-generated catch block
				throw new InstructionsException(e);
			}
			
			throw new InstructionsException(e);
		}
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
	 * Realiza la operación correspondiente. Este método es llamado por el método execute() una vez procesados dos operandos.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand1 - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * @param operand2 - es el segundo operando (subcima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws ArithmeticException 
	 */
	protected abstract boolean operate(OperandStack stack, Memory memory, ControlUnit control, int operand1, int operand2) throws InstructionsException;
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente. Este método es llamado por el método parse() una vez preprocesada la cadena de entrada.
	 * 
	 * @return Devuelve el nuevo objeto del tipo de la instrucción.
	 */
	protected abstract Instruction	createInstruction();
}
