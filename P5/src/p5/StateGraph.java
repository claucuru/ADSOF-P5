package p5;

import java.util.*;
import java.util.function.Consumer;

public class StateGraph<T> {
	private String name, action; 
	private Map<String, Consumer<T>> nodes;
	private String initial_node_name; 
	private Consumer<T> initial_node_action; 
	private Map<String, Consumer<T>> final_nodes;
	
	public StateGraph(String name, String action) {
		this.name = name; 
		this.action = action; 
		this.nodes = new HashMap<String, Consumer<T>>();
		this.final_nodes = new HashMap<String, Consumer<T>>();
	}
	
	public StateGraph<T> addNode(String name, Consumer<T> action) {
		this.nodes.put(name, action);
		return this;
	}

	public void setInitial(String name) {
		Consumer<T> action = this.nodes.get(name);
		if (action != null) {
			this.initial_node_action = action;
			this.initial_node_name = name; 
		}
	}

	public void setFinal(String name) {
		Consumer<T> action = this.nodes.get(name);
		if (action != null) {
			this.final_nodes.put(name, action);
		}
	}

	public void addEdge(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public NumericData run(NumericData input, boolean debug) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		String buffer = "";
		buffer += "Workflow '" + this.name + "' (" + this.action + "):\n";
		buffer += "- Nodes: {";
		for(Map.Entry<String, Consumer<T>> ns: this.nodes.entrySet()) {
			buffer += ns.getKey() + "=" + "Node " + ns.getKey() + "("; /*TODO: ver de donde sale lo del output node, creo que son los nodos que van detrás pero ni idea*/
		}
		
		return buffer; 
	}

}
