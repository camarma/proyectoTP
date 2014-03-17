package tp.pr1.mv;
/**
 * Clase encargada de analizar la entrada del usuario y generar la instrucción correspondiente.
 * @author George y Alberto
 *
 */

public class InstructionParser {
	
	/**
	 * Constructora por defecto
	 */
	public InstructionParser(){		
	
	}

	/**
	 * Analiza la orden introducida por el usuario y genera la instrucción correspondiente.
	 * @param line -es la orden introducida por el usuario.
	 * @return 
	 * Devuelve la instrucción correspondiente o null en caso de error al analizar la orden en el parámetro line.
	 */
	public Instruction parse(String line){
		
		line = line.trim();
		String [] parts = line.split(" +");
		Instruction instruccion = null;

		if(line!=""){
			if (parts.length==1){
				if (parts[0].toUpperCase().equals(InstructionType.ADD.toString())){
					instruccion = new Instruction(InstructionType.ADD);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.SUB.toString())){
					instruccion = new Instruction(InstructionType.SUB);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.MUL.toString())){
					instruccion = new Instruction(InstructionType.MUL);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.DIV.toString())){
					instruccion = new Instruction(InstructionType.DIV);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.DUP.toString())){
					instruccion = new Instruction(InstructionType.DUP);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.FLIP.toString())){
					instruccion = new Instruction(InstructionType.FLIP);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.HALT.toString())){
					instruccion = new Instruction(InstructionType.HALT);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.OUT.toString())){
					instruccion = new Instruction(InstructionType.OUT);
				}
				else if (parts[0].toUpperCase().equals(InstructionType.POP.toString())){
					instruccion = new Instruction(InstructionType.POP);
				}
				else{
					instruccion = null;
				}
			}
			else if(parts.length==2){
				if (Integer.parseInt(parts[1]) > -1 ){
					if (parts[0].toUpperCase().equals(InstructionType.LOAD.toString())){
						instruccion = new Instruction(InstructionType.LOAD,Integer.parseInt(parts[1]));
					}		
					else if (parts[0].toUpperCase().equals(InstructionType.PUSH.toString())){
						instruccion = new Instruction(InstructionType.PUSH,Integer.parseInt(parts[1]));
					}
					else if (parts[0].toUpperCase().equals(InstructionType.STORE.toString())){
						instruccion = new Instruction(InstructionType.STORE,Integer.parseInt(parts[1]));
					}
					else{
						instruccion = null;
					}
				}
				else{
					instruccion = null;
				}
					
			}
			else{
				instruccion = null;
			}
			return instruccion;
		}	
		else {
			instruccion = null;
			return instruccion;
		}
	}
}