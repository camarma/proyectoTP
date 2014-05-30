package tp.pr5.mv.Model;

import java.util.Arrays;

import tp.pr5.mv.Model.exceptions.stack.StackException;



/**
 * Clase encargada de representar la pila de operandos.
 * @author George y Alberto
 *
 */

public class OperandStack extends Observable<OperandStack.Observer>{
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
	 * Apila un valor.
	 * y actualiza la lista de observers
	 * @param value -es el valor a apilar.
	 * @return
	 * True si se ha podidio realizar el apilado o false si no se ha realizado porque la pila estaba llena.
	 */
	public void push (int value) throws StackException{
		cima = cima + 1;
		if(cima < stack.length){
			stack[cima]=value;
			for (Observer o : observers){
				o.onStackChange(getData());
			}
		}else{
			cima = cima - 1;
			throw new StackException("La pila esta llena");
		}
	}
	
	/**
	 * Desapila el valor de la cima.
	 * y actualiza la lista de observers.
	 */
	public void pop (){
		if (cima >= 0){
			 cima = cima - 1;
		}
		for (Observer o : observers){
			o.onStackChange(getData());
		}
	}
	
	/**
	 * Devuelve el valor de la cima. 
	 * @return
	 * Devuelve el valor de la cima.
	 */
	public int top () throws StackException{
		if(cima==-1){
			throw new StackException("Faltan operandos en la pila.");
		}else{
			int resultado = 0; 
			resultado = stack[cima];
			return  resultado;
		}
	}
	public int getCima(){
		return this.cima;
	}
	
	/**
	 * Interfaz implementado por los observadores de la clase
	 * para ser notificados cuando ocurre algún evento en la pila.
	 */
	public interface Observer {
		
		/**
		 * Se invoca cuando ha habido un cambio en la memoria
		 */
		void onStackChange(OperandStack.Data data);
	}
	
	/**
	 * Clase utilizada para enviar los datos a la vista
	 */
	public class Data{
		private int array[]=null;
		Data(int[] stack) {
			super();
			this.array = stack;
		}
		public int[] getStack(){
			return array;
		}
	}

	public OperandStack.Data getData(){
		Data realStack = new Data(Arrays.copyOf(stack, cima+1));
		return realStack;
	}

}
