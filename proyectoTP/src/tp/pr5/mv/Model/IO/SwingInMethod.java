package tp.pr5.mv.Model.IO;
 
import tp.pr5.mv.View.gui.InputPanel;

/**
 * Clase hija de InMethod encargada de la entrada modo swing.
 * @author George y Alberto
 *
 */

public class SwingInMethod implements InMethod{
	@SuppressWarnings("unused")
	private InMethod in;
	private InputPanel ip = new InputPanel();
	private StringBuilder content;
	private int pos;
	
	/**
	 * constructora es inicializar el panel input con el contenido del fichero que se le proporciona
	 * @param in
	 */
	public SwingInMethod(InMethod in){
		this.in = in;
		this.content = new StringBuilder();
		int character = in.read();
		while (character != -1) {
			this.content.append((char)character);
			character = in.read();
		}
		ip.rellenaPanel(content);
	}
	
	/**
	 * metodo encargado de leer del ficher y sustituirlo con el *
	 */
	@Override
	public int read() {
		// TODO Auto-generated method stub
		int c = -1;
		if(pos != content.length()){
			c = content.codePointAt(pos);
			if (c != '\n' && c != '\r')
				content.replace(pos, pos+1, "*");
			this.pos++;
		}
		ip.getTextArea(content);
		return c;
	}	
}
