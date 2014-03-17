package tp.pr1.mv;
/**
 * Clase que representa la CPU de la máquina virtual.
 * @author George y Alberto
 *
 */

public class CPU {
    private static final String SEP = "\n"; // System.getProperty("line.separator");
	private OperandStack stack;
	private Memory memory;
	private int cima, subcima;
	private boolean halt;
	private boolean resultado;
	private boolean exito;
	
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
		 this.halt = false;
	}
	
	/**
	 * Método que indica si la CPU ha sido detenida por una instrucción HALT.
	 * @return
	 * Devuelve true si paramos la CPU.
	 */
	public boolean isHalted(){
		return halt;
	}
	
	/**
	 * Método encargado de ejecutar la instrucción recibida por parámetro. En caso de error de ejecución debe dejarse la CPU en el mismo estado.
	 * @param instruction - Instrucción que se va a ajecutar.
	 * @return
	 * Devuelve true si la ejecución fue correcta, false en caso de error o que la CPU esté parada.
	 */
	public boolean execute(Instruction instruction){
		if(instruction != null){
			InstructionType instruccion = instruction.getType();
	
			switch(instruccion){
			case PUSH:	
				exito = stack.push(instruction.getParam());
				if(exito){
					resultado = true;
				}else{
					resultado = false;
				}
				break;
			case POP: 
				if(stack.isEmpty()){
					resultado = false;
				}else{
					stack.pop();
					resultado = true;
				}
				break;
			case ADD: 
				if(!stack.isEmpty()){
					cima = stack.top();
					stack.pop();
					if(!stack.isEmpty()){
						subcima = stack.top();
						stack.pop();
						stack.push(subcima+cima);
						resultado = true;
					}
					else{
						stack.push(cima);
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case MUL: 
				if(!stack.isEmpty()){
					cima = stack.top();
					stack.pop();
					if(!stack.isEmpty()){
						subcima = stack.top();
						stack.pop();
						stack.push(subcima*cima);
						resultado = true;
					}
					else{
						stack.push(cima);
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case SUB: 
				if(!stack.isEmpty()){
					cima = stack.top();
					stack.pop();
					if(!stack.isEmpty()){
						subcima = stack.top();
						stack.pop();
						stack.push(subcima-cima);
						resultado = true;
					}
					else{
						stack.push(cima);
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case DIV: 
				if(!stack.isEmpty()){
					cima = stack.top();
					stack.pop();
					if(!stack.isEmpty()){
						subcima = stack.top();
						if(cima !=0){
							stack.pop();
							stack.push(subcima/cima);
							resultado = true;
						}
						else{
							stack.push(cima);
							resultado = false;
						}
					}
					else{
						stack.push(cima);
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case OUT: 
				if(!stack.isEmpty()){
					cima = stack.top();
					if(cima != 0){
						System.out.println((char) cima);
						stack.pop();
						resultado = true;
					}
					else{
						resultado = false;;
					}
				}
				else{
					return false;
				}
				break;	
			case STORE: 
				if(instruction.getParam()>=0){
					if (!stack.isEmpty()){
						exito = memory.store(instruction.getParam(), stack.top());
						if (exito){
							stack.pop();
							resultado = true;
						}
						else{
							resultado = false;
						}
					}
					else{
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case LOAD:
				cima = memory.load(instruction.getParam());
				if(cima != 0){
					exito = stack.push(cima);
					if(exito){
						resultado = true;
					}else{
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case HALT:
				this.halt = true;
				resultado = true;
				break;
			case FLIP:
				if(!stack.isEmpty()){
					cima = stack.top();
					stack.pop();
					if(!stack.isEmpty()){
						subcima = stack.top();
						stack.pop();
						stack.push(cima);
						stack.push(subcima);
						resultado = true;
					}
					else{
						stack.push(cima);
						resultado = false;
					}
				}
				else{
					resultado = false;
				}
				break;
			case DUP:
				if(!stack.isEmpty()){
					cima = stack.top();
					exito = stack.push(cima);
					if(exito){
						resultado = true;
					}
					else{
						resultado = false;
					}
				}else{
					resultado = false;
				}
				break;
			default:
				resultado = false;
			}
			return resultado;
		}else{
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
	
}
