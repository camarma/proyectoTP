package tp.pr5.mv.View.gui;
  
import java.awt.Dimension;
import java.awt.Font;  

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.ProgramMV;
/**
 * Clase encargada de pintar el panel del programa en la ventana
 * @author George y Alberto
 *
 */
public class ProgramPanel extends JPanel{
      
    private JScrollPane scroll;
    private JList<String> programJlist;
    private DefaultListModel<String> modeloLista;
    private static final long serialVersionUID = 1L;
    private Instruction[] lisInstructions;
      
	/**
	 * Constructora encargada de llamar al metodo build
	 */
    ProgramPanel() {
    	
    	build();
    }
    
	/**
	 * metodo encargado de construir el panel
	 */
    private void build(){
        
    	setBorder(BorderFactory.createTitledBorder("Programa"));
        this.modeloLista = new DefaultListModel<String>();
        this.programJlist = new JList<String>(modeloLista);
        this.scroll = new JScrollPane(programJlist);
        this.scroll.setPreferredSize(new Dimension(260,800));
        this.programJlist.setFont(new Font("Courier",Font.PLAIN,16));
        this.programJlist.setMaximumSize(new Dimension(50,50));
        
        add(scroll);
    }
    
    /**
     * metodo encargado de recuperar la lista de instrucciones
     * @param instructions  la instruciones
     * @param cp el contador
     */
    public void refresh(ProgramMV.Data instructions, int cp){
    	lisInstructions = instructions.getInstructions();
    	//refreshPanel(cp);
    	SwingUtilities.invokeLater(new RefreshProgram(cp));
    }
    /**
     * Metodo encargado de refrescar la vista de las instrucciones
     */
    public void refreshPanel(int cp){
    	String programLista[] = new String[lisInstructions.length];
    	modeloLista.clear();
        for (int i=0;i<lisInstructions.length;i++){
        	if(cp == i){
        		programLista[i] = "*  " + i + "- " + lisInstructions[i].toString();
        	}
        	else{
        		programLista[i] = "   " + i + "- " + lisInstructions[i].toString();
        	}

        	modeloLista.addElement(programLista[i]+"\n");
        }
     }
    
	/**
	 * Clase encargada de refrescar el panel del programa.
	 * @author George y Alberto
	 *
	 */
	class RefreshProgram implements Runnable{
		int cp;
		public RefreshProgram(int cp) {
			this.cp = cp;
			refreshPanel(cp);
		}
		
		@Override
		public void run() {
			refreshPanel(cp);
		}
	}
} 