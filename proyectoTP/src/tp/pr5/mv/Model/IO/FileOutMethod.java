package tp.pr5.mv.Model.IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase hija de OutMethod encargada de la salida de fichero.
 * @author George y Alberto
 *
 */
public class FileOutMethod implements OutMethod {
	private OutputStream out;

	/**
	 * Constructora para inicializar la salida de fichero.
	 * Inicializa la salida de fichero. 
	 * @param archivo
	 */
	public FileOutMethod(String archivo){
		try {
			this.out = new FileOutputStream(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo sobreescrito de la interfaz para leer el fichero.
	 * @param caracter a escribir.
	 * @return devuelve el caracter convertido a entero que leas.
	 */
	@Override
	public void write(char c) throws IOException {
		// TODO Auto-generated method stub
		out.write(c);
	}

}
