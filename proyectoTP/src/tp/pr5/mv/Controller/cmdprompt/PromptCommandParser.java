package tp.pr5.mv.Controller.cmdprompt;
import tp.pr5.mv.Controller.cmdprompt.commands.Pop;
import tp.pr5.mv.Controller.cmdprompt.commands.Push;
import tp.pr5.mv.Controller.cmdprompt.commands.Run;
import tp.pr5.mv.Controller.cmdprompt.commands.Step;
import tp.pr5.mv.Controller.cmdprompt.commands.Steps;
import tp.pr5.mv.Controller.cmdprompt.commands.Write;
import tp.pr5.mv.Model.exceptions.ParseException;
/**
 * Clase encargada de analizar la entrada del usuario y generar el comando correspondiente.
 * Guarda internamente un array con todos los comandos disponibles.
 * @author George y Alberto
 *
 */
 
public class PromptCommandParser {
	
	static PromptCommand [] cmds = {new Step(), new Steps(), new Run(), new  Write(), new Pop(), new Push()};
	
	/**
	 * Constructora por defecto.
	 */
	public PromptCommandParser(){
				
	}
	
	/**
	 * Analiza la orden introducida por el usuario y genera el comando correspondiente.
	 * @param line -es la orden introducida por el usuario.
	 * @return
	 * Devuelve el comando correspondiente o null en caso de error al analizar la orden en el parámetro line.
	 */
	public static PromptCommand parseCommand(String line) throws ParseException{
		PromptCommand comand= null;
		int i;
		for (i=0; i < cmds.length; i++){
			comand = cmds[i].parse(line.trim());
			if (comand!=null){
				return comand;
			}
		}
		throw new ParseException("Error: Comando no reconocido");
	}	
}
