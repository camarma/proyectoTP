package tp.pr4.mv.exceptions.memory;

/**
 * Clase encargada de controlar las excepciones producidas por un error en la memoria.
 * 
 * @author George y Alberto
 *
 */

import tp.pr4.mv.exceptions.MVException;

public class MemoryException extends MVException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public MemoryException(){
		super();
		
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public MemoryException(String cadena){
		super(cadena);
	}
	
	/**
	 * Constructora con string y un parametro para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume- Valor del error.
	 */
	public MemoryException(String cadena,int nume){
		super(cadena+" "+nume);
	}
}
