package p5;

import java.util.*;
import java.util.function.Consumer;

public class Node<T> {
	private String name; 
	private Consumer<T> action; 
	private Set<Node<T>> edges;
	
	public Node(String name, Consumer<T> action) {
		this.name = name; 
		this.action = action; 
		this.edges = new HashSet<Node<T>>();
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
	
	public void addEdges(Node <T> edge) {
		this.edges.add(edge);
	}
}
