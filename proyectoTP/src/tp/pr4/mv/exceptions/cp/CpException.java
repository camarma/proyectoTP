package tp.pr4.mv.exceptions.cp;

import tp.pr4.mv.exceptions.MVException;

public class CpException  extends MVException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructora por defecto.
	 */
	public CpException(){
		super();
		
	}
	
	/**
	 * Constructora con string para recoger mensaje de la excepción.
	 * 
	 * @param cadena- Comentario de la excepción.
	 */
	public CpException(String cadena){
		super(cadena);
	}
	
	/**
	 * Constructora con causa y el mensaje de la excepcion que provoco el error.
	 * 
	 * @param cadena- Comentario de la excepción.
	 * @param nume1 - parametro que hace referencia a la cima.
	 */
	public CpException(String cadena,int nume1){
		 super(cadena+" "+nume1);
	}
}
