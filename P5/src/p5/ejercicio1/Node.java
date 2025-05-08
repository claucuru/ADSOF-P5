package p5.ejercicio1;

import java.util.*;
import java.util.function.*;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * 
 * Clase que define un nodo
 */
public class Node<T> {
	private String name; 
	private Consumer<T> action;
	private List<Node<T>> edges;
	private Predicate<T> conditionalAction;

	/**
	 * @param name nombre del nodo
	 * @param action acción del nodo
	 */
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

	/**
	 * Funcion que añade un nodo hijo a un nodo
	 * @param edge nodo
	 */
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

	/**
	 * Funcion que ejecuta la accion de un nodo
	 * @param input accion de un nodo
	 */
	public void executeAction(T input) {
		this.action.accept(input);
	}
}
