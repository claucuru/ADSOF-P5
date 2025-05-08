package p5.ejercicio1;
import java.util.*;
/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * 
 * Clase que extiende de LinkedHashMap
 * Guarda operadores
 */
public class Data<T> extends LinkedHashMap<String, T>{
	/**
	 * Constructor de la clase
	 * @param T op1
	 * @param T op2
	 */
	public Data(T op1, T op2) {
		this.put("op1", op1);
		this.put("op2", op2);
	}
}
