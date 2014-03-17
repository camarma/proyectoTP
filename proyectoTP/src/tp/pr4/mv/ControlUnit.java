package tp.pr4.mv;

import tp.pr4.mv.exceptions.cp.CpException;

/**
 * Unidad de control de la máquina virtual. Controla el contador de programa y gestiona el estado de la máquina (halt).
 * @author George y Alberto
 *
 */

public class ControlUnit {
	private int cp;
	private boolean halt = false;
	private boolean bloquea = false;
	private ProgramMV program= new ProgramMV();
	
	/**
	 * Constructora por defecto.
	 */
	public ControlUnit(){
		
	}
	
	/**
	 * Devuelve el valor del contador de programa.
	 * @return
	 * Devuelve el valor del contador de programa.
	 */
	public int getCP(){
		return cp;		
	}
	
	/**
	 * Establece el valor del contador de programa. Antes de establecer el valor se comprueba que la máquina no esté detenida y que el nuevo valor sea positivo.
	 * @param NewCP -es el nuevo valor del contador de programa.
	 * @return
	 * Devuelve false si la máquina está detenida o el nuevo valor es negativo. true en otro caso.
	 */
	public boolean setCP(int NewCP) throws CpException{
		
			if(program.getTamLista()<NewCP){
				throw new CpException("El salto realizado sobrepasa el numero de instrucciones del programa");
			}
			if(NewCP < 0 || isHalted()){
				throw new CpException("El salto realizado no pertenece a una posicion correcata");
			}
			else{
				cp = NewCP;
				bloquea = true;
				return true;
			}
	}
	
	/**
	 * Incrementa el valor del contador de programa. Antes de establecer el valor se comprueba que la máquina no esté detenida.
	 * @param increment  -es el incremento del cp. Puede ser un valor positivo o negativo.
	 * @return
	 * Devuelve false si la maquina esta detenida. true en caso de incrementar.
	 */
	public boolean increaseCP(int increment)throws CpException{		
		int calculaSalto=0;
			if(isHalted()){
				return false;
			}		
			else{
				calculaSalto= cp + increment;
				if(program.getTamLista()<calculaSalto || cp+calculaSalto <= 0){
					throw new CpException("El incremento: "+ increment +",sobrepasa el numero de instrucciones del programa");
				}
				cp = calculaSalto;
				bloquea = true;
				return true;
			}
	}
	
	/**
	 * Avanza si la maquina no esta detenida o en caso de que el contador no se haya modificado por el setCP o el increaseCP.
	 */
	public void next(){
			isHalted();
				if (bloquea == false){
					cp++;
				}
					bloquea = false;
	}
	
	/**
	 * Para la máquina.
	 */
	public void halt(){
		halt = true;
	}
	
	/**
	 * Indica si la máquina está parada.
	 * @return
	 * si la maquina esta parada o no.
	 */
	public boolean isHalted(){
		
		return halt;
	}
}