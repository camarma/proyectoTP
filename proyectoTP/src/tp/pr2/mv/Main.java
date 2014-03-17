package tp.pr2.mv;
import java.util.Scanner;
import tp.pr2.mv.cmdprompt.Prompt;
/**
 * Clase principal que únicamente incluye el método main.
 * @author George y Alberto
 *
 */

public class Main {

	/**
	 * Método principal de la aplicación.
	 * Ejecuta el bucle principal encargado de leer las instrucciones y ejecutarlas.
	 * @param args.
	 */
	public static void main(String[] args) {
		Prompt promp = new Prompt();
		Scanner input = new Scanner(System.in);
		ProgramMV programa = new ProgramMV();
		CPU cpu = new CPU();
		programa.readProgram(input);
		cpu.loadProgram(programa);
		promp.runPrompt(input, cpu);
		input.close();
	}
}