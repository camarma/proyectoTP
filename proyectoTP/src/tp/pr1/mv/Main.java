package tp.pr1.mv;
import java.util.Scanner;
/**
 * Clase principal que únicamente incluye el método main.
 * @author George y Alberto
 *
 */

public class Main {
	
	/**
	 * Método principal de la aplicación.
	 * Ejecuta el bucle principal encargado de leer la instrucción de consola, analizarla y ejecutarla. 
	 * @param args.
	 */
	public static void main(String[] args) {
		String line;
		InstructionParser parser = new InstructionParser();
		Scanner sc = new Scanner(System.in);
		boolean exito = false;
			
		CPU cpu = new CPU();
		do{		
			System.out.print("Instrucción a ejecutar: ");
			line = sc.nextLine();				
			Instruction instruccion = parser.parse(line);
			
			if(instruccion==null){
				System.out.println("Error: instrucción incorrecta");
			}
			else{
				if(instruccion.getParam()==-1)
					System.out.println("Comienza la ejecución de "+ instruccion.getType().toString());
				else
					System.out.println("Comienza la ejecución de "+ instruccion.getType().toString()+ " "+ instruccion.getParam());
				exito=cpu.execute(parser.parse(line));
				if(exito){
					System.out.println("El estado de la máquina tras ejecutar la instrucción es: ");
					System.out.println(cpu.toString());
				}	
				else{
					System.out.println("Error en la ejecución de la instrucción ");
				}
			}
		}while(!cpu.isHalted());
		sc.close();
	}
}


