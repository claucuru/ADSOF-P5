package p5.ejercicio1;
import java.util.*;

public class NumericData extends Data<Integer>{
	public NumericData(int op1, int op2) {
		super(op1, op2);
		super.put("result", 0);
	}

}
