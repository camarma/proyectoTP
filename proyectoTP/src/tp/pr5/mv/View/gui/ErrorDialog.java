package tp.pr5.mv.View.gui;
 
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Clase encargada de generar un ventana para mostrar errores y validar operandos
 * @author George y Alberto
 *
 */
public class ErrorDialog {

	public ErrorDialog(){
		
	}
	
	/**
	 * Método que genera la ventana de errores
	 * @param msg devuelve el mensaje
	 */
	void reportError(String msg){ 
		JDialog dialogo = new JDialog();
		Image img = new ImageIcon(MainWindow.class.getResource("img/warning2.png")).getImage();
		dialogo.setIconImage(img);		
		String [] parts = msg.split(":");
		int i=0;
		while(parts[i].contains("tp")){
			i++;
		}
		msg = "";
		for(; i<parts.length;i++){
			msg+=parts[i]+" ";
		}
		
		JLabel redLabel = new JLabel(msg); 

		redLabel.setHorizontalAlignment(JLabel.CENTER);

		dialogo.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		redLabel.setFont(new Font("Courier", Font.BOLD, 15));
		
		
		JLabel label = new JLabel();  
        label.setIcon(new ImageIcon(MainWindow.class.getResource("img/warning2.png")));
        dialogo.add(label);
        dialogo.add(redLabel); 
		dialogo.setTitle("Error en la maquina Virtual");
		dialogo.setModal(true);
        dialogo.setSize(610, 200);
        dialogo.setLocationRelativeTo(label);
        dialogo.setVisible(true);
	}
	
	/**
	 * Método encargado de validar los operandos
	 * @param operando el valor del operando de entrada
	 * @return la validacion
	 */
	boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}
}
