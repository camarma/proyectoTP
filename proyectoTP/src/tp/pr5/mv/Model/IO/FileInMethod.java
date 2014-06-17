package tp.pr5.mv.Model.IO;
 
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Clase hija de InMethod encargada de la entrada de fichero.
 * @author George y Alberto
 *
 */
public class FileInMethod implements InMethod{
	
	private InputStream in;
	
	/**
	 * Constructora para inicializar la entrada de fichero.
	 * Inicializa la entrada de fichero. 
	 * @param file - nombre del fichero.
	 */
	public FileInMethod(String file) {
		try {
			this.in = new FileInputStream (file);
		}
		catch(FileNotFoundException e) {
				System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Metodo sobreescrito de la interfaz para leer el fichero.
	 * @return devuelve el caracter convertido a entero que leas.
	 */
	@Override
	public int read() {
		// TODO Auto-generated method stub
		int entrada = -1;
		try {
			entrada = in.read();
		}
		catch (EOFException e) {
			System.err.println(e.getMessage());
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return entrada;
	}
}
