package tp.pr5.mv.Controller;

import java.util.Scanner;

import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Controller.cmdprompt.Prompt;

/**
 * Clase encargada de organizar el controlador para el modo interactivo
 * @author George y Alberto
 *
 */

public class InteractiveController extends Controller{
	private Scanner input;
	Prompt promt;
	
	/**
	 * Constructora encargada de inicilzar la cpu en la clase padre y la entrada de teclado
	 * @param model
	 */
	public InteractiveController(CPU model, Scanner sc) {
		super(model);
		promt = new Prompt();
		this.input = sc;
	}

	/**
	 * metodo encargado llamar al metodo runprompt
	 */
	@Override
	public void start() {
		
		super.start();		
		promt.runPrompt(input, this.cpu);
	}

}