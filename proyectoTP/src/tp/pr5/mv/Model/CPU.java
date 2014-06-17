package tp.pr5.mv.Model;
import tp.pr5.mv.Model.exceptions.MVException;
 
/**
 * Clase que representa la CPU de la máquina virtual.
 * @author George y Alberto
 *
 */

public class CPU extends Observable<CPU.Observer>{
	private OperandStack stack;
	private Memory memory;
	private ControlUnit control = new ControlUnit();
	private ProgramMV program = new ProgramMV();
	private boolean error = false;
	private boolean pause = false;
	
	/**
	 * Constructora por defecto.
	 * Inicializa el tamaño maximo de la pila y de la memoria. 
	 */
	public CPU(){
		this(100,100);
	}
	
	/**
	 * Constructora. Crea los elementos de la CPU. La capacidad máxima de almacenamiento de la memoria y de la pila de operandos se establecerá según los parámetros.
	 * @param memory_size - es el tamaño de la memoria.
	 * @param stack_size - es el tamaño de la pila.
	 */
	public CPU(int memory_size, int stack_size){
		 stack = new OperandStack(stack_size);
		 memory = new Memory(memory_size);	
	}
	
	/**
	 * Carga el programa indicado.
	 * @param program -es el programa que contiene las instrucciones.
	 */
	public void loadProgram(ProgramMV program){
		this.program = program;
	}
	
	/**
	 * Ejecuta la instrucción del programa apuntada por el contador de programa. 
	 * No se ejecutará nada si el programa no está cargado o la máquina está parada En caso de ejecución correcta de la instrucción se avanza el contador de programa.
	 * @return
	 * false si el programa no está cargado, la máquina está parada o hay un error de ejecución de la instrucción. true en otro caso.
	 * y actualiza la lista de observers
	 */
	public void step(){
		error=false;
		int cp = control.getCP();
		Instruction ins = program.getInstructionAt(cp);
		
		if(control.isHalted()){
			for(Observer o: observers){
				o.onError(new MVException("Maquina parada"));
			}
		}
		
		else if(program == null){
			for(Observer o: observers){
				o.onError(new MVException("Programa incorrecto"));
			}
		}
		
		else if(ins == null){
			for(Observer o: observers){
				o.onError(new MVException("Instruccion incorrecta"));
			}
		}
		else{
			try {
				ins.execute(stack, memory, control);
				control.next();
				for(Observer o: observers){
					o.onInstructionEnd(ins, memory.getData(), stack.getData(), control.getData());
				}
			} catch (MVException e) {
				// TODO Auto-generated catch block
				error = true;
				for(Observer o: observers){
					o.onError(e);
				}
			}
		}
	
	}
	
	/**
	 * Método encargado de devolver si la ejecución run esta en pausa.
	 * @return true si esta en pausa, false cc.
	 */
	public boolean isPaused(){
		return pause;
	}
	
	/**
	 * Método encargado de establacer pausa a true.
	 */
	public void pause(){
		 pause = true;
	}
	
	/**
	 * Método encargado de establecer pausa a false.
	 */
	public void resume(){
		pause = false;
	}
	
	/**
	 * Método que indica si la CPU ha sido detenida por una instrucción HALT.
	 * @return
	 * Devuelve true si paramos la CPU.
	 */
	public boolean isHalted(){
		return control.isHalted();
	}
	
	/**
	 * Devuelve la siguiente instrucción a ejecutar para informar al usuario.
	 * @return
	 * Devuelve el nombre de la siguente instrución a ejecutar o null en caso de error.
	 */
	public String getNextInstructionName(){
		
		String nextInstruct = "";
		int cp = control.getCP();
		if(program.listaInstruciones.length >cp){
			nextInstruct = program.listaInstruciones[cp].toString();
		}
		return nextInstruct;
	}	
	
	/**
	 * Método encargado de ejecutar el pop, el push y el pop en linea de comandos y actualiza la lista de observers.
	 * @param insCmd instrucción
	 * @return
	 */
	public boolean executeInstruction(Instruction insCmd){
		
		try {
			insCmd.execute(stack, memory,control);
			return true;
		} catch (MVException e) {
			// TODO Auto-generated catch block
			for(Observer o: observers){
				o.onError(e);
			}
			return false;
		}
	}
	/**
	 * Método encargado de devolover si se ha produciro un error en la ejecución de una instrucción
	 * @return true si hay error false cc.
	 */
	public boolean error(){
		return error;
	}

	/**
	 * Interfaz implementado por los observadores de la clase
	 * para ser notificados cuando ocurre algún evento de alto nivel 
	 * en la CPU. Para eventos relacionados con la memoria, pila y unidad de control
	 * se deben utilizar los observadores especializados.
	 */
	public interface Observer {
		
		
		/**
		 * Se invoca cuando se carga un nuevo programa en la CPU. 
		 * Además indica que el contador de programa vale 0 y la memoria
		 * y la pila están vacías.
		 * 
		 * @param program Lista de las instrucciones del nuevo programa.
		 */
		public void onStart(ProgramMV.Data instructions);
		
		/**
		 * Se invoca justo antes de ejecutar una instrucción. 
		 * Si la ejecución de la instrucción tiene exito, a continuación
		 * se invocará a onInstructionEnd. Si la ejecución falla se
		 * invocará a onError. 
		 * 
		 * @param instr Instrucción que se va a ejecutar.
		 */
		public void onInstructionStart(Instruction instr);
		
		/**
		 * Se invoca justo después de ejecutar una instrucción de
		 * manera correcta. 
		 * 
		 * @param instr Instrucción que se completó.
		 * @param mem Estado final de la memoria 
		 * @param ops Estado final de la pila de operandos .
		 * @param pc Estado final del contador de programa.
		 */
		public void onInstructionEnd(Instruction instr, Memory.Data mem, OperandStack.Data ops, ControlUnit.Data pc);
		
		/**
		 * Se invoca cuando se produce un error al ejecutar una instrucción.
		 * 
		 * @param instr Instrucción que provocó el error.
		 * @param trapMessage Mensaje con la explicación del error.
		 */
		public void onError(Exception error);
	}
	
	
	/**
	 * Método que utilizará el controlador para indicar 
	 * se carga un nuevo programa en la CPU.
	 * Avisa a los observadores enivándoles la lista de instrucciones cargadas
	 */
	
	public void start() {
		for (Observer o: observers){
			o.onStart(program.getData());
		}
	}
	

	//*********************************************************/
	/* Métodos que utilizará el controlador para registrar
	 * la vista como oyente de los elementos internos de la 
	 * cpu: pila, memoria y unidad de control  
	/*********************************************************/

	public void addObserver(ControlUnit.Observer obs) {
		this.control.addObserver(obs);
	}
	
	public void addObserver(OperandStack.Observer obs) {
		this.stack.addObserver(obs);
	}
	
	public void addObserver(Memory.Observer obs) {
		this.memory.addObserver(obs);
	}

}
