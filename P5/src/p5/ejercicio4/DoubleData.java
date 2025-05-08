package p5.ejercicio4;

import java.text.DecimalFormat;

import p5.ejercicio1.Data;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * 
 * Clase que extiende de Data
 * Guarda operadores de tipo double
 */
public class DoubleData extends Data<Double>{

	/**
	 * Constructo de la clase
	 * @param d
	 * param i
	 */
	public DoubleData(double d, double i) {
		super(d, i);
	}
	
	@Override
	public String toString() {
		String buffer = "";
		buffer = super.get("op1") + " (avg. = " + String.format("%.3f", super.get("op2"))+")";
		return buffer;
	}

}
