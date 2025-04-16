package p5;

import java.util.*;
import java.util.function.Consumer;

public class StateGraph<T> {
	private String name, action; 
	private Node<T> initial_node;
	private List<Node<T>> nodes;
	private Node<T> final_node;
	
	public StateGraph(String name, String action) {
		this.name = name; 
		this.action = action; 
		this.nodes = new LinkedList<Node<T>>();
	}
	
	public Node<T> findNode(String name){
		for(Node<T> n: this.nodes) {
			if(n.getName() == name) {
				return n; 
			}
		}
		return null; 
	}
	
	public StateGraph<T> addNode(String name, Consumer<T> action) {
		Node<T> n = new Node<T>(name, action);
		this.nodes.add(n);
		return this;
	}

	public void setInitial(String name) {
		Node <T> n = this.findNode(name);
		if(n != null) {
			this.initial_node = n; 
		}
	}

	public void setFinal(String name) {
		Node <T> n = this.findNode(name);
		if(n != null) {
			this.final_node = n; 
		}
	}

	public void addEdge(String name_node1, String name_node2) {
		Node<T> node1 = this.findNode(name_node1);
		Node<T> node2 = this.findNode(name_node2);
		if(node1 != null && node2 != null) {
			this.nodes.get(this.nodes.indexOf(node1)).addEdges(this.nodes.get(this.nodes.indexOf(node2)));
			this.nodes.get(this.nodes.indexOf(node2)).addEdges(this.nodes.get(this.nodes.indexOf(node1)));
		}
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
		for(Node<T> n:  this.nodes) {
			buffer += n.getName() + "=" + "Node " + n.getName() + "("; /*TODO: ver de donde sale lo del output node, creo que son los nodos que van detrás pero ni idea*/
		}
		buffer += "}\n";
		buffer += "- Initial: " + this.initial_node.getName() + "\n";
		buffer += "- Final: " + this.final_node.getName() + "\n";
		return buffer; 
	}

}
