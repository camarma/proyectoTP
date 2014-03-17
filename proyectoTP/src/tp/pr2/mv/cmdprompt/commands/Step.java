package tp.pr2.mv.cmdprompt.commands;
import tp.pr2.mv.cmdprompt.PromptCommand;
/**
 * Comando primario encargado de ejecutar la siguiente instrucción. El resto de comandos heredarán de esta clase.
 * @author George y Alberto
 *
 */

public class Step extends PromptCommand {
	private static String MSG_EXEC_BEGIN = "Comienza la ejecucion de  "; 
	private static String MSG_EXEC_END = "El estado de la maquina tras ejecutar la instruccion es:";
	private static String MSG_EXEC_ERROR = "Error en la ejecucion de la instruccion";

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
				System.out.println((MSG_EXEC_BEGIN) + nombreInstruccion);
				if(cpu.step()){
					System.out.println(MSG_EXEC_END);
					System.out.println(cpu.toString());
					return true;
				}
				else{
					System.out.println(MSG_EXEC_ERROR);
					return false;
				}
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
