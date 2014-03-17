package tp.pr1.mv;
/**
 * Clase encargada de reprentar una instrucción.
 * @author George y Alberto
 *
 */
public class Instruction {
	private int param; //Aqui nunca se inicializan las variables, la maquina virtual de java les dara el valor que quiera hasta que no generes el objeto a traves de la constructora, enconces sera cuando se inicialiecen
	private InstructionType type;
	
	/**
	 * Constructora por defecto .
	 */
	public Instruction(){ //Como tenemos tres cosntructoras y cada una va ir inicalizando a la otra
		this(InstructionType.NONE);
	}
	
	/**
	 * Constructora indicando el tipo de instrucción. Esta constructora se utilizará para instrucciones sin parámetros.
	 * @param type -es el tipo de la instrucción.
	 */
	public Instruction(InstructionType type){
		this(type,-1);
	}
	
	/**
	 * Constructora indicando el tipo de instrucción y su parámetro. Esta constructora se utilizará para instrucciones con un parámetro.
	 * @param type -es el tipo de la instrucción.
	 * @param param -es el parámetro de la instrucción.
	 */
	public Instruction(InstructionType type, int param){
		this.type = type;
		this.param = param;
	}
	
	/**
	 * Devuelve el tipo de la instrucción.
	 * @return
	 * Devuelve el tipo de la instrucción.
	 */
	public InstructionType getType(){
		return this.type;
	}
	
	/**
	 * Devuelve el parámetro de la instrucción.
	 * @return
	 * Devuelve el parámetro de la instrucción.
	 */
	public int getParam (){
		return this.param;
	}

}
