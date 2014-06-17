package tp.pr5.mv.Model;
 
import tp.pr5.mv.Model.exceptions.cp.CpException;

/**
 * Unidad de control de la máquina virtual. Controla el contador de programa y gestiona el estado de la máquina (halt).
 * @author George y Alberto
 *
 */

public class ControlUnit extends Observable<ControlUnit.Observer>{
	private int cp;
	private boolean halt = false;
	private boolean bloquea = false;
	private ProgramMV program = new ProgramMV();
	
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
	public void setCP(int NewCP) throws CpException{
		if(program.setTamLista()<NewCP){
			throw new CpException("El salto realizado sobrepasa el numero de instrucciones del programa");
		}
		if(NewCP < 0 || isHalted()){
			throw new CpException("El salto realizado no pertenece a una posicion correcata");
		}
		else{
			cp = NewCP;
			bloquea = true;
			for(Observer o: observers){
				o.onCPchange(getData());
			}
		}
	}
	
	/**
	 * Incrementa el valor del contador de programa. Antes de establecer el valor se comprueba que la máquina no esté detenida.
	 * @param increment  -es el incremento del cp. Puede ser un valor positivo o negativo.
	 * @return
	 * Devuelve false si la maquina esta detenida. true en caso de incrementar.
	 */
	public void increaseCP(int increment)throws CpException{	
		int calculaSalto=0;
		if(isHalted()){
			throw new CpException("Maquina parada");
		}		
		else{
			calculaSalto= cp + increment;
			if(program.setTamLista()<calculaSalto || cp+calculaSalto <= 0){
				throw new CpException("El incremento: "+ increment +",sobrepasa el numero de instrucciones del programa");
			}
			cp = calculaSalto;
			bloquea = true;
			for(Observer o: observers){
				o.onCPchange(getData());
			}
		}
	}	
	/**
	 * Avanza si la maquina no esta detenida o en caso de que el contador no se haya modificado por el setCP o el increaseCP.
	 */
	public void next(){
			isHalted();
				if (bloquea == false){
					cp++;
					for(Observer o : observers){
						o.onCPchange(getData());
					}
				}
					bloquea = false;
	}
	
	/**
	 * Para la máquina.
	 */
	public void halt(){
		halt = true;
		for(Observer o : observers){
			o.onHalt();
		}
	}
	
	/**
	 * Indica si la máquina está parada.
	 * @return
	 * si la maquina esta parada o no.
	 */
	public boolean isHalted(){		
		return halt;
	}
	
	/**
	 * Interfaz implementado por los observadores de la clase
	 * para ser notificados cuando ocurre algún evento en el ControlUnit.
	 */
	public interface Observer {
		
		
		/**
		 * Se invoca cuando ha habido un cambio en la unidad de control
		 */
		void onCPchange(Data cpData);
		
		/**
		 * Se invoca cuando la ejecución del programa ha terminado de manera
		 * correcta, es decir, cuando se alcanzada una instrucción HALT.
		 */
		public void onHalt();
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		private int controlUnit = 0;
		public int getCp() {
			return controlUnit;
		}

		Data(int cp) {
			super();
			this.controlUnit = cp;
		}
		
	}
	
	public ControlUnit.Data getData(){
		Data realCP = new Data(cp);
		return realCP;
	}
	
	
}