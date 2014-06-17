package tp.pr5.mv.View.gui;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.Memory;
import tp.pr5.mv.Model.OperandStack;
import tp.pr5.mv.Model.OperandStack.Data;

/**
 * Clase encargada de pintar el panel de estado en la ventana
 * @author George y Alberto
 *
 */

public class StatePanel extends JPanel implements OperandStack.Observer, ControlUnit.Observer ,  Memory.Observer, CPU.Observer {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private int i; 
	private JLabel lblParada;
	private JLabel lblNumIns;
	private JLabel lblMem;
	private JLabel lblPila;
	private JCheckBox chMem;
	private JCheckBox chPila;
	
	/**
	 * Constructora encargada de llamar al metodo build
	 */
	public StatePanel(){
		
		build();
	}
	
	/**
	 * metodo encargado de construir el panel
	 */
	private void build(){
		
		panel = new JPanel();
		lblParada = new JLabel("Máquina Parada");
		lblParada.setFont(new Font("Courier",Font.PLAIN,20));
		lblParada.setForeground(Color.red);
		lblParada.setVisible(false);
		lblNumIns = new JLabel("Num. instrucciones ejecutadas: " + i);
		lblNumIns.setFont(new Font("Courier",Font.PLAIN,20));
		chMem = new JCheckBox();
		lblMem = new JLabel("Memoria modificada");
		lblMem.setFont(new Font("Courier",Font.PLAIN,20));
		chPila = new JCheckBox();
		lblPila = new JLabel("Pila modificada");
		lblPila.setFont(new Font("Courier",Font.PLAIN,20));		
		panel.add(lblParada);
		panel.add(lblNumIns);
		panel.add(chMem);
		panel.add(lblMem);
		panel.add(chPila);
		panel.add(lblPila);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * habilita el checkbox en caso de cambiar los datos de la pila
	 */
	@Override
	public void onStackChange(Data data) {	
		// TODO Auto-generated method stub
		chPila.setSelected(true);
	}

	/**
	 * no hace nada
	 */
	@Override
	public void onStart(tp.pr5.mv.Model.ProgramMV.Data instructions) {
		// TODO Auto-generated method stub
	}

	/**
	 * no hace nada
	 */
	@Override
	public void onInstructionStart(Instruction instr) {
		// TODO Auto-generated method stub
	}

	/**
	 * no hace nada
	 */
	@Override
	public void onInstructionEnd(Instruction instr, tp.pr5.mv.Model.Memory.Data mem,
		Data ops, tp.pr5.mv.Model.ControlUnit.Data pc) {
		// TODO Auto-generated method stub
		lblNumIns.setText("Num. instrucciones ejecutadas: " + ++i);
	}

	/**
	 * no hace nada
	 */
	@Override
	public void onError(Exception error) {
		// TODO Auto-generated method stub
	}

	/**
	 * habilita el checkbox en caso de cambiar los datos de la memoria
	 */
	@Override
	public void onMemoryChange(tp.pr5.mv.Model.Memory.Data data) {
		// TODO Auto-generated method stub
		chMem.setSelected(true);
	}

	/**
	 * no hace nada
	 */
	@Override
	public void onCPchange(tp.pr5.mv.Model.ControlUnit.Data cpData) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * deshabilita los cheakboxes de la pila y memoria y muestra k la maquina esta parada
	 */
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		chMem.setSelected(false);
		chPila.setSelected(false);
		lblParada.setVisible(true);	
	}
}
