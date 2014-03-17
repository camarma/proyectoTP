package tp.pr2.mv;
/**
 * Clase que representa la CPU de la máquina virtual.
 * @author George y Alberto
 *
 */

public class CPU {
    private static final String SEP = "\n"; // System.getProperty("line.separator");
	private OperandStack stack;
	private Memory memory;
	private ControlUnit control = new ControlUnit();
	private ProgramMV program = new ProgramMV();
	
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
	 */
	public boolean step(){
		
		if(!control.isHalted()){
			if(program != null){
				int cp = control.getCP();
				Instruction ins = program.getInstructionAt(cp);
				if(ins != null){
					if(ins.execute(stack, memory, control)){
						control.next();
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{			
			return false;
		}
	}
	
	/**
	 * Método encargado de devolver una representación textual de la CPU, es decir, el estado de la pila y la memoria.
	 * @return
	 * Devuelve la representacion textual de la pila y la memoria.
	 */
	public String toString(){
		String resultado="Memoria: "+memory.toString()+ SEP +"Pila de operandos: "+stack.toString();
		return resultado;
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
		if(program.lista.size() >cp){
			nextInstruct = program.lista.get(cp).toString();
		}
		return nextInstruct;
	}	
}
