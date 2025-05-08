package p5.ejercicio5;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

public interface Graph<T extends Data<?>> {
	
	/**
	 * Funcion que añade un  nodo a un grafo
	 * @param name nombre del nodo
	 * @param accion del nodo
	 */
	Graph<T> addNode(String name, Consumer<T> action);
	
	Graph<T> setInitial(String name);

	Node<T> getInitialNode();
	
	Node<T> getFinalNode();
	
	String getName();
	
	List<Node<T>> getNodes();

	/**
	 * Funcion que relaciona dos nodos en un edge con una condicion. Para ello se añade a una lista del primer nodo
	 * @param name_node1 
	 * @param name_node2
	 * @param action 
	 * @return el StateGraph
	 */
	Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action);

	/**
	 * Funcion que devuelve el historial de un grafo
	 * @return buffer con el historial
	 */
	String history();

	/**
	 * Funcion que ejecuta todos los nodos de un grafo
	 * @param input grafo
	 * @param debug
	 */
	T run(T input, boolean debug);
	
	String toString();
	
}
