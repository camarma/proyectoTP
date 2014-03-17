package tp.pr2.mv;
import tp.pr2.mv.instructions.arithmetic.Add;
import tp.pr2.mv.instructions.arithmetic.Div;
import tp.pr2.mv.instructions.arithmetic.Mult;
import tp.pr2.mv.instructions.arithmetic.Neg;
import tp.pr2.mv.instructions.arithmetic.Sub;
import tp.pr2.mv.instructions.comparison.EQ;
import tp.pr2.mv.instructions.comparison.GT;
import tp.pr2.mv.instructions.comparison.LE;
import tp.pr2.mv.instructions.comparison.LT;
import tp.pr2.mv.instructions.io.Out;
import tp.pr2.mv.instructions.jump.BF;
import tp.pr2.mv.instructions.jump.BT;
import tp.pr2.mv.instructions.jump.Jump;
import tp.pr2.mv.instructions.logic.And;
import tp.pr2.mv.instructions.logic.Not;
import tp.pr2.mv.instructions.logic.Or;
import tp.pr2.mv.instructions.memory.Load;
import tp.pr2.mv.instructions.memory.Store;
import tp.pr2.mv.instructions.misc.Halt;
import tp.pr2.mv.instructions.stack.Dup;
import tp.pr2.mv.instructions.stack.Flip;
import tp.pr2.mv.instructions.stack.Pop;
import tp.pr2.mv.instructions.stack.Push;
/**
 * Clase encargada de analizar la instrucción introducida por el usuario y generar la instrucción correspondiente.
 * @author George y Alberto
 *
 */

public class InstructionParser {
	
	/**
	 * Constructora por defecto.
	 * Guarda internamente un array con todas las instrucciones disponibles.
	 */
	public InstructionParser(){		

	}
	
	static Instruction [] instrucciones = {	new Add(), new Sub(), new Div(), new Mult(),new Neg(),	//Arithmetic
											new EQ(), new GT(), new LE(), new LT(),					//Comparision
											new Out(),												//Io
											new BT(), new BF(), new Jump(),							//Jump
											new And(), new Or(), new Not(),							//Logic
											new Load(), new Store(),								//Memory
											new Halt(),												//Misc
											new Push(), new Pop(), new Dup(), new Flip()};			//Stack
	
	/**
	 * Analiza la orden introducida por el usuario y genera la instrucción correspondiente.
	 * @param line -es la orden introducida por el usuario.
	 * @return 
	 * Devuelve la instrucción correspondiente o null en caso de error al analizar la orden en el parámetro line.
	 */
	public static Instruction parse(String line){
		Instruction instruccion = null;
		int i;
		for (i=0; i < instrucciones.length; i++){
			instruccion =instrucciones[i].parse(line);
			if (instruccion!=null){
				return instruccion;
			}
		}
		return null;
	}	
}