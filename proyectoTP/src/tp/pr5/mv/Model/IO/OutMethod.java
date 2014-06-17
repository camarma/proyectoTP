package tp.pr5.mv.Model.IO;
 
import java.io.IOException;

/**
 * Interfaz encargada de definir la funcionalidad para los tres tipos posibles de salida.
 * El método definido permiten escribir un caracter en la salida definida ya sea en fichero, en teclado o que no haga nada.
 * @author George y Alberto
 *
 */
public interface OutMethod {

	/**
	 * Escribe un caracter en la salida.
	 * @param caracter a escrbir.
	 */
	public void write(char c) throws IOException;
}
