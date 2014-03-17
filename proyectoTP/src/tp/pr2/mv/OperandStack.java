package tp.pr2.mv;
/**
 * Clase encargada de representar la pila de operandos.
 * @author George y Alberto
 *
 */

public class OperandStack {
	private int cima=-1;
	private int [] stack;

	/**
	 * Constructora que crea la pila de operandos con la capacidad máxima indicada.
	 * @param max_size.
	 */
	public OperandStack (int max_size){
		stack = new int [max_size];
	}

	/**
	 * Indica si la pila está vacía.
	 * @return
	 * True si la pila está vacía.
	 */
	public boolean isEmpty(){
		boolean resultado;
		if(cima==-1){
			resultado = true;
		}
		else{
			resultado = false;
		}
		return resultado;
	}
	
	/**
	 * Apila un valor.
	 * @param value -es el valor a apilar.
	 * @return
	 * True si se ha podidio realizar el apilado o false si no se ha realizado porque la pila estaba llena.
	 */
	public boolean push (int value){
		cima = cima + 1;
		if(cima < stack.length){
			stack[cima]=value;
			return true;
		}else{
			cima = cima - 1;
			return false;
		}
	}
	
	/**
	 * Desapila el valor de la cima.
	 */
	public void pop (){
		if (isEmpty()==false){
			 cima = cima - 1;
		}
	}
	
	/**
	 * Devuelve el valor de la cima. 
	 * @return
	 * Devuelve el valor de la cima.
	 */
	public int top (){
		int resultado = 0; 
		resultado = stack[cima];
		return  resultado;
	}
	
	/**
	 * Método encargado de devolver una representación textual de la pila.
	 * @return
	 * Devuelve una cadena con la representación de la pila, en caso de estar vacía la cadena a mostrar sera <vacia>.
	 */
	public String toString(){
		int i = 0;
		String contenidoPila="";
		if(cima < 0){
			contenidoPila = "<vacia>";
		}
		else{
			for (i=0; cima>=i; i++){
				contenidoPila = contenidoPila + stack[i]+" ";
			}
		}
		return contenidoPila.trim();
	}
}
