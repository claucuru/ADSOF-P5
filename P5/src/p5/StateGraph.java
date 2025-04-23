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
		this.initial_node = null; 
		this.final_node = null; 
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
			if(this.initial_node != null) { /*TODO: por ahora asumimos que se mete al final, a saber luego*/
				this.nodes.add(this.initial_node);
			}
			this.initial_node = n; 
			this.nodes.remove(n);
		}
	}

	public void setFinal(String name) {
		Node <T> n = this.findNode(name);
		if(n != null) {
			if(this.final_node != null) { /*TODO: por ahora asumimos que se mete al final, a saber luego*/
				this.nodes.add(this.final_node);
			}
			this.final_node = n;
			this.nodes.remove(n);
		}
	}

	public void addEdge(String name_node1, String name_node2) {
		Node<T> node1 = this.findNode(name_node1);
		Node<T> node2 = this.findNode(name_node2);
		if(node1 != null && node2 != null) {
			this.nodes.get(this.nodes.indexOf(node1)).addEdges(this.nodes.get(this.nodes.indexOf(node2)));
		}
	}

	public T run(T input, boolean debug) {
		String message_debug = "";
		int step = 1; 
		message_debug += "Step " + step +" (" + this.name + ") - input: " + input + "\n";
		
		if(this.initial_node != null) {
			this.initial_node.getAction().accept(input);
			step++;
			message_debug += "Step " + step +" (" + this.name + ") - "+ this.initial_node.getName() +" executed: " + input + "\n";
		}
		
		for(Node<T> n: this.initial_node.getEdges()) {
			n.getAction().accept(input);
			step++;
			message_debug += "Step " + step +" (" + this.name + ") - "+ n.getName() +" executed: " + input + "\n";
		}
		
		for(Node<T> n: this.nodes) {
			n.getAction().accept(input);
			step++;
			message_debug += "Step " + step +" (" + this.name + ") - "+ n.getName() +" executed: " + input + "\n";
		}
		
		if(this.final_node != null) {
			this.final_node.getAction().accept(input);
			step++;
			message_debug += "Step " + step +" (" + this.name + ") - "+ this.final_node.getName() +" executed: " + input + "\n";
		}
		
		message_debug = message_debug.substring(0, message_debug.length() - 1);
		if (debug == true) {
			System.out.println(message_debug);
		}
		return input;
	}
	
	/*TODO: idea del run*/
	/*for empezando por el inicial y cogemos el hijo y vuelve arriba hasta que el hijo o sea null o sea el final*/
	/*public T run(T input, boolean debug) {
		String message_debug = "";
		int step = 1; 
		message_debug += "Step " + step +" (" + this.name + ") - input: " + input + "\n";
		Node<T> initial = null; 
		Node<T> final_n = null;
		
		if(this.initial_node != null) {
			initial = this.initial_node;
		}else {
			initial = this.nodes.getFirst();
		}
		
		if(this.final_node != null) {
			final_n = this.final_node;
		}else {
			final_n = this.nodes.getLast();
		}
		
		initial.run(input, debug, final_n, step+1);
		
		return input;
	}*/
	
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
		buffer += "- Initial: " + this.initial_node.getName() + "\n";
		buffer += "- Final: " + this.final_node.getName() + "\n";
		buffer = buffer.substring(0, buffer.length() - 1);
		return buffer; 
	}

	public void addConditionalEdge(String node_name1, String node_name2, Consumer<T> action) {
		Node<T> node1 = this.findNode(node_name1);
		Node<T> node2 = this.findNode(node_name2);
		this.addEdge(node_name1, node_name2);
		if(node1 != null && node2 != null) {
			this.nodes.get(this.nodes.indexOf(node1)).setEdgeAction(action);
		}
	}

}
