package tp.pr5.mv.Model.exceptions.instructions;
 
/**
 * Clase encargada de controlar las excepciones producidas por un error en una instrucción aritmética.
 * 
 * @author George y Alberto
 *
 */

public class ArithmeticException extends InstructionsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructora por defecto.
	 */
	public ArithmeticException(){
		super();
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public ArithmeticException(String cadena){
		super(cadena);
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public ArithmeticException(Throwable causa){
		super(causa);
	}
	
	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume1 - parametro que hace referencia a la cima.
	 */
	public ArithmeticException(String cadena,int nume1){
		 super(cadena+" "+nume1);
	}
	

	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume1 - parametro que hace referencia a la cima.
	 * @param nume2 - parametro que hace referencia a la subcima.
	 */
	public ArithmeticException(String cadena,int nume1, int nume2){
		 super(cadena+" "+nume1+"/"+nume2);
	}
}
