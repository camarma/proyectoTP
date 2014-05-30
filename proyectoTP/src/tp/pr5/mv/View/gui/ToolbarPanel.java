package tp.pr5.mv.View.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controller.SwingController;


/**
 * Clase encargada de pintar el toolbar panel en la ventana
 * @author George y Alberto
 *
 */

public class ToolbarPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	;
	private SwingController s_ctrl;
	private JButton	btnStep;
	private JButton btnRun;
	private JButton btnPause;
	private JButton btnQuit;
	/**
	 * Constructora para inicializar el toolbar panel con sus botones y llamar al metodo build
	 * @param gui recibe como entrada al controlador
	 */
	public ToolbarPanel(SwingController ctrl) {
		
		this.s_ctrl = ctrl;
		build();
	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		
		setBorder(new TitledBorder("Acciones"));
		btnStep = new JButton();
		btnStep.setIcon(createImageIcon("img/step2.png"));
		btnStep.setToolTipText("Step");
		add(btnStep);
		botonStep();
		btnRun = new JButton();
		btnRun.setIcon(createImageIcon("img/run2.png"));
		btnRun.setToolTipText("Run");
		add(btnRun);
		botonRun();
		btnPause = new JButton();
		btnPause.setIcon(createImageIcon("img/pause2.png"));
		btnPause.setToolTipText("Pause");
		add(btnPause);
		botonPause();
		btnQuit= new JButton();
		btnQuit.setIcon(createImageIcon("img/exit2.png"));
		btnQuit.setToolTipText("Quit");
		add(btnQuit);
		botonQuit();
	}
	
	/**
	 * metodo encargado de la ejecucion del boton step
	 */
	private void botonStep(){
		
		btnStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					s_ctrl.performStep();

			}
		});
	}
	
	/**
	 * metodo encargado de la ejecucion del boton run
	 */
	private void botonRun(){
		
		btnRun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					s_ctrl.performRun();

			}
		});
	}
	
	/**
	 * metodo encargado de la ejecucion del boton pause
	 */
	private void botonPause(){

		btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				s_ctrl.performPause();//modificar para que sea run
			}
		});		
	}
	
	/**
	 * metodo encargado de la ejecucion del boton quit
	 */
	private void botonQuit(){
		
		btnQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int opcion = JOptionPane.showConfirmDialog(null, "Realmente deseas salir?", "Aviso", JOptionPane.YES_NO_OPTION); 
				if (opcion == 0) {
					System.exit(0);
				}
			}
		});
	}
	
	/**
	 * Metodo encargado de asignar el icono al boton corresondiente
	 * @param path la ruta
	 * @return la imagen o null si no existe
	 */
	private static ImageIcon createImageIcon(String path){
		 java.net.URL imgURL = MainWindow.class.getResource(path);
		 if (imgURL != null) return new ImageIcon(imgURL);
		 	return null;
	}
}