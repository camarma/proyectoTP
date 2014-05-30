package tp.pr5.mv.Model.IO;
import tp.pr5.mv.View.gui.OutputPanel;

/**
 * Clase hija de OutMethod encargada de la entrada en modo swing.
 * @author George y Alberto
 *
 */

public class SwingOutMethod implements OutMethod{
	
	private OutMethod out;
	private OutputPanel op = new OutputPanel();
	private StringBuilder content = new StringBuilder();
	
	/**
	 * Constructora para inicializar el archivo de salida y pintarlo
	 */
	public SwingOutMethod(OutMethod out){
		this.out = out;
	}

	
	/**
	 *metodo encargado de leer y repintar la salida
	 * 
	 */
	public void write(char c){
		try {
		if(out != null){
			out.write(c);
		}
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content.append(c);
		op.setTextArea(content);
		
	}
	
	/**
	 * metodo encargado de inicilizar el panel de salida
	 * @param op
	 */
	public void setOutput(OutputPanel op){
		this.op = op;
	}

}
