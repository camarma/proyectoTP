package tp.pr5.mv.Model;

import java.util.Arrays;
import tp.pr5.mv.Model.exceptions.memory.MemoryException;

/**
 * Clase encargada de representar la memoria de datos. 
 * Cada dato se define por su dirección y valor. 
 * Para representar un dato en memoria se utiliza la clase DataMemoryRegister.
 * @author George y Alberto.
 *
 */

public class Memory extends Observable<Memory.Observer>{
	private DataMemoryRegister [] memory;
	private int cima;
	
	/**
	 * Constructora que crea la memoria de datos con la capácidad de almacenamiento máxima indicada en el parámetro.
	 * @param max_size - es la capacidad máxima de la memoria. Se asume que siempre será un número mayor que 0.
	 */
	public Memory(int max_size){
		memory = new DataMemoryRegister [max_size];
		cima = 0;
	}
	
	/**
	 * Indica que la posición de memoria indicada es correcta y que contiene un valor válido.
	 * @param pos - es la posición de memoria cuyo valor se desea obtener.
	 * @return 
	 * True si la posición de memoria indicada es correcta y que contiene un valor válido.
	 */
	public boolean canLoad(int pos) throws MemoryException{
		if (MemoriaVacia()){
			throw new MemoryException("Memoria vacia");
		}else{
			for (int i=0; i<cima;i++){
				if(memory[i].getPos()==pos){
					return true;
				}
			}
			throw new MemoryException("Dirección de memoria incorrecta, no se puede cargar la posición:", pos);
		}
	}

	/**
	 * Devuelve el valor en la posición de memoria indicada. 
	 * En caso de indicarse una posición incorrecta se devolverá 0 por defecto. 
	 * Los clientes de esta clase deben llamar al método canLoad antes de llamar a este método para comprobar que la posición de memoria es correcta y que contiene un valor válido.
	 * @param pos - es la posición de memoria cuyo valor se desea obtener.
	 * @return 
	 * El valor de dicha posición de memoria si dicha posición es válida, o 0 e.o.c.
	 * @throws MemoryException 
	 */
	public int load(int pos) throws MemoryException{
		int i;
		if(canLoad(pos) == true){
			for(i=0;i<cima;i++){
				if(this.memory[i].getPos()==pos){
					return this.memory[i].getValue();
				}
			}
		}
		return 0;
	}
	
	/**
	 * Almacena un valor en la posición de memoria indicada. En caso de que esa posición ya almacene un valor se sobreescribirá.
	 * @param pos - es la posición de memoria.
	 * @param value - es el valor a almacenar.
	 * @return 
	 * Si la operación ha podido realizarse con éxito o si ha fallado ya que se ha alcanzado la capacidad máxima o la dirección es incorrecta.
	 * @throws MemoryException 
	 */
	public boolean store(int pos,int value) throws MemoryException{		
		if(pos<0){
			throw new MemoryException("Posición incorrecta:"+pos);
		}
		else{
			int posRegistro = this.existRegister(pos);
			
			if(posRegistro!=-1){
				this.memory[posRegistro].setValue(value);
				for (Observer o : observers){
					o.onMemoryChange(getData());
				}
				return true;
			}
			else{
				if(this.memory.length == cima){
					throw new MemoryException("Memoria llena.");
				}
				else{
				int posInsercion = this.buscaPosicion(pos);
				
				desplazar(posInsercion);
				
				this.memory[posInsercion] = new DataMemoryRegister(pos,value);
				
				this.cima++;
				
				for (Observer o : observers){
					o.onMemoryChange(getData());
				}
				
				return true;
				}
			}
		}
	}
	
	/**
	 * Método privado que comprueba si existen dos registros con la misma dirección.
	 * Si no existe ninguna dirección igual se devuelve -1.
	 * Si existe ya esa dirección se devuelve la posición en la que se encuentra para sobreescrbir su valor.
	 * @param dir - Dirección de la memoria en la que se guardará el registro.
	 * @return 
	 * Si el registro ya existe se manda la posicion de memoria que ocupa y sino -1.
	 */
	private int existRegister(int dir){
		int i=0;
		for (i=0;i<cima;i++){
			if(memory[i] != null){
				if(memory[i].getPos()==dir)
					return i;
			}else{
				return -1;
			}
		}
		return -1;
	}
	
	/**
	 * Método privado que busca en que posición hay que insertar un nuevo registro.
	 * Si el registro que seu quiere guardar es mayor a los que ya exixten en la memoria se devuelve la posición de la cima para que inserte un nuevo valor.
	 * En caso de que el registro deba introducirse entre direcciones ya existentes se devuelve la posicion donde debe introducirse para posteriormente dejar el huco libre con el metodo desplazar.
	 * @param dir - Dirección de la memoria en la que se guardará el registro.
	 * @return 
	 * Posición de la memoria donde se debe introducir el registro.
	 */
	private int buscaPosicion(int dir){
		int pos=cima;
		int i=0;
		for (i=0;i<cima;i++){
			if(memory[i].getPos()>dir){
				return i;
			}
		}
		return pos;
	}
	
	/**
	 * Método privado que se encarga de desplazar las posiciones de memoria desde la posición proporcionada por el método buscaPosicion
	 * @param posInse - Posición que hay que dejar libre para introducir el nuevo registro.
	 */
	private void desplazar(int posInse){
		int i=cima;
		while(i>0){
			if(i >= posInse){
				memory[i] = memory[i-1];
			}
			i--;
		}
	}
	
	/**
	 * Método encargado de comprobar si la memoria está vacía.
	 * @return true si esta vacía false en caso contrario.
	 */
	private boolean MemoriaVacia(){
		boolean vacia = false;
		for (int i=0; i<memory.length; i++){
			if(memory[i]!=null){
				return vacia;
			}
		}
		return vacia = true;
	}
	
	/**
	 * Interfaz implementado por los observadores de la clase
	 * para ser notificados cuando ocurre algún evento en la memoria.
	 */
	public interface Observer {
		
		/**
		 * Se invoca cuando ha habido un cambio en la memoria
		 */
		void onMemoryChange(Data data);

	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 * @author Juan A. Recio García
	 */
	public class Data{
		private DataMemoryRegister data[] = null;
		Data(DataMemoryRegister[] data) {
			super();
			this.data = data;
		}

		public DataMemoryRegister[] getData() {
			return data;
		}
		
	}
	
	public Memory.Data getData(){
		Data realMemory = new Data(Arrays.copyOf(memory, cima));
		return realMemory;
	}
}
