package tp.pr5.mv.Model.exceptions.stack;
 
/**
 * Clase encargada de controlar las excepciones producidas por un error en la pila.
 * 
 * @author George y Alberto
 *
 */

import tp.pr5.mv.Model.exceptions.MVException;

public class StackException extends MVException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public StackException(){
		super();
		
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public StackException(String cadena){
		super(cadena);
	}
}
