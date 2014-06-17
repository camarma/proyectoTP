package tp.pr5.mv.Model;
 
import java.util.Vector;

/**
 * Todas las clases que puedan ser observadas deben heredar de esta clase.
 * Añade una lista de observadores protegida y métodos públicos para añadir
 * y eliminar observadores.
 */
public abstract class Observable<I> {

	protected Vector<I> observers = new Vector<I>();
	/**
	 * Añade un nuevo observador a la lista; si el observador ya
	 * existía, la operación no tiene efecto.
	 * 
	 * @param observer Nuevo observador.
	 */
	public void addObserver(I observer) {
		this.observers.add(observer);
	}
	
	/**
	 * Elimina de la lista el observador indicado; si el observador
	 * no estaba regitrado, la operación no tiene efecto.
	 * @param observer Observador a eliminar.
	 */
	public void removeObserver(I observer) {
		this.observers.remove(observer);
	}
}
