package p5.ejercicio5;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

public interface Graph<T extends Data<?>> {
	
	Graph<T> addNode(String name, Consumer<T> action);
	
	Graph<T> setInitial(String name);
	
	Node<T> getInitialNode();
	
	Node<T> getFinalNode();
	
	String getName();
	
	List<Node<T>> getNodes();

	Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action);
	
	String history();
	
	T run(T input, boolean debug);
	
	String toString();
	
}
