package p5.ejercicio1;

import java.util.*;
import java.util.function.*;
import p5.ejercicio3.*;
import p5.ejercicio5.*;



public class StateGraph<T extends Data<?>> implements Graph<T>{
	private String name, action; 
	private Node<T> initial_node;
	private List<Node<T>> nodes;
	private Node<T> final_node;
	
	
	public StateGraph(String name, String action) {
		this.name = name; 
		this.action = action; 
		this.initial_node = null; 
		this.final_node = null; 
		this.nodes = new LinkedList<Node<T>>();
	}
	
	public String getName() {
		return this.name; 
	}
	
	public String getAction() {
		return this.action;
	}
	
	public Node<T> getInitialNode(){
		return this.initial_node;
	}
	
	public Node<T> getFinalNode(){
		return this.final_node;
	}
	
	public List<Node<T>> getNodes(){
		return this.nodes; 
	}
	
	public Node<T> findNode(String name){
		for(Node<T> n: this.nodes) {
			if(n.getName() == name) {
				return n; 
			}
		}
		return null; 
	}
	
	public Graph<T> addNode(String name, Consumer<T> action) {
		Node<T> n = new Node<T>(name, action);
		this.nodes.add(n);
		return this;
	}
	
	public <S extends Data<?>> NodeGraph<T, S> addWfNode(String name, StateGraph<S> wfNumeric) {
		NodeGraph<T, S> n = new NodeGraph<T, S>(name, wfNumeric);
		this.nodes.add(n);
		return n;
	}

	public Graph<T> setInitial(String name) {
		Node <T> n = this.findNode(name);
		if(n != null) {
			if(this.initial_node != null) {
				this.nodes.add(this.initial_node);
			}
			this.initial_node = n; 
			this.nodes.remove(n);
		}
		return this; 
	}

	public void setFinal(String name) {
		Node <T> n = this.findNode(name);
		if(n != null) {
			if(this.final_node != null) { 
				this.nodes.add(this.final_node);
			}
			this.final_node = n;
			this.nodes.remove(n);
		}
	}

	public StateGraph<T> addEdge(String name_node1, String name_node2) {
		Node<T> node1 = this.findNode(name_node1);
		Node<T> node2 = this.findNode(name_node2);
		if(node1 != null && node2 != null) {
			this.nodes.get(this.nodes.indexOf(node1)).addEdges(this.nodes.get(this.nodes.indexOf(node2)));
		}
		return this;
	}
	
	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action) {
		this.addEdge(node_name1, node_name2);
		Node<T> node1 = this.findNode(node_name1);
		Node<T> node2 = this.findNode(node_name2);
		if(node1 != null && node2 != null) {
			this.nodes.get(this.nodes.indexOf(node1)).setEdgeAction(action);
		}
		return this; 
	}

	/*for empezando por el inicial y cogemos el hijo y vuelve arriba hasta que el hijo o sea null o sea el final*/
	public T run(T input, boolean debug) {
		String message_debug = "";
		int step = 1; 
		message_debug += "Step " + step +" (" + this.name + ") - input: " + input + "\n";
		Node<T> node = null; 
		
		if(this.initial_node != null) {
			this.initial_node.executeAction(input);
			step++;
			message_debug += "Step " + step +" (" + this.name + ") - "+ this.initial_node.getName() +" executed: " + input + "\n";
			node = this.initial_node;
		}else {
			node = this.nodes.getFirst();
		}

		while(node != this.final_node && node != null) {
			List <Node <T>> nodes = new ArrayList<>(node.getEdges());
			if(nodes.isEmpty()) {
				break; 
			}
			if(node.getConditionalAction()!=null) {
				if(node.getConditionalAction().test(input)) {
					nodes.getFirst().executeAction(input);
					step++;
					message_debug += "Step " + step +" (" + this.name + ") - "+ nodes.getFirst().getName() +" executed: " + input + "\n";
				}
				if (node.equals(nodes.getFirst())){
					break;
				}
				node = nodes.getFirst();
			}else {
				nodes.getFirst().executeAction(input);
				step++;
				message_debug += "Step " + step +" (" + this.name + ") - "+ nodes.getFirst().getName() +" executed: " + input + "\n";
				node = nodes.getFirst();
			}
		}
		
		message_debug = message_debug.substring(0, message_debug.length() - 1);
		if (debug == true) {
			System.out.println(message_debug);
		}
		return input;
	}
	
	public String history() {
		return "";
	}
	
	@Override
	public String toString() {
		String buffer = "";
		int counter = this.nodes.size() -1;
		if(this.initial_node != null) {
			counter++; 
		}
		if(this.final_node != null) {
			counter ++; 
		}
		buffer += "Workflow '" + this.name + "' (" + this.action + "):\n";
		buffer += "- Nodes: {";
		if(this.initial_node != null) {
			buffer += this.initial_node + "(" + counter + " output nodes)" + ", "; 
			counter --;
		}
		for(Node<T> n:  this.nodes) {
			buffer += n + "(" + counter + " output nodes)" + ", "; 
			counter --; 
		}
		if(this.final_node != null) {
			buffer += this.final_node + "(" + counter + " output nodes)" + ", "; 
			counter --;
		}
		buffer = buffer.substring(0, buffer.length() - 2);
		buffer += "}\n";
		String name = "None";
		if(this.initial_node != null) {
			name = this.initial_node.getName();
		}
		buffer += "- Initial: " + name + "\n";
		name = "None";
		if(this.final_node != null) {
			name = this.final_node.getName();
		}
		buffer += "- Final: " + name + "\n";
		buffer = buffer.substring(0, buffer.length() - 1);
		return buffer; 
	}
}
