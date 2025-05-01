package p5;
import java.util.*;

public class Data<T> extends LinkedHashMap<String, T>{
	public Data(T op1, T op2) {
		this.put("op1", op1);
		this.put("op2", op2);
	}
//	public Data<T> copy() {
//	    Data<T> newData = new Data<>(this.get("op1"), this.get("op2"));
//	    for (Map.Entry<String, T> entry : this.entrySet()) {
//	        newData.put(entry.getKey(), entry.getValue());
//	    }
//	    return newData;
//	}
}
