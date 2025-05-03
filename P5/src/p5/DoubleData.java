package p5;

import java.text.DecimalFormat;

public class DoubleData extends Data<Double>{

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
