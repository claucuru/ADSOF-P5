package p5.ejercicio1;
import java.util.*;

public class Data<T> extends LinkedHashMap<String, T>{
	public Data(T op1, T op2) {
		this.put("op1", op1);
		this.put("op2", op2);
	}
}
