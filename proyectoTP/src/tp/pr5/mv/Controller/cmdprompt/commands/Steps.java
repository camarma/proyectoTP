package tp.pr5.mv.Controller.cmdprompt.commands;
import tp.pr5.mv.Controller.cmdprompt.PromptCommand;
/**
 * Comando encargado de ejecutar n instrucciones.
 * @author George y Alberto
 *
 */
 
public class Steps extends Step{
	private int n;
	
	/**
	 * Constructora por defecto que le da valor 1.
	 */
	public Steps(){
		this(1);
	}
	
	/**
	 * Constructora donde se establece el número de pasos.
	 * @param nsteps -numero de instruccion para ejecutar.
	 */
	public Steps(int nsteps){
		this.n = nsteps;
	}

	/**
	 * Ejecuta el método heredado doStep() hasta que la cpu se detenga. 
	 * En caso de error al ejecutar una instrucción no se ejecutan más instrucciones.
	 * @return 
	 * si la ejecucion fue correcta o incorrecta.
	 */
	public boolean executeCommand(){

		for(int i=0; i < n; i++)
		{			
			if(!doStep())
			{
				return false;
			}
		}		
		return false;	
	}
	
	/**
	 * Devuelve un nuevo objeto de tipo Steps en caso de que la cadena recibida como parámetro sea "Step N" o null en caso contrario.
	 * @param cadena -de texto supuestamente representando el comando.
	 * @return
	 * Devuelve el objeto STEPS o null en caso de no coincidencia.
	 */
	public PromptCommand parse(String cadena){
	
		String [] parts = cadena.split(" +");
		if(parts[0].toUpperCase().equals("STEP") && parts.length == 2){
			return new Steps(Integer.parseInt(parts[1]));
		}
		else {
			return null;
		}
	}	
}