package p5.ejercicio5;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;

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
		T output = this.graph.run(input, debug);
		return output;
		
	}
	
	public Node<T> getInitialNode(){
		return this.graph.getInitialNode();
	}
	
	public Node<T> getFinalNode(){
		return this.graph.getFinalNode();
	}
	
	public String getName() {
		return this.graph.getName(); 
	}
	
	public List<Node<T>> getNodes(){
		return this.graph.getNodes(); 
	}
	
	public String toString() {
		return this.graph.toString();
	}
	
}
