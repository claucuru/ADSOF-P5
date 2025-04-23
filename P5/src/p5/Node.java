package p5;

import java.util.*;
import java.util.function.Consumer;

public class Node<T> {
	private String name; 
	private Consumer<T> action; 
	private Set<Node<T>> edges;
	private Consumer<T> conditionalAction;
	
	public Node(String name, Consumer<T> action) {
		this.name = name; 
		this.action = action; 
		this.edges = new LinkedHashSet<Node<T>>();
		this.conditionalAction = null; 
	}

	public String getName() {
		return name;
	}

	public Consumer<T> getAction() {
		return action;
	}

	public Set<Node<T>> getEdges() {
		return edges;
	}
	
	public Consumer<T> getConditionalAction() {
		return this.conditionalAction; 
	}
	
	public void addEdges(Node <T> edge) {
		this.edges.add(edge);
	}
	
	public void setEdgeAction(Consumer<T> action) {
		this.conditionalAction = action; 
		
	}
	
	/*TODO: esto es una idea para arreglar lo de los edges, no funciona porque empieza a imprimir desde abajo, solo está para el ej1*/
	
	/*public T run(T input, boolean debug, Node<T> final_node, int step) {
		String message_debug = "";
		
		this.getAction().accept(input);
		step++;
		message_debug += "Step " + step +" (" + this.name + ") - "+ this.getName() +" executed: " + input + "\n";
		
		for(Node<T> n: this.getEdges()) {
			n.run(input, debug, step+1);
		}
		
		if (debug == true) {
			System.out.println(message_debug);
		}
		return input; 
	}*/
	
	@Override
	public String toString() {
		String buffer = "";
		buffer += this.name + "=" + "Node " + this.name;
		return buffer; 
	}
}
