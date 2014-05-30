package tp.pr5.mv;

import java.util.ArrayList;
import java.util.Scanner;

import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.InstructionParser;
import tp.pr5.mv.Model.ProgramMV;
import tp.pr5.mv.Model.exceptions.ParseException;

/**
 * Clase auxiliar al Main para cargar el programa y limpiar el fichero
 * @author George y Alberto
 *
 */
public class ProgramLoader {
	private static String END_TOKEN = "END";
	private static String MSG_PROMPT = ">";
	private ProgramMV progmv;
	private Instruction[] listaPrograma;
	private ArrayList<Instruction> lista = new ArrayList<Instruction>();
	
	public ProgramLoader(){
		
	}
	
	/**
	 * metodo que te devuelve la lista de instrucciones
	 * @return listaPrograma la lista del programa
	 */
	public Instruction[] getListaInstruction(){
		this.listaPrograma = lista.toArray(new Instruction[lista.size()]);
		return listaPrograma;
		
	}
	
	/**
	 * metodo de tipo programMV que lee el programa de teclado o de fichero
	 * @param input el tipo de entrada
	 * @param window si es modo window
	 * @return progmv objeto tipo programMV
	 */
	
	public ProgramMV readProgram(Scanner input, boolean window){	
		
		Instruction instrucion;
		String linea= END_TOKEN;
		
		if(window){
			System.out.close();
		}
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
		progmv = new ProgramMV(lista.toArray(new Instruction[lista.size()]));
		return progmv;		

	}
	
	/**
	 * metodo encargado de limpiar el fichero de los comentarios devolviendo un stringbuffer con las 
	 * instruciones del programa
	 * @param entrada
	 * @return contenido 
	 */
	StringBuffer leerAsm(Scanner entrada){
		StringBuffer contenido = new StringBuffer();
		while(entrada.hasNextLine()){	
			String linea =  entrada.nextLine();
			if(!linea.startsWith(";") && !linea.contains(";") && !linea.equals("")){
				contenido.append(linea);
				contenido.append("\n");
			}else if(linea.contains(";") && !linea.startsWith(";")){
				String linea2="";
				char c; 
				for (int n=0; n <linea.length(); n++) { 
					c = linea.charAt (n); 
					if(c != ';'){
						linea2 = linea2 + c;
					}else{
						n= linea.length();
					}
				}
				contenido.append(linea2);
				contenido.append("\n");
			}
		}
		contenido.append("end");
		return contenido;
	}
}
