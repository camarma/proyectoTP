package tp.pr5.mv.Model.IO;

import java.io.IOException;

/**
 * Clase hija de OutMethod encargada de la entrada de teclado.
 * @author George y Alberto
 *
 */
public class StdOutMethod implements OutMethod {

	/**
	 * Metodo sobreescrito encrgado de escrinir un caracter.
	 * param caracter.
	 */
	@Override
	public void write(char c) throws IOException {
		// TODO Auto-generated method stub
		System.out.print(c);
	}

}
