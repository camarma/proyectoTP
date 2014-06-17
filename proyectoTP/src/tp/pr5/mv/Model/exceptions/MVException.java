package tp.pr5.mv.Model.exceptions;
 
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
	private String msg;

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
		this.msg = cadena;
		
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
		this.msg = cadena;
	}
	
	/**
	 * Constructora con el mensaje de la excepcion que provoco el error y un parámetro.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume - Parametro que provoca el error.
	 */
	public MVException(String cadena, int nume){
		super(cadena+nume);
		this.msg = cadena+" "+nume;
	}
	
	public void message(String msg){
		this.msg = msg;
	}
	public String message(){
		return msg;
	}


}
