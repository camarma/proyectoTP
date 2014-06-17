package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;
 
/**
 * Comando encargado de almacenar un dato en la memoria en la posicion indicada.
 * @author George y Alberto
 *
 */ 
public class Write extends PromptCommand {
	private int posi, value;
	
	/**
	 * Constructora por defecto.
	 */
	public Write(){
		this(0,0);
	}
	
	/**
	 * Constructora pora inicializar la posicion y el valor.
	 */
	public Write(int pos, int valor){
		this.posi = pos;
		this.value = valor;
	}
	
	/**
	 * Introduce un elemento en la posición de memoria indicada en tiempo de ejecución.
	 * @return 
	 * si la ejecucion fue correcta o incorrecta.
	 */
	public boolean executeCommand() {
		Instruction insCmd = new tp.pr5.mv.Model.instructions.memory.Write(posi, value);
		return cpu.executeInstruction(insCmd);
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Write en caso de que la cadena recibida como parámetro sea "WRITE" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * Devuelve el objeto Write o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena) {
		String parts[] = cadena.split(" +");
		if(parts[0].toUpperCase().equals("WRITE") && parts.length == 3){
			return new Write(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
		}
		else {
			return null;
		}
	}	
}
