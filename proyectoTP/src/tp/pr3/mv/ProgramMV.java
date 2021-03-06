package tp.pr3.mv;
import java.util.ArrayList;
import java.util.Scanner;

import tp.pr3.mv.exceptions.ParseException;
/**
 * Clase encargada de leer el programa.
 * @author George y Alberto
 *
 */

public class ProgramMV {
	private static String END_TOKEN = "END"; 
	private static String MSG_INTRO = "Introduce el programa fuente";
	private static String MSG_PROMPT = "> ";
	private static String MSG_SHOW = "El programa introducido es:";
	private static int tamLista;
	public ArrayList<Instruction> lista = new ArrayList<>();
	/**
	 * Constructora por defecto.
	 */
	public ProgramMV(){
		
	}
	
	/**
	 * Método encargado de leer el programa, mostrando al usuario MSG_PROMPT y los mensajes de información o error.
	 * Las instrucciones leidas se analizan con el InstructionParser. 
	 * Termina cuando el usuario introduce el valor definido por la constante END_TOKEN. 
	 * Al terminar de leer se muestra el programa cargado.
	 * @param input -es la entrada desde donde se lee el programa.
	 */
	public void readProgram(Scanner input){	
		int i = 0;
		Instruction instrucion;
		String linea= END_TOKEN;
		
		System.out.println(MSG_INTRO);
		do{
			System.out.print(MSG_PROMPT);
			linea = input.nextLine();	
			if(!linea.toUpperCase().equals(END_TOKEN)){
				try{
					instrucion = InstructionParser.parse(linea);
					if(instrucion != null){
						lista.add(instrucion);
					}
				}catch(ParseException e){
					e.printStackTrace();
				}
			}
		}while(!linea.toUpperCase().equals(END_TOKEN));		
		
		System.out.println(MSG_SHOW);
		
		for (i=0; i<lista.size();i++){
			System.out.println(i + ": " + lista.get(i));
		}
		this.tamLista = lista.size();
	}
	
	/**
	 * Devuelve la instrucción en la posición indicada.
	 * @param i -es la posición de la instrucción.
	 * @return
	 * la instrucción si la posición entra en el rango.
	 */
	public Instruction getInstructionAt(int i){
		if(lista.isEmpty() || i < 0 || lista.size()<=i){
			return null;
		}
		else{
			return lista.get(i);
		}
	}
	
	/**
	 * Método encargado de proporcionar el tamaño de la lista.
	 * @return
	 */
	public int getTamLista(){
		return tamLista;
	}
}
