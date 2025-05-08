package p5.ejercicio4;

import java.util.*;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * 
 * Clase que hereda de la clase StateGraph
 * Crea un grafo the tipo Streaming
 */
public class StreamingStateGraph<T extends Data<?>> extends StateGraph<T>{
	public List<T> history; 
	
	
	/**
	 * Constructor de la clase
	 * @param name
	 * @param action
	 */
	public StreamingStateGraph(String name, String action) {
		super(name, action);
		this.history = new ArrayList<T>();
	}
	
	/**
	 * Funcion que ejecuta todos los nodos de un grafo
	 * @param input grafo
	 * @param debug
	 */
	@Override
	public T run(T input, boolean debug) {
		String message_debug = "";
		int step = 1;
		String history = this.history().substring(0, this.history().length() - 1);
		message_debug += "Step " + step +" (" + super.getName() + ") - input: " + history + input + "]\n";
		Node<T> node = null; 
		
		if(super.getInitialNode() != null) {
			super.getInitialNode().executeAction(input);
			this.addHistory(input);
			step++;
			message_debug += "Step " + step +" (" + super.getName() + ") - "+ super.getInitialNode().getName() +" executed: " + this.history() + "\n";
			node = super.getInitialNode();
		}else {
			node = super.getNodes().getFirst();
		}
		
		while(node != super.getFinalNode() && node != null) {
			List <Node <T>> nodes = new ArrayList<>(node.getEdges());
			if(nodes.isEmpty()) {
				break; 
			}
			if(node.getConditionalAction()!=null) {
				if(node.getConditionalAction().test(input)) {
					nodes.getFirst().executeAction(input);
					this.addHistory(input);
					step++;
					message_debug += "Step " + step +" (" + super.getName() + ") - "+ nodes.getFirst().getName() +" executed: " + this.history() + "\n";
				}
				node = nodes.getFirst();
			}else {
				nodes.getFirst().executeAction(input);
				this.addHistory(input);
				step++;
				message_debug += "Step " + step +" (" + super.getName() + ") - "+ nodes.getFirst().getName() +" executed: " + this.history() + "\n";
				node = nodes.getFirst();
			}
		}
		message_debug = message_debug.substring(0, message_debug.length() - 1);
		if (debug == true) {
			System.out.println(message_debug);
		}
		return input; 
	}

	/**
	 * Funcion que devuelve el historial de un grafo
	 * @return buffer con el historial
	 */
	public String history() {
		String buffer = "[";
		for(T t: this.history) {
			buffer += t + ", ";
		}
		buffer = buffer.substring(0, buffer.length() - 1);
		buffer += "]";
		return buffer; 
	}

	public List<T> getHistory() {
		if(this.history.isEmpty()) {
			return null; 
		}
		return this.history;
	}

	/**
	 * Funcion que almacena el historial de un grafo
	 * @param input objeto a guardar
	 */
	public void addHistory(T input) {
		this.history.add(input);
	}
}
