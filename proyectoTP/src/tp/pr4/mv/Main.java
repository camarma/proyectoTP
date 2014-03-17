package tp.pr4.mv;
import java.util.Scanner;

import tp.pr4.mv.cmdprompt.Prompt;
import tp.pr4.mv.instructions.io.In;
import tp.pr4.mv.instructions.io.Out;

import java.io.*;

import org.apache.commons.cli.*;
/**
 * Clase principal que únicamente incluye el método main.
 * @author George y Alberto
 *
 */

public class Main {
	
	
	/**
	 * Método principal de la aplicación.
	 * Lee los argumentos que le pasamos y dependiendo de ello la ejecucion se 
	 * realiza de una forma u otra
	 * @param args.
	 */
	public static void main(String[] args) {
		
		// Objetos
		CPU cpu = new CPU();
		Prompt prompt = new Prompt();
		ProgramMV programa = new ProgramMV();
		Options options = opciones();	
		// Booleanas
		boolean batch = true;
		// Strings para leer los nombres de los ficheros
		String asmRoute = null;
		String iofile = null;
		// Printstream para guardar lo que muestra el out
		PrintStream outprog = null;
		// Scanners 
		Scanner inasm = null;
		Scanner inprog = null;
	    Scanner run = new Scanner(System.in);
		// Defino los argumentos del programa		
		CommandLineParser parser  = null;  
	    CommandLine cmdLine = null;
	    
		try {
	
		parser  = new BasicParser();  
		cmdLine = parser.parse(options, args);
		
				// Si esta la opcion de ayuda, la imprimimos y salimos.
				if(cmdLine.hasOption("h")){
					if(args.length < 2) {
						new HelpFormatter().printHelp("tp.pr4.mv.Main", options, true);  
						System.exit(0);
					}else
						System.err.println("-h no tiene argumentos");
		        }
				
		        // Eligir el modo que se quiere utilizar
		        if(cmdLine.hasOption("m")){
		        	if(cmdLine.getOptionValue("m").equals("interactivo")){
		        		batch = false;
		        		inasm = new Scanner(System.in);
		        	}else if(cmdLine.getOptionValue("m").equals("batch")){ 
			        	batch = true;
			        }else{
						System.err.println("Uso incorrecto: Modo incorrecto (parametro -m|--mode)");
						System.err.println("Use -h/--help para mas informacion.");
						System.exit(2);
			        }
			        	
		        }
					           
		        // Si el usuario ha especificado el puerto lo leemos
				if(cmdLine.hasOption("a") || batch){
					asmRoute = cmdLine.getOptionValue("a");
					Scanner fichero = null;
					try{
						fichero = new Scanner(new File(asmRoute));
						inasm = new Scanner(leerAsm(fichero).toString());
						
					}catch(Exception io){
						System.err.println("Uso incorrecto: Fichero ASM no expecificado.");
						System.err.println("Use -h/--help para mas informacion.");
						System.exit(2);
					}finally{
						try{
							if(fichero != null)
								fichero.close();
						}catch(Exception e){
							e.printStackTrace();
						}					
					}
				}

		        // Si el usuario quiere importar un fichero
				if(cmdLine.hasOption("i")){
					iofile = cmdLine.getOptionValue("i");	
					try{
						inprog = new Scanner(new File(iofile));
					}catch(FileNotFoundException e){					
						System.err.println("Error al acceder al fichero de entrada (" +iofile + ")");
						System.err.println("Use -h/--help para mas informacion.");
						System.exit(2);
					}
				}else if(!batch){
		        	inprog = new Scanner(new NullInputStream());
		        }else {
		        	inprog = new Scanner(System.in);
		        }
							
		        In.setEntrada(inprog);
			
		        // Si el usuario quiere guardar la salida del programa en la maquina
				if(cmdLine.hasOption("o")){
					iofile = cmdLine.getOptionValue("o");
					try{
						outprog = new PrintStream(iofile);
					}catch(FileNotFoundException e){
						e.printStackTrace();
					}
		        }else if(batch){
		        	outprog = System.out;
		        }else{
		        	outprog = new PrintStream(new NullOutputStream());
		        }
				
	        	Out.setSalida(outprog);


				programa.readProgram(inasm);
				cpu.loadProgram(programa);
				if(batch == true){
					try{
						while(!cpu.isHalted()){
							cpu.step();				
						}
					}catch(Exception e){
							System.err.println(e.getMessage());
					}
				}else{
					prompt.runPrompt(run, cpu);
				}
		        
		           
		}catch (Exception e){
			System.err.print("Uso incorrecto: ");
			System.err.println(e.getMessage());
			System.err.println("Use -h/--help para mas informacion.");
	    	System.exit(1); 
		}finally{
			try{
				if(inasm != null)
					inasm.close();
			}catch(Exception e){
				e.printStackTrace();
			}					
		}
	}
	
	
	/**
	 * Metodo opciones encargado de guardar la ayuda (help)
	 * @return options
	 */
	private static Options opciones(){
		
		Options options = new Options();
			//ASM
			OptionBuilder.withLongOpt("asm");
			OptionBuilder.withDescription("Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch");
			OptionBuilder.hasArg();
			OptionBuilder.withArgName("asmfile");
			options.addOption(OptionBuilder.create('a'));
			//HELP
			OptionBuilder.withLongOpt("help");
			OptionBuilder.withDescription("Muestra esta ayuda");
			options.addOption(OptionBuilder.create('h'));
			//IN
			OptionBuilder.withLongOpt("in");
			OptionBuilder.withDescription("Entrada del programa de la maquina-p");
			OptionBuilder.hasArg();
			OptionBuilder.withArgName("infile");
			options.addOption(OptionBuilder.create('i'));
			//MODE
			OptionBuilder.withLongOpt("mode");
			OptionBuilder.withDescription("Modo de funcionamiento (batch | interactive). por defecto, batch");
			OptionBuilder.hasArg();
			OptionBuilder.withArgName("mode");
			options.addOption(OptionBuilder.create('m'));
			//OUT
			OptionBuilder.withLongOpt("out");
			OptionBuilder.withDescription("Fichero donde se guarda la salida del programa de la maquina-p");
			OptionBuilder.hasArg();
			OptionBuilder.withArgName("outfile");
			options.addOption(OptionBuilder.create('o'));
		return options;
	}
	
	
	/**
	 * metodo encargado de limpiar el fichero de los comentarios devolviendo un stringbuffer con las 
	 * instruciones del programa
	 * @param entrada
	 * @return contenido 
	 */
	private static StringBuffer leerAsm(Scanner entrada){
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