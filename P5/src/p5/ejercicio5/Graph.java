package p5.ejercicio5;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.StateGraph;

public interface Graph<T extends Data<?>> {
	
	public Graph<T> addNode(String name, Consumer<T> action);
	
	public Graph<T> setInitial(String name);

	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action);
	
	public String history();
	
	public T run(T input, boolean debug);
	
}
