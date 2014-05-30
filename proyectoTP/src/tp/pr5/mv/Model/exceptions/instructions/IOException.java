package tp.pr5.mv.Model.exceptions.instructions;

/**
 * Clase encargada de controlar las excepciones producidas por un error en una instrucción io.
 * 
 * @author George y Alberto
 *
 */

public class IOException extends InstructionsException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public IOException(){
		super();
		
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public IOException(String cadena){
		super(cadena);
	}
}
