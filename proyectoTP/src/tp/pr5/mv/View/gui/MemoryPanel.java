package tp.pr5.mv.View.gui;
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.Model.DataMemoryRegister;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.Memory.Data;
import tp.pr5.mv.Controller.SwingController;
/**
 * Clase encargada de pintar el panel de la memoria en la ventana
 * @author George y Alberto
 *
 */
public class MemoryPanel extends JPanel implements Memory.Observer{

	
	private static final long serialVersionUID = 1L;

	// Componentes visuales
	private JScrollPane scroll;
	private ModeloTabla modelo;
	private JTable tbMemoria;
	private JTextField txtPos;
	private JTextField txtValor;
	private JLabel lblPos;
	private JLabel lblValor;
	private JButton btnWrite;
	private SwingController s_ctrl;
	private DataMemoryRegister[] data;
	private ErrorDialog errorDialog;
	
	/**
	 * Constructora para inicializar el panel de memoria con sus botones y ventas correspondientes
	 * @param gui recibe como entrada al controlador
	 */
	public MemoryPanel(SwingController ctrl){

		this.s_ctrl = ctrl;
		this.errorDialog = new ErrorDialog();
		build();

	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		
		// Establecer un borde para el panel
		setBorder(BorderFactory.createTitledBorder("Memoria"));	
		setLayout(new BorderLayout());
		
		// Crear la tabla con un modelo personalizado
		modelo = new ModeloTabla();
		tbMemoria = new JTable(modelo);
		scroll = new JScrollPane(tbMemoria);
		scroll.setPreferredSize(new Dimension(100,100));
		tbMemoria.setFont(new Font("Courier",Font.PLAIN,16));
		add(scroll, BorderLayout.CENTER);
		
		// Crear el panel con los textfields y el botones
		JPanel panel = new JPanel();
		lblPos = new JLabel("Posición", JLabel.CENTER);
		txtPos = new JTextField(5);
		lblValor = new JLabel("Valor", JLabel.CENTER);
		txtValor = new JTextField(5);		
		
		btnWrite = new JButton("Write");
		botonWrite();

		panel.add(lblPos);
		panel.add(txtPos);
		panel.add(lblValor);
		panel.add(txtValor);
		panel.add(btnWrite);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * metodo encargado de la ejecucion del boton write
	 */
	private void botonWrite(){
		
		btnWrite.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(errorDialog.validarOperando(txtPos.getText())){
					if(errorDialog.validarOperando(txtValor.getText()) && Integer.parseInt(txtValor.getText()) >= 0){
							s_ctrl.performWrite(Integer.parseInt(txtPos.getText()),Integer.parseInt(txtValor.getText()));
					}else{
						errorDialog.reportError("Valor incorrecto");
					}
				}else{
					errorDialog.reportError("Posición incorrecta");
				}
			}
		});
	}
	
	/**
	 * metodo encargado de refrescar la vista en la memoria
	 */
	void refreshView() {
		modelo.refresh();
	}
	
	/**
	 * metodo encargado de deshabilitar los botones e inputs
	 */
	public void disable(){
		txtValor.setEnabled(false);
		txtPos.setEnabled(false);
		btnWrite.setEnabled(false);
	}
	
	/**
	 * Clase que genera el modelo de la tabla
	 * @author George y Alberto
	 *
	 */
	private class ModeloTabla extends AbstractTableModel {
		

		private static final long serialVersionUID = 1L;
		
		String[] colNames = { "Posición", "Valor" };
		
		/**
		 * Constructora que llama al metodo refrescar
		 */
		ModeloTabla() {
			refresh();
		}
		
		/**
		 * metodo encargado de repintar la tabla con los nuevos valores
		 */
		public void refresh() {
			this.fireTableDataChanged();
		}

		/**
		 * metodo encargado de recoger el nombre de la columna
		 */
		public String getColumnName(int column){
			return colNames[column];
		}

		/**
		 * metodo que devuelve el numero de columnas
		 */
		@Override
		public int getColumnCount() {
			return 2;
		}
		
		/**
		 * metodo encargado de devolver el numero de celdas
		 */
		@Override
		public int getRowCount() {
			if(data == null){
				return 0;
			}
			else{
				return data.length;
			}
		}

		/**
		 * metodo encargado de pintar la tabla con las columnas y filas de la tabla 
		 */
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			DataMemoryRegister fila = data[rowIndex];
			if(columnIndex==0)
				return fila.getPos();
			else
				return fila.getValue();
		}
	}
	
	/**
	 * metodo encargado de recuperar los datos de la memoria
	 */
	@Override
	public void onMemoryChange(Data data) {
		// TODO Auto-generated method stub
		this.data = data.getData();
		//refreshView();
		SwingUtilities.invokeLater(new RefreshMemory());
		
	}
	
	/**
	 * Clase encargada de refrescar el panel de la memoria.
	 * @author George y Alberto
	 *
	 */
	class RefreshMemory implements Runnable{
		
		public RefreshMemory() {
			// TODO Auto-generated constructor stub
			//refreshView();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			refreshView();
		}
	}
}