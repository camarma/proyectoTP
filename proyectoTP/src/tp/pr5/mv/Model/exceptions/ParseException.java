package tp.pr5.mv.Model.exceptions;
/**
 * Clase encargada de controlar las excepciones producidas por un parseo incorrecto.
 * 
 * @author George y Alberto
 *
 */
public class ParseException extends MVException{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public ParseException(){
		super();
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public ParseException(String cadena){
		super(cadena);
	}
	
	public ParseException(Throwable causa){
		super(causa);
	}

}
