package tp.pr5.mv.Controller;
 
import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.instructions.memory.Write;
import tp.pr5.mv.Model.instructions.stack.Pop;
import tp.pr5.mv.Model.instructions.stack.Push;

/**
 * Clase encargada de realizarla comunicacion entre venta y modelo
 * @author George y Alberto
 *
 */

public abstract class Controller {

	protected CPU cpu;
	
	/**
	 * Constructora encargada de inicilzar la cpu
	 * @param model
	 */
	public Controller(CPU model) {
		this.cpu = model;
	}

	/**
	 * metodo encargado de ejecutar la instruccion push
	 * @param v el valor k apila
	 */
	public void performPush(int v) {
		this.cpu.executeInstruction(new Push(v));
	}

	/**
	 * metodo encargado de ejecutar la instruccion pop
	 */
	public void performPop() {
		this.cpu.executeInstruction(new Pop());
	}

	/**
	 * metodo encargado de ejecutar la instruccion write
	 * @param pos la posicion
	 * @param val el valor 
	 */
	public void performWrite(int pos, int val) {
		this.cpu.executeInstruction(new Write(pos, val));
	}

	/**
	 * metodo encargado de añadir el observer de la pila 
	 * @param obs
	 */
	public void addStackObserver(OperandStack.Observer obs) {
		this.cpu.addObserver(obs);
	}
	
	/**
	 * metodo encargado de añadir el observer de la memoria
	 * @param obs
	 */
	public void addMemoryObserver(Memory.Observer obs) {
		this.cpu.addObserver(obs);
	}
	
	/**
	 * metodo encargado de añadir el observer de la cpu
	 * @param obs
	 */
	public void addCPUObserver(CPU.Observer obs) {
		this.cpu.addObserver(obs);
	}
	
	/**
	 * metodo encargado de añadir el observer del control unit
	 * @param obs
	 */
	public void addControlUnitObserver(ControlUnit.Observer obs) {
		this.cpu.addObserver(obs);
	}

	/**
	 * metodo encargado de llamar al metodo step de la cpu
	 */
	public void performStep(){
		this.cpu.step();
	}
	
	/**
	 * metodo encargado de llamar al metodo run de la cpu
	 */
	public void performRun(){
		do{
			this.cpu.step();
		}while(!cpu.isHalted() && !cpu.error());
	}
	
	/**
	 * metodo encargado de llamar al metodo start de la cpu
	 */
	public void start(){
		this.cpu.start();
	}
}
