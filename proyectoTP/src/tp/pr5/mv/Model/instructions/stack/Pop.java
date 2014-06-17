package tp.pr5.mv.Model.instructions.stack;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones pop.
 * 
 * @author George y Alberto
 */



public class Pop extends NoParamOneOperandInstruction implements Instruction{
	
	/**
	 * Constructora donde se indica la instrucción Pop.
	 */
	public Pop() {
		super("POP");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Pop.
	 * 
	 * @return Devuelve el nuevo objeto Pop.
	 */
	protected Instruction createInstruction(){
		return new Pop();
	}
	
	/**
	 * Realiza la operación pop para desapilar de la pila.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el operando extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws  
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand) {
		stack.pop();
	}
}
