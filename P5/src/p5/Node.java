package p5;

import java.util.*;
import java.util.function.*;

public class Node<T> {
	private String name; 
	private Consumer<T> action; 
	private List<Node<T>> edges;
	private Predicate<T> conditionalAction;
	
	public Node(String name, Consumer<T> action) {
		this.name = name; 
		this.action = action; 
		this.edges = new LinkedList<Node<T>>();
		this.conditionalAction = null; 
	}

	public String getName() {
		return name;
	}

	public Consumer<T> getAction() {
		return action;
	}

	public List<Node<T>> getEdges() {
		return edges;
	}
	
	public Predicate<T> getConditionalAction() {
		return this.conditionalAction; 
	}
	
	public void addEdges(Node <T> edge) {
		this.edges.add(edge);
	}
	
	public void setEdgeAction(Predicate<T> action) {
		this.conditionalAction = action; 
		
	}
	
	@Override
	public String toString() {
		String buffer = "";
		buffer += this.name + "=" + "Node " + this.name;
		return buffer; 
	}
}
