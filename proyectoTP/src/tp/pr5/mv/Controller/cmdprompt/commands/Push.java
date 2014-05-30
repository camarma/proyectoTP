package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;

/**
 * Comando encargado de almacenar un dato en la pila.
 * @author George y Alberto
 *
 */ 

public class Push extends PromptCommand{
	private int value;
	
	/**
	 * Constructora por defecto.
	 */
	public Push(){
		this(0);
	}
	
	/**
	 * Constructora para inicializar el valor del Push.
	 */
	public Push(int value){
		this.value = value;
	}
	
	/**
	 * Apila un elemento en la pila en tiempo de ejecución.
	 * @return
	 * si la ejecución fue correcta o incorrecta.
	 */
	public boolean executeCommand(){
		Instruction insCmd  = new tp.pr5.mv.Model.instructions.stack.Push(value);
		return cpu.executeInstruction(insCmd);
			
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Push en caso de que la cadena recibida como parámetro sea "PUSH" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * Devuelve el objeto PUSH o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena){
		String [] parts = cadena.split(" +");
		if(parts[0].toUpperCase().equals("PUSH") && parts.length == 2){
			return new Push(Integer.parseInt(parts[1]));
		}
		else {
			return null;
		}
	}
}
