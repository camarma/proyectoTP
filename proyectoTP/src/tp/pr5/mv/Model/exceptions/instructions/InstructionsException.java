package tp.pr5.mv.Model.exceptions.instructions;
 
/**
 * Clase encargada de controlar las excepciones producidas por un error en una instrucción.
 * 
 * @author George y Alberto
 *
 */

import tp.pr5.mv.Model.exceptions.MVException;

public class InstructionsException extends MVException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public InstructionsException(){
		super();
	}
	
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public InstructionsException(String causa){
		super(causa);
	}
	
	/**
	 * Constructora con causa de la excepcion que provoco el error.
	 * 
	 * @param causa- Comentario de la excepción.
	 */
	public InstructionsException(Throwable causa){
		super(causa);
	}
	
	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param causa- Comentario de la excepción.
	 * @param causa - Causa d ela excepcion probocada.
	 */
	public InstructionsException(String cadena, Throwable causa){
		super(cadena, causa);
	}
	
	/**
	 * Constructora con el mensaje de la excepcion que provoco el error y un parámetro.
	 * 
	 * @param causa- Comentario de la excepción.
	 * @param nume - Parametro que provoca el error.
	 */
	public InstructionsException(String cadena, int nume){
		super(cadena, nume);
	}
	
}
