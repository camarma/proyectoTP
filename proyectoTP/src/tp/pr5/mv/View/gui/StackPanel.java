package tp.pr5.mv.View.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.OperandStack.Data;
import tp.pr5.mv.Controller.SwingController;

/**
 * Clase encargada de pintar el panel de la pila en la ventana
 * @author George y Alberto
 *
 */

public class StackPanel extends JPanel implements OperandStack.Observer{ 
	
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scroll;
	private JList<Integer> lstPrograma;
	private DefaultListModel<Integer> modeloLista;
	private JLabel lblValor; 
	private JTextField txtValor;
	private JButton btnPush;
	private JButton btnPop;
	private SwingController s_ctrl;
	private JPanel panel;
	private int[] datos;
	private ErrorDialog errorDialog;

	/**
	 * Constructora para inicializar el panel de la pila con sus botones y llamara al build
	 * @param gui recibe como entrada al controlador
	 */
	public StackPanel(SwingController ctrl){
		
		this.s_ctrl=ctrl;
		errorDialog = new ErrorDialog();
		build();
	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		
		// Establecer un borde para el panel
		setBorder(BorderFactory.createTitledBorder("Pila de Operandos"));
		setLayout(new BorderLayout());
		// Crear un componente de tipo JList con un modelo personalizado
		modeloLista = new DefaultListModel<Integer>();
		lstPrograma = new JList<Integer>(modeloLista);
		scroll = new JScrollPane(lstPrograma);
		scroll.setPreferredSize(new Dimension(560, 260));
		lstPrograma.setFont(new Font("Courier",Font.PLAIN,16));
		add(scroll,BorderLayout.CENTER);
		
		//Botones y Label
		panel = new JPanel();
		lblValor = new JLabel("Valor", JLabel.CENTER);
		txtValor = new JTextField(5);
		
		btnPush = new JButton("Push");
		botonPush();
		btnPop = new JButton("Pop");
		botonPop();
	
		panel.add(lblValor);
		panel.add(txtValor);
		panel.add(btnPush);
		panel.add(btnPop);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * metodo encargado de la ejecucion del boton push
	 */
	private void botonPush(){
		btnPush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(errorDialog.validarOperando(txtValor.getText())){
					s_ctrl.performPush(Integer.parseInt(txtValor.getText()));
				}
				else{
					errorDialog.reportError("Valor invalido.");
				}
				
			}
		});
	}
	
	/**
	 * metodo encargado de la ejecucion del boton pop
	 */
	private void botonPop(){
		btnPop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s_ctrl.performPop();
			}
		});
	}

	/**
	 * Metodo encargado de refrescar las vista de la pila
	 */
	public void refreshView(){

		modeloLista.clear();
		for(int i = 0; i<datos.length;i++){
			this.modeloLista.addElement(datos[i]);
		}
	
	}
	
	/**
	 * metodo encargado de deshabilitar los botones e inputs
	 */
	public void disable(){
		txtValor.setEnabled(false);
		btnPush.setEnabled(false);
		btnPop.setEnabled(false);
	}	
	
	/**
	 * metodo encargado de recuperar los datos de la pila
	 */
	@Override
	public void onStackChange(Data data) {
		// TODO Auto-generated method stub
		this.datos = data.getStack();
		//refreshView();
		SwingUtilities.invokeLater(new RefreshStack());
		
	}
	
	/**
	 * Clase encargada de refrescar el panel de la pila.
	 * @author George y Alberto
	 *
	 */
	class RefreshStack implements Runnable{
		
		public RefreshStack(){
		
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			refreshView();
		}
	}
}
