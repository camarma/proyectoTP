package tp.pr5.mv.Model.instructions.arithmetic;
 
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.exceptions.instructions.InstructionsException;
import tp.pr5.mv.Model.exceptions.stack.StackException;
import tp.pr5.mv.Model.instructions.NoParamOneOperandInstruction;

/**
 * Clase encargada de realizar las operaciones de negación.
 * 
 * @author George y Alberto
 */



public class Neg extends NoParamOneOperandInstruction implements Instruction{

	/**
	 * Constructora donde se indica la instrucción Neg.
	 * 
	 */
	public Neg() {
		super("NEG");
	}
	
	/**
	 * Crea un objeto nuevo de la instrucción Neg.
	 * 
	 * @return Devuelve el nuevo objeto Neg.
	 */
	protected Instruction createInstruction(){
		return new Neg();
	}
	
	/**
	 * Realiza la operación de negación y apila el resultado.
	 * 
	 * @param stack -es la pila.
	 * @param memory - es la memoria.
	 * @param control -es la unidad de control.
	 * @param operand - es el primer operando (cima) extraido de la pila por el método execute() y sobre el que se realiza la operación.
	 * 
	 * @return Devuelve el resultado de ejecutar la operación.
	 * @throws InstructionsException 
	 */
	protected void operate(OperandStack stack, Memory memory, ControlUnit control, int operand) throws InstructionsException{
		operand = operand * -1;
		stack.pop();
		try {
			stack.push(operand);
		} catch (StackException se) {
			// TODO Auto-generated catch block
			throw new InstructionsException(se);
		}
	}
}
