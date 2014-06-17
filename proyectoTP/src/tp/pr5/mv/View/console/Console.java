package tp.pr5.mv.View.console;
 
import tp.pr5.mv.Controller.Controller;
import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.ProgramMV.Data;

/**
 * Clase que se encarga de implementar los Obervers en modo consola
 * @author George y Alberto
 *
 */

public class Console implements CPU.Observer{
	private static String MSG_BEGIN = "Comienza la ejecucion del programa:";
	private static String MSG_SHOW = "El programa intruducido es:";
	private static String MSG_EXEC_END = "El estado de la maquina tras ejecutar la instruccion es:";
	private static final String SEP = System.getProperty("line.separator");
	private Controller ctrl;
	
	public Console(Controller ctrl){
		this.ctrl = ctrl;
		this.ctrl.addCPUObserver(this);
	}
	
	/**
	 * metodo encargado de mostrar en pantalla el programa cargado
	 */
	@Override
	public void onStart(Data instructions) {
		// TODO Auto-generated method stub
		System.out.println(MSG_SHOW);
		for(int i=0; i<instructions.getInstructions().length;i++){
			System.out.println(i+" :"+instructions.getInstructions()[i]);
		}
		
	}
	
	/**
	 * metodo encargado de mostrar la instruccion que se esta ejecutando
	 */
	@Override
	public void onInstructionStart(Instruction instr) {
		System.out.println(MSG_BEGIN + " "+ instr);
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * metodo encargado de mostrar el estado de la maquina
	 */
	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.Model.Memory.Data mem, tp.pr5.mv.Model.OperandStack.Data ops, tp.pr5.mv.Model.ControlUnit.Data pc) {
		// TODO Auto-generated method stub
		System.out.println(MSG_EXEC_END);
		System.out.println("Memoria: "+memorytoString(mem).toString()+ SEP +"Pila de operandos: "+stacktoString(ops).toString());
		//mem.getData().
	}
	
	/**
	 * metodo encargado de mostrar errores
	 */
	@Override
	public void onError(Exception error) {
		// TODO Auto-generated method stub
		System.out.println(error.getMessage());
	}
	
	/**
	 * metodo encargado de devolver el contenido de la memoria
	 * @param mem utilizado para recoger los valores de la memoria 
	 * @return resultado la representacion de la memoria
	 */
	private  String memorytoString(tp.pr5.mv.Model.Memory.Data mem){
		int i=0;
		String contenidoMemoria = "";
		for (i=0;i<mem.getData().length;i++){
			contenidoMemoria = contenidoMemoria + "["+ mem.getData()[i].getPos() +"]:" + mem.getData()[i].getValue() + " ";
		}
		if (contenidoMemoria==""){
			contenidoMemoria = "<vacia>";
		}
		return contenidoMemoria.trim();
	}
	
	/**
	 * metodo encargado de devolver el contenido de la pila
	 * @param ops utilizado para recoger los valores de la pila 
	 * @return contenidoPila la representacion de la Pila
	 */
	private String stacktoString(tp.pr5.mv.Model.OperandStack.Data ops){
		int i = 0;
		String contenidoPila="";
		if(ops.getStack().length < 0){
			contenidoPila = "<vacia>";
		}
		else{
			for (i=0; ops.getStack().length >i; i++){
				contenidoPila = contenidoPila + ops.getStack()[i]+" ";
			}
		}
		return contenidoPila.trim();
	}
}
