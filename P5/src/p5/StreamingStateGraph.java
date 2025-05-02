package p5;

import java.util.*;

public class StreamingStateGraph<T extends Data<?>> extends StateGraph<T>{
	public List<T> history; 
	
	
	public StreamingStateGraph(String name, String action) {
		super(name, action);
		this.history = new ArrayList<T>();
	}
	
	@Override
	public T run(T wfInput, boolean debug) {
		T t =  super.run(wfInput, debug);
		this.addHistory(t);
		return t; 
	}

	public String history() {
		String buffer = "";
		for(T t: this.history) {
			buffer = t.get("op1") + " (avg.= " + t.get("op2") + ")" + "\n";
		}
		return buffer; 
	}

	public List<T> getHistory() {
		if(this.history.isEmpty()) {
			return null; 
		}
		return this.history;
	}

	public void addHistory(T input) {
		this.history.add(input);
	}
}
