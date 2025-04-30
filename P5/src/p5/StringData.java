package p5;

import java.util.*;
import java.util.function.*;

public class StringData extends Data<String>{
	public StringData(String op1, String op2) {
		super(op1, op2);
		super.put("result", "");
	}

	public StringData withInjector(Consumer<StringData> action) {
		// TODO Auto-generated method stub
		return this;
	}
	
	public StringData withExtractor(BiConsumer<NumericData, StringData> action){
		return null; 
	}
	
	public NumericData toNumericData() {
		// TODO Auto-generated method stub
		return null;
	}

	public StringData setTimes(Integer integer) {
		// TODO Auto-generated method stub
		return null;
	}

	public StringData replicate() {
		// TODO Auto-generated method stub
		return null;
	}

	public int times() {
		// TODO Auto-generated method stub
		return 0;
	}
}
