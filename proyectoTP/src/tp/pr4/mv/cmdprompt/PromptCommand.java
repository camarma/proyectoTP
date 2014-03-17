package tp.pr4.mv.cmdprompt;
import tp.pr4.mv.CPU;
import tp.pr4.mv.exceptions.instructions.InstructionsException;
/**
 * Clase abstracta que define los métodos a implementar por los comandos.
 * @author George y Alberto
 *
 */

public abstract class PromptCommand {
	
	/**
	 * Atributo estático donde se guarda la CPU sobre la que se ejecutan los comandos.
	 */
	protected static CPU cpu;
	
	/**
	 * Constructora por defecto.
	 */
	public PromptCommand(){

	}
	
	/**
	 * Establece la cpu sobre la que se ejecutarán todos los comandos.
	 * @param cpu -cpu sobra la que se ejecutaran los comandos.
	 */
	protected static void configureCommandInterpreter(CPU cpu){		
		PromptCommand.cpu = cpu;
	}
	
	/**
	 * Ejecuta el comando.
	 * @return
	 * si la ejecucion fue correcta o incorrecta.
	 * @throws InstructionsException 
	 */
	public abstract boolean executeCommand() throws InstructionsException;
	
	/**
	 * Devuelve el comando correspondiente a la cadena recibida como parámetro.
	 * @param cadena -de texto supuestamente representando un comando.
	 * @return
	 * Devuelve el comando correspondiente o null en caso de no coincidencia. 
	 */
	public abstract PromptCommand parse(String cadena);
}

