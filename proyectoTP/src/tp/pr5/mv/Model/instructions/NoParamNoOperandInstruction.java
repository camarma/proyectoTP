package tp.pr5.mv.Model.instructions;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;

/**
 * Clase abstracta proporcionando el código común a las instrucciones que no tienen parámetro y no toman ningún operando de la pila.
 * 
 * @author George y Alberto
 */



public abstract class NoParamNoOperandInstruction implements Instruction{
	//Clases que heredan de esta clase: Halt.
	
	protected String type; //El tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	
	/**
	 * Constructora donde se indica el tipo de la instrucción utilizado al parsear o mostrar la instrucción al usuario.
	 * 
	 * @param type - el tipo de la instrucción.
	 */
	protected NoParamNoOperandInstruction(String type){
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
	public void execute(OperandStack stack, Memory memory, ControlUnit control){
		try {
			operate(stack, memory, control);
		} catch (InstructionsException e) {
			// TODO Auto-generated catch block
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
	 * Realiza la operación correspondiente. Este método es llamado por el método execute() directamente.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected abstract void operate(OperandStack stack, Memory memory, ControlUnit control) throws InstructionsException;
	
	/**
	 * Crea un objeto nuevo de la instrucción correspondiente. Este método es llamado por el método parse() una vez preprocesada la cadena de entrada.
	 * 
	 * @return Devuelve el nuevo objeto del tipo de la instrucción.
	 */
	protected abstract Instruction createInstruction();
}
