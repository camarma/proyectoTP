package tp.pr5.mv.Model.exceptions.instructions;

/**
 * Clase encargada de controlar las excepciones producidas por un error en una instrucción de salto.
 * 
 * @author George y Alberto
 *
 */

public class JumpException extends InstructionsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructora por defecto.
	 */
	public JumpException(){
		super();
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public JumpException(String cadena){
		super(cadena);
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public JumpException(Throwable causa){
		super(causa);
	}
	
	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume1 - parametro que hace referencia a la cima.
	 */
	public JumpException(String cadena,int nume1){
		 super(cadena+" "+nume1);
	}
}
