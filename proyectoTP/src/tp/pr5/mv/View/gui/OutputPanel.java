package tp.pr5.mv.View.gui;
 
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Model.IO.SwingOutMethod;
/**
 * Clase encargada de pintar el panel de salida del programa 
 * @author George y Alberto
 *
 */
public class OutputPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;
	SwingOutMethod outmeth;
	private JTextArea salidaTextArea;
	private static StringBuilder Content = new StringBuilder();
	static char c = ' ';
	
	/**
	 * Constructora encargada de llamar al metodo build e inicializar el textarea
	 */
	public OutputPanel(){
		
		build();
		salidaTextArea.setText(Content.toString());
	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		
		setBorder(new TitledBorder("Salida"));
		setLayout(new BorderLayout());
		salidaTextArea = new JTextArea(6,70);
		salidaTextArea.setFont(new Font("Courier",Font.PLAIN,20));
		salidaTextArea.setEditable(false);
		add(new JScrollPane(salidaTextArea),BorderLayout.CENTER);	
		
	}
	
	/**
	 * metodo encargado de pintar la salida
	 * @param content
	 */
	@SuppressWarnings("static-access")
	public void setTextArea(StringBuilder content){
		this.Content = content;
		salidaTextArea.setText(Content.toString());
		SwingUtilities.invokeLater(new RefreshOut(content));
	}
	
	/**
	 * Clase encargada de refrescar el panel de salida.
	 * @author George y Alberto
	 *
	 */
	class RefreshOut implements Runnable{
		private StringBuilder content;
		public RefreshOut(StringBuilder content) {
			// TODO Auto-generated constructor stub
			//salidaTextArea.setText(content.toString());
			this.content = content;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			salidaTextArea.setText(content.toString());
		}
	}
}

