package tp.pr5.mv.Controller;
 
import tp.pr5.mv.Model.CPU;

/**
 * Clase encargada de organizar el controlador para el modo swing
 * @author George y Alberto
 *
 */

public class SwingController extends Controller {

	/**
	 * Constructora encargada de inicilzar la cpu en la clase padre 
	 * @param model
	 */
	public SwingController(CPU model) {
		super(model);
	}
	
	/**
	 * Método encargado de ejecutar un hilo de ejecución cuando se pulsa el boton run.
	 */
	public void performRun(){
		cpu.resume();
		new RunThread().start();
	}
	
	/**
	 * Método encargado de detener el hilo de ejecución cuando se pulsa el boton pause.
	 */
	public void performPause(){
		cpu.pause();
	}
	
	/**
	 * Clase encargada de generar un hilo para la ejecución de las instrucciones mediante el método step
	 * @author George y Alberto
	 *
	 */
	class RunThread extends Thread {
		
		public void run(){
			while (!cpu.isHalted() && !cpu.isPaused() && !cpu.error()){
				try {
					cpu.step();
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}


