package tp.pr3.mv;

import java.io.IOException;
import java.io.InputStream;

/**
 * Clase encargada de devolver un cero.
 * @author George y Alberto.
 *
 */
public class NullInputStream extends InputStream{
	
	/**
	 * Método encargado de devolver un cero.
	 */
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}