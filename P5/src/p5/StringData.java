package p5;

import java.util.*;

public class StringData extends Data<String>{
	public StringData(String op1, String op2) {
		super(op1, op2);
		super.put("result", "");
	}
}
