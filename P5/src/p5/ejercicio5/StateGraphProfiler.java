package p5.ejercicio5;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;

import p5.ejercicio1.Data;

public class StateGraphProfiler<T extends Data<?>> extends Decorator<T>{
	private Decorator<T> sl;
	public Map<LocalDateTime, T> history;
	
	public StateGraphProfiler(Decorator<T> sl) {
		super(sl.getGraph());
		this.sl = sl; 
		this.history = new LinkedHashMap<LocalDateTime, T>();
	}

	@Override
	public Graph<T> addNode(String name, Consumer<T> action) {
		return sl.addNode(name, action);
	}

	@Override
	public Graph<T> setInitial(String name) {
		return sl.setInitial(name);
	}

	@Override
	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action) {
		return sl.addConditionalEdge(node_name1, node_name2, action);
	}

	@Override
	public String history() {
//		String buffer = "[";
//		for(Map.Entry<LocalDateTime, T> t: this.history.entrySet()) {
////			buffer += 
//		}
////		for(T t: this.history) {
////			buffer += t + ", ";
////		}
//		buffer = buffer.substring(0, buffer.length() - 1);
//		buffer += "]";
//		return buffer; 
		return sl.history();
	}

	@Override
	public T run(T input, boolean debug) {
		return sl.run(input, debug);
	}
	
}
