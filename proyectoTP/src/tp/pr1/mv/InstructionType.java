package tp.pr1.mv;
/**
 * Enumerado definiendo los tipos de instrucción.
 * @author George y Alberto
 *
 */

public enum InstructionType {
	NONE,
	PUSH, 	//Apilar
	POP,	//Desapilar
	ADD,	//Sumar
	MUL,	//Multiplicar
	SUB,	//Restar
	DIV,	//Dividir
	OUT,	//Mostrar por pantalla la cima de la pila
	STORE,	//Lleva la cima de la pila a memoria
	LOAD,	//Indicar direccion de la pila y la cola en la cima de la pila
	HALT,	//Parar
	FLIP,	//Invierte la posicion de los dos valores mas altos de la pila
	DUP		//Duplica el valor de la cima

}
