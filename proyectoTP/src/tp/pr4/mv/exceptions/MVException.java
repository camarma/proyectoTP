package tp.pr4.mv.exceptions;

/**
 * Clase principal de las excepciones de nuestra maquina virtual.
 * 
 * @author George y Alberto
 *
 */

public class MVException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructora por defecto.
	 */
	public MVException(){
		super();
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public MVException(String cadena){
		super(cadena);
	}
	
	/**
	 * Constructora con causa de la excepcion que provoco el error.
	 * 
	 * @param causa- Comentario de la excepción.
	 */
	public MVException(Throwable causa){
		super(causa);
	}
	
	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param causa - Causa de la excepción probocada.
	 */
	public MVException(String cadena, Throwable causa){
		super(cadena, causa);
	}
	
	/**
	 * Constructora con el mensaje de la excepcion que provoco el error y un parámetro.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume - Parametro que provoca el error.
	 */
	public MVException(String cadena, int nume){
		super(cadena+nume);
	}


}
