package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;
/**
 * Comando primario encargado de ejecutar la siguiente instrucción. El resto de comandos heredarán de esta clase.
 * @author George y Alberto
 *
 */
 
public class Step extends PromptCommand {

	/**
	 * Constructora por defecto.
	 */
	public Step(){
	
	}
	
	/**
	 * Ejecuta el comando delegando en el método doStep() para que pueda ser reutilizado por las clases hijas.
	 * @return
	 * si la ejecución fue correcta o incorrecta.
	 */
	public boolean executeCommand() {
		
		return doStep();
	}
	
	/**
	 * Realiza el step mostrando los mensajes correspondientes. La ejecución no se realiza si la CPU se encuentra detenida.
	 * @return
	 * si la ejecución fue correcta o incorrecta.
	 */
	protected boolean doStep(){
		if(!cpu.isHalted()){
			String nombreInstruccion = cpu.getNextInstructionName();
			if(!nombreInstruccion.equals("")){
				
				cpu.step();
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	/**
	 * Devuelve un nuevo objeto de tipo Step en caso de que la cadena recibida como parámetro sea "STEP" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * el objeto STEP o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena) {
		String parts[] = cadena.split(" +");
		if(parts[0].toUpperCase().equals("STEP") && parts.length == 1){
			return new Step();
		}
		else {
			return null;
		}
	}	
}
