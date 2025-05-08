package p5.ejercicio1;
import java.util.*;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * Clase que define un dato de tipo numérico
 */
public class NumericData extends Data<Integer>{

	/**
	 * Constructor de la clase
	 * @param op1 un entero
	 * @param op2 un entero
	*/
	public NumericData(int op1, int op2) {
		super(op1, op2);
		super.put("result", 0);
	}

}
