package tp.pr2.mv.cmdprompt;
import java.util.Scanner;


import tp.pr2.mv.CPU;
/**
 * Clase encargada de ejecutar el prompt que controla la ejecución del programa.
 * @author George y Alberto
 *
 */

public class Prompt {
	private static String END_TOKEN = "QUIT"; 
	private static String MSG_ERROR = "Error: Comando no reconocido";
	private static String MSG_PROMPT = "> ";
	
	/**
	 * Constructora por defecto.
	 */
	public Prompt(){
		
	}
	
	/**
	 * Método encargado de ejecutar el prompt para leer los comandos del usuario. Muestra al usuario MSG_PROMPT y los mensajes de información o error. 
	 * Termina cuando el usuario introduce el valor definido por la constante END_TOKEN.
	 * @param input -de entrada desde donde leer los comandos del usuario.
	 * @param cpu -sobre la que se ejecutan los comandos.
	 */
	public void runPrompt(Scanner input, CPU cpu)
	{
		
		PromptCommand command;
		String prompt;
		do{
			System.out.print(MSG_PROMPT);
			prompt = input.nextLine();
			if(!prompt.toUpperCase().equals(END_TOKEN)){
				command = PromptCommandParser.parseCommand(prompt);
				if(command != null){
					PromptCommand.configureCommandInterpreter(cpu);
					command.executeCommand();
				}
				else{
					System.out.println(MSG_ERROR);
				}
			}
		}while(!prompt.toUpperCase().equals(END_TOKEN));
	}
}
