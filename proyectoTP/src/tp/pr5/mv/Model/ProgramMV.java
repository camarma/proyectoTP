package tp.pr5.mv.Model;


/**
 * Clase encargada de leer el programa.
 * @author George y Alberto
 *
 */

public class ProgramMV{

	public Instruction[] listaInstruciones;
	public static Instruction[] listaAux;
	/**
	 * Constructora por defecto.
	 */
	public ProgramMV(){
		
	} 
	
	public ProgramMV(Instruction[] lista){
		this.listaInstruciones = lista;
		listaAux = listaInstruciones;
		
	}
	
	/**
	 * Devuelve la instrucción en la posición indicada.
	 * @param i -es la posición de la instrucción.
	 * @return
	 * la instrucción si la posición entra en el rango.
	 */
	public Instruction getInstructionAt(int i){
		if(listaInstruciones.length<=0 || i < 0 || listaInstruciones.length <=i){
			return null;
		}
		else{
			return listaInstruciones[i];
		}
	}
	
	/**
	 * Método encargado de devolver la lista de instrucciones.
	 * @return lista de instrucciones.
	 */
	public int setTamLista(){
		return listaAux.length;
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		private Instruction lista[]=null;
		Data(Instruction[] instructions) {
			super();
			this.lista = instructions;
		}
		
		public Instruction[] getInstructions(){

			return lista;
		}
		
	}
	
	public Data getData(){

		Data realListInstruction = new Data(listaInstruciones);	
		return realListInstruction;
	}
}
