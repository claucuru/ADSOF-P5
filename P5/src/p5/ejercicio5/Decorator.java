package p5.ejercicio5;

import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;

public abstract class Decorator<T extends Data<?>> implements Graph<T> {
	Graph<T> graph;
	
	public Decorator(Graph<T> graph) {
		this.graph = graph;
	}
	
	public Graph<T> getGraph() {
		return this.graph; 
	}
	
	public Graph<T> addNode(String name, Consumer<T> action){
		return this.graph.addNode(name, action);
	}
	
	public Graph<T> setInitial(String name){
		return this.graph.setInitial(name);
	}

	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action){
		return this.graph.addConditionalEdge(node_name1, node_name2, action);
	}
	
	public String history() {
		return this.graph.history();
	}
	
	public T run(T input, boolean debug) {
		return this.graph.run(input, debug);
	}
	
}
