package tp.pr5.mv.View.gui;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tp.pr5.mv.Model.CPU;
import tp.pr5.mv.Model.ControlUnit;
import tp.pr5.mv.Model.ControlUnit.Data;
import tp.pr5.mv.Model.Instruction;
import tp.pr5.mv.Model.ProgramMV;
import tp.pr5.mv.Controller.SwingController;
import tp.pr5.mv.Model.IO.SwingOutMethod;

/**
 * Clase encargada de pintar el panel general de la ventana
 * 
 * @author George y Alberto
 * 
 */
public class MainWindow extends JFrame implements CPU.Observer,
		ControlUnit.Observer {

	private static final long serialVersionUID = 1L;
	private SwingController s_ctrl;
	private StackPanel sp;
	private MemoryPanel mp;
	private InputPanel ip;
	private OutputPanel op;
	private ProgramPanel pp;
	private ToolbarPanel tp;
	private StatePanel st;
	private int cp;
	private ErrorDialog re = new ErrorDialog();

	/**
	 * Constructora donde se definen los paneles y su distribucion en la ventana
	 * 
	 * @param cpu
	 *            recibe la cpu cargada con el programa
	 */
	public MainWindow(SwingController ctrl) {

		// Titulo del la ventana
		super("Maquina Virtual");
		this.s_ctrl = ctrl;
		this.sp = new StackPanel(s_ctrl);
		this.mp = new MemoryPanel(s_ctrl);
		this.pp = new ProgramPanel();
		this.tp = new ToolbarPanel(s_ctrl);
		this.st = new StatePanel();

		this.ip = new InputPanel();
		this.op = new OutputPanel();

		this.re = new ErrorDialog();

		build();

		// Panel de Pila
		this.s_ctrl.addStackObserver(sp);
		// Panel de Programa,In y Out
		this.s_ctrl.addControlUnitObserver(this);
		this.s_ctrl.addCPUObserver(this);
		// Panel de Memoria
		this.s_ctrl.addMemoryObserver(mp);
		// Panel de Estado
		this.s_ctrl.addStackObserver(st);
		this.s_ctrl.addMemoryObserver(st);
		this.s_ctrl.addControlUnitObserver(st);
		this.s_ctrl.addCPUObserver(st);

	}

	/**
	 * metodo encargado de construir el panel
	 */
	private void build() {

		// Definimos el tipo de estructura general de nuestra ventana
		setLayout(new BorderLayout());
		// Dividimos la ventana en secciones
		add(pp, BorderLayout.WEST);
		add(tp, BorderLayout.NORTH);
		add(st, BorderLayout.SOUTH);

		// Panel para todo lo que va en el centro, Memoria, Pila, Entrada y
		// Salida.
		JPanel centerPanel = new JPanel(new GridLayout(2, 0));

		add(centerPanel, BorderLayout.CENTER);

		// Dividimos el panel central en dos paneles uno para la pila y la
		// memoria y otro para la entrada y salida.
		JPanel centerPanelNorth = new JPanel(new GridLayout(1, 0));
		JPanel centerPanelSouth = new JPanel(new GridLayout(2, 0));

		// Paneles para pila y memoria
		JPanel stackPanel = new JPanel(new BorderLayout());
		JPanel memoryPanel = new JPanel(new BorderLayout());

		// Al norte pila y memoria
		centerPanel.add(centerPanelNorth);
		sp = new StackPanel(s_ctrl);
		mp = new MemoryPanel(s_ctrl);

		centerPanelNorth.add(stackPanel);
		centerPanelNorth.add(memoryPanel);

		stackPanel.add(sp);
		memoryPanel.add(mp);

		// Al sur entrada y salida
		centerPanel.add(centerPanelSouth);
		ip = new InputPanel();
		op = new OutputPanel();

		centerPanelSouth.add(ip);
		centerPanelSouth.add(op);
		setVisible(true);
		setSize(1400, 740);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * metodo encargado de inicializar el out
	 * 
	 * @param out
	 */
	public void initOUT(SwingOutMethod out) {

		out.setOutput(op);
	}

	/**
	 * metodo encargado de cargar y refrescar el panel del programa
	 */
	@Override
	public void onStart(ProgramMV.Data instructions) {
		// TODO Auto-generated method stub
		pp.refresh(instructions, cp);
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
	public void onInstructionEnd(Instruction instr,
			tp.pr5.mv.Model.Memory.Data mem,
			tp.pr5.mv.Model.OperandStack.Data ops,
			tp.pr5.mv.Model.ControlUnit.Data pc) {
		// TODO Auto-generated method stub

	}

	/**
	 * metodo encargado de llamar al dialogo para pintar el error por ventana
	 * 
	 * @param error
	 *            el tipo de error
	 */
	@Override
	public void onError(Exception error) {
		// TODO Auto-generated method stub
		String mensaje = error.getMessage();
		this.re.reportError(mensaje);
	}

	/**
	 * metodo encargado de recuperar el contador de programa
	 */
	@Override
	public void onCPchange(Data cpData) {
		// TODO Auto-generated method stub
		cp = cpData.getCp();
		pp.refreshPanel(cp);
	}

	/**
	 * metodo encargado desabilitar los botones de la ventana cuando la maquina
	 * esta parada
	 */
	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		sp.disable();
		mp.disable();
	}
}