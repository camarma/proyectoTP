package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;
  
/**
 * Comando encargado de desapilar.
 * @author George y Alberto
 *
 */ 
public class Pop extends PromptCommand{
	
	/**
	 * Constructora por defecto.
	 */
	public Pop(){
		
	}
	
	/**
	 * Desapila la cima de la pila en tiempo de ejecución.
	 * @return 
	 * si la ejecucion fue correcta o incorrecta.
	 */
	public boolean executeCommand(){
		Instruction insCmd = new tp.pr5.mv.Model.instructions.stack.Pop();
		return cpu.executeInstruction(insCmd);
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Pop en caso de que la cadena recibida como parámetro sea "POP" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * Devuelve el objeto POP o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena){
		String [] parts = cadena.split(" +");
		if(parts[0].toUpperCase().equals("POP")){
			return new Pop();
		}
		else {
			return null;
		}
	}
}

