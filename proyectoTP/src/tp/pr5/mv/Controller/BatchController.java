package tp.pr5.mv.Controller;
 
import tp.pr5.mv.Model.CPU;

/**
 * Clase encargada de organizar el controlador para el modo batch
 * @author George y Alberto
 *
 */

public class BatchController extends Controller{
	
	/**
	 * Constructora encargada de inicilzar la cpu en la clase padre 
	 * @param model
	 */
	public BatchController(CPU model) {
		super(model);
	}
	
	/**
	 * metodo encargado de arrancar el metodo step para ejecutar las instrucciones
	 */
	@Override
	public void start() {
			while(!cpu.isHalted()){
				cpu.step();
			}
	}
}
