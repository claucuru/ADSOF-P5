package p5.ejercicio5;

import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.StateGraph;

public class StateGraphLogger<T extends Data<?>> extends Decorator<T> { //TODO: hereda de StateGraph???
	private String file; 

	
	public StateGraphLogger(Graph<T> sg, String file) {
		super(sg);
		this.file = file; 
	}

	public String getFile() {
		return file;
	}

	@Override
	public Graph<T> addNode(String name, Consumer<T> action) {
		return super.addNode(name, action);
	}

	@Override
	public Graph<T> setInitial(String name) {
		return super.setInitial(name);
	}

	@Override
	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action) {
		return super.addConditionalEdge(node_name1, node_name2, action);
	}

	@Override
	public String history() {
		return super.history();
	}

	@Override
	public T run(T input, boolean debug) {
		return super.run(input, debug);
	}
	
	

}
