package tp.pr5.mv.Model.IO;

import java.io.IOException;

/**
 * Clase hija de InMethod encargada de la entrada de teclado.
 * @author George y Alberto
 *
 */
public class StdInMethod implements InMethod{
	
	private boolean msg=true;
	/**
	 * Constructora por defecto
	 */
	public StdInMethod(){
		
	}
	
	/**
	 * Metodo sobreescrito encrgado de leer un caracter.
	 * @return caracter convertido a entero.
	 */
	@Override
	public int read(){
		// TODO Auto-generated method stub
		int in = -1;
		try {
			if(msg){
				msg = false;
				System.out.print("Introduce caracter de entrada: ");
			}
			in = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
}
