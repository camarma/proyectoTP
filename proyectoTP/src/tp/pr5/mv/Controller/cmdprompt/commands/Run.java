package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;
/**
 * Comando encargado de ejecutar tantas instrucciones como sea posible hasta que la cpu se detenga.
 * @author George y Alberto
 *
 */

public class Run extends Step{
	
	/**
	 * Constructora por defecto.
	 */
	public Run(){
	
	}
	
	/**
	 * Ejecuta el método heredado doStep() hasta que la cpu se detenga. 
	 * En caso de error al ejecutar una instrucción no se ejecutan más instrucciones.
	 * @return 
	 * si la ejecucion fue correcta o incorrecta.
	 */
	public boolean executeCommand(){
		while(!cpu.isHalted() && !cpu.error())
		{
			if(!doStep()){
				return false;
			}
		}
		return true;	
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Run en caso de que la cadena recibida como parámetro sea "RUN" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * Devuelve el objeto RUN o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena){
		String parts[] = cadena.split(" +");
		if(parts[0].toUpperCase().equals("RUN")){
			return new Run();
		}
		else {
			return null;
		}
	}
}
