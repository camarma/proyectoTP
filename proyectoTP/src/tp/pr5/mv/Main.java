package tp.pr5.mv;

import java.io.File;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import tp.pr5.mv.Controller.BatchController;
import tp.pr5.mv.Controller.InteractiveController;
import tp.pr5.mv.Controller.SwingController;
import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Model.ProgramMV;
import tp.pr5.mv.Model.IO.FileInMethod;
import tp.pr5.mv.Model.IO.InMethod;
import tp.pr5.mv.Model.IO.NullInMethod;
import tp.pr5.mv.Model.IO.StdInMethod;
import tp.pr5.mv.Model.IO.SwingInMethod;
import tp.pr5.mv.Model.instructions.io.In;
import tp.pr5.mv.Model.instructions.io.Out;
import tp.pr5.mv.Model.IO.FileOutMethod;
import tp.pr5.mv.Model.IO.NullOutMethod;
import tp.pr5.mv.Model.IO.OutMethod;
import tp.pr5.mv.Model.IO.StdOutMethod;
import tp.pr5.mv.Model.IO.SwingOutMethod;
import tp.pr5.mv.View.console.Console;
import tp.pr5.mv.View.gui.MainWindow;
 
/**
 * Clase principal que únicamente incluye el método main.
 * @author George y Alberto
 *
 */

public class Main {
	private static String MSG_INTRO = "Introduce las instrucciones:";
		
	/**
	 * Método principal de la aplicación.
	 * Lee los argumentos que le pasamos y dependiendo de ello la ejecucion se 
	 * realiza de una forma u otra
	 * @param args.
	 */
	public static void main(String[] args) {
		
		// Objetos
		CPU cpu = new CPU();
		SwingController s_cntr = new SwingController(cpu);
		ProgramMV programa = new ProgramMV();
		ProgramLoader progLoad = new ProgramLoader();
		Options options = opciones();	
		InteractiveController i_ctrl = null;
		BatchController b_ctrl = null;
		Console console = null; 
		// Booleanas
		boolean batch = true;
		boolean window = false;
		// Strings para leer los nombres de los ficheros
		String asmRoute = null;
		String iofile = null;
		// Entrada y Salida
		 OutMethod outprog = null;
		 InMethod inprog = null;
		 SwingOutMethod s_out = null;
		 SwingInMethod s_in = null; 
		// Scanners 
		Scanner inasm = null;
		// Defino los argumentos del programa		
		CommandLineParser parser  = null;  
	    CommandLine cmdLine = null;

		try {
	
		parser  = new BasicParser();  
		cmdLine = parser.parse(options, args);
		
				// Si esta la opcion de ayuda, la imprimimos y salimos.
				if(cmdLine.hasOption("h")){
					if(args.length < 2) {
						new HelpFormatter().printHelp("tp.pr5.mv.Main", options, true);  
						System.exit(0);
					}else
						System.err.println("-h no tiene argumentos");
		        }
				
		        // Eligir el modo que se quiere utilizar
		        if(cmdLine.hasOption("m")){
		        	if(cmdLine.getOptionValue("m").equals("interactivo")){
		        		batch = false;
		        		inasm = new Scanner(System.in);
		        		if(!cmdLine.hasOption("a")) 
		        				System.out.println(MSG_INTRO);
		        	}else if(cmdLine.getOptionValue("m").equals("batch")){ 
			        	batch = true;
		        	}else if(cmdLine.getOptionValue("m").equals("window")){
		        		batch = false;
		        		window = true;
		        	}else{
						System.err.println("Uso incorrecto: Modo incorrecto (parametro -m|--mode)");
						System.err.println("Use -h/--help para mas informacion.");
						System.exit(2);
			        }
			        	
		        }
					           
		        // Si el usuario ha especificado el puerto lo leemos
				if(cmdLine.hasOption("a") || batch || window){
					asmRoute = cmdLine.getOptionValue("a");
					Scanner fichero = null;
					try{
						fichero = new Scanner(new File(asmRoute));
						inasm = new Scanner(progLoad.leerAsm(fichero).toString());
						
					}catch(Exception io){
						System.err.println("Uso incorrecto: Fichero ASM incorrecto.");
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
					inprog = new FileInMethod(iofile);
				}else if(!batch){
		        	inprog = new NullInMethod();
		        }else {
		        	inprog = new StdInMethod();
		        }
				if (window){
			       	s_in = new SwingInMethod(inprog);
			       	In.setEntrada(s_in);
			       //inprog = new SwingInMethod(inprog);
				}else{
					In.setEntrada(inprog);
				}
		        // Si el usuario quiere guardar la salida del programa en la maquina
				if(cmdLine.hasOption("o")){
					iofile = cmdLine.getOptionValue("o");
					outprog = new FileOutMethod(iofile);
		        }else if(batch){
		        	outprog = new StdOutMethod();
				}else{
		        	outprog = new NullOutMethod();
		        }
				
				if (window){
		        	s_out = new SwingOutMethod(outprog);
		        	Out.setSalida(s_out);
				}else{
					Out.setSalida(outprog);
				}
	        	
				
	        	programa = progLoad.readProgram(inasm, window);
	        	cpu.loadProgram(programa); 
	        	
	        	if(window){
	        		ModoSwing(cpu, s_out, s_cntr);
	        	}else if(batch){
	        		ModoBatch(cpu, b_ctrl);
				}else{
					ModoInteractivo(cpu, i_ctrl, console);
				}	        
		           
		}catch (Exception e){
			System.err.println("Uso incorrecto ");
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
	 * Metodo encargado de ejecutar en el modo batch
	 * @param cpu la cpu que ha cargado el programa
	 * @param b_ctrl el tipo de controlador
	 */
	private static void ModoBatch(CPU cpu, BatchController b_ctrl){
		
		b_ctrl = new BatchController(cpu);
		b_ctrl.start();
	}
	
	/**
	 * Metodo encargado de ejecutar en el modo interactivo
	 * @param cpu la cpu que ha cargado el programa
	 * @param i_ctrl el tipo de controlador
	 * @param console para inicializar la vista de la consola
	 */
	private static void ModoInteractivo(CPU cpu, InteractiveController i_ctrl, Console console){
		
		i_ctrl = new InteractiveController(cpu, new Scanner(System.in));
		console = new tp.pr5.mv.View.console.Console(i_ctrl);
		i_ctrl.start();
	}

	/**
	 * Metodo encargado de ejecutar en el modo window
	 * @param cpu la cpu que ha cargado el programa
	 * @param in el tipo de entrada
	 * @param s_cntr el tipo de controlador
	 */
	private static void ModoSwing(CPU cpu, SwingOutMethod out, SwingController s_cntr){
		
		//le metemos un poco de look&feel predeterminado
		try {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		//le metemos nimbus que es el predeterminado
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

		} catch (Exception e) {
		// si nimbus no existe, le metemos el predeterminado del sistema operativo
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				//si no existe, no hay más que hacer
				System.exit(0);
			}
		}
		MainWindow ventana = new MainWindow(s_cntr);
		ventana.initOUT(out);
		s_cntr.start();		
	}

}