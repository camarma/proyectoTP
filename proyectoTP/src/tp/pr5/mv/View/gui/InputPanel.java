package tp.pr5.mv.View.gui;
 
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 * Clase encargada de pintar el panel de entrada del programa en la ventana
 * @author George y Alberto
 *
 */

public class InputPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private static JTextArea entradaTextArea;
	private static StringBuilder Content = new StringBuilder();
	
	
	/**
	 * Constructora encargada de llamar al metodo build e inicializar el textarea
	 */
	public InputPanel(){
		build();
		entradaTextArea.setText(Content.toString());

	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Entrada"));
		entradaTextArea = new JTextArea(6,70);
		entradaTextArea.setFont(new Font("Courier",Font.PLAIN,20));
		entradaTextArea.setEditable(false);
		add(new JScrollPane(entradaTextArea),BorderLayout.CENTER);	
		
	}
	
	/**
	 * Metodo encargado de pintar en el text area
	 * @param content es el contenido que recibe
	 */
	public void getTextArea(StringBuilder content){
		entradaTextArea.setText(content.toString());
	}
	
	/**
	 * Metodo encargado de inicializar el contenido para pinarla por primera vez
	 * @param content
	 */
	@SuppressWarnings("static-access")
	public void rellenaPanel(StringBuilder content){
		SwingUtilities.invokeLater(new RefreshIn(content));
		this.Content = content;
	}
	
	/**
	 * Clase encargada de refrescar el panel de entrada.
	 * @author George y Alberto
	 *
	 */
	class RefreshIn implements Runnable{
		private StringBuilder content;
		public RefreshIn(StringBuilder content) {
			// TODO Auto-generated constructor stub
			this.content = content;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			entradaTextArea.setText(content.toString());
		}
	}
}
