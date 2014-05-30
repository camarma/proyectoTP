package tp.pr5.mv.Model.IO;

/**
 * Interfaz encargada de definir la funcionalidad para los tres tipos posibles de entrada.
 * El método definido permiten leer un caracter de la entrada definida ya sea de un fichero, de teclado o que no haga nada.
 * @author George y Alberto
 *
 */
public interface InMethod {
	
	/**
	 * Lee un caracter de la entrada.
	 */
	public int read();
}
