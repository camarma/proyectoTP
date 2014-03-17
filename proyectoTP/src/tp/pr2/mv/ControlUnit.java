package tp.pr2.mv;
/**
 * Unidad de control de la máquina virtual. Controla el contador de programa y gestiona el estado de la máquina (halt).
 * @author George y Alberto
 *
 */

public class ControlUnit {
	private int cp;
	private boolean halt = false;
	private boolean bloquea = false;
	
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
	public boolean setCP(int NewCP) {
		
		if(NewCP < 0 || isHalted()){
			return false;
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
	public boolean increaseCP(int increment){		
		
		if(isHalted()){
			return false;
		}		
		else{			
			cp = cp + increment;
			bloquea = true;
			return true;
		}
	}
	
	/**
	 * Avanza si la maquina no esta detenida o en caso de k el contador no se haya modificado por la el setCP o el increaseCP.
	 */
	public void next(){
		if(!isHalted()){
			if (bloquea == false){
				cp++;
			}
				bloquea = false;
		}	
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