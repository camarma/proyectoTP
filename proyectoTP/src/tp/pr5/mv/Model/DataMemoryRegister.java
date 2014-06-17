package tp.pr5.mv.Model;
/**
 * Clase encargada de representar un valor almacenado en la memoria de datos.
 * @author George y Alberto
 *
 */
 
public class DataMemoryRegister {
	private int dir;//Direccion que le pasas al store. ejemplo STORE (3,4) -> 3 es pos, 4 es value
	private int value;
	
	/**
	 * Crea el registro de la memoria de datos con una posición y valor dados.
	 * @param pos -es la posición del valor en la memoria.
	 * @param value  -es el valor de dicha posición de memoria.
	 */
	public DataMemoryRegister(int pos, int value){
		dir= pos;
		this.value=value;
	}
	
	/**
	 * Devuelve la posición de memoria ocupada por el registro.
	 * @return
	 * Devuelve la posición de la memoria ocupada por el registro.
	 */
	public int getPos(){
		return dir;
	}
	
	/**
	 * Devuelve el valor de dicha posición de memoria.
	 * @return
	 * Devuelve el valor del registro.
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Establece un nuevo valor en el registro.
	 * @param value -es el nuevo valor de dicha posición de memoria.
	 */
	public void setValue(int value){
		this.value = value;
	}
	
}
