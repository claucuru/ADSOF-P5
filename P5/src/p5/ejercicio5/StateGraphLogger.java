package p5.ejercicio5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * 
 * Clase que decora a StateGraph
 */
public class StateGraphLogger<T extends Data<?>> extends Decorator<T> {
	private String file;
	private SimpleDateFormat dateFormat;

	/**
	 * Constructor de la clase
	 * @param sg 
	 * @param file
	 */
	public StateGraphLogger(Graph<T> sg, String file) {
		super(sg);
		this.file = file; 
		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	}

	public String getFile() {
		return file;
	}
	
	/**
	 * Funcion que guarda el historial en un fichero
	 * @param message
	 */
	private void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String timestamp = dateFormat.format(new Date());
            writer.write("[" + timestamp + "] " + message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

	/**
	 * Funcion que añade un  nodo a un grafo
	 * @param name nombre del nodo
	 * @param accion del nodo
	 */
	@Override
	public Graph<T> addNode(String name, Consumer<T> action) {
		return super.addNode(name, action);
	}

	@Override
	public Graph<T> setInitial(String name) {
		return super.setInitial(name);
	}

	/**
	 * Funcion que relaciona dos nodos en un edge con una condicion. Para ello se añade a una lista del primer nodo
	 * @param name_node1 
	 * @param name_node2
	 * @param action 
	 * @return el StateGraph
	 */
	@Override
	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action) {
		return super.addConditionalEdge(node_name1, node_name2, action);
	}

	/**
	 * Funcion que guarda el historial de un grafo
	 * @return buffer con el historial
	 */
	@Override
	public String history() {
		return super.history();
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
		message_debug += "Step " + step +" (" + super.getName() + ") - input: " + input + "\n";
		Node<T> node = null; 
		
		if(super.getInitialNode()!= null) {
			super.getInitialNode().executeAction(input);
			logToFile("node "+ super.getName() +" executed, with output: " + input);
			step++;
			message_debug += "Step " + step +" (" + super.getName() + ") - "+ super.getInitialNode().getName() +" executed: " + input + "\n";
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
					logToFile("node "+ super.getName() +" executed, with output: " + input);
					step++;
					message_debug += "Step " + step +" (" + super.getName() + ") - "+ nodes.getFirst().getName() +" executed: " + input + "\n";
				}
				if (node.equals(nodes.getFirst())){
					break;
				}
				node = nodes.getFirst();
			}else {
				nodes.getFirst().executeAction(input);
				logToFile("node "+ super.getName() +" executed, with output: " + input);
				step++;
				message_debug += "Step " + step +" (" + super.getName() + ") - "+ nodes.getFirst().getName() +" executed: " + input + "\n";
				node = nodes.getFirst();
			}
		}
		
		message_debug = message_debug.substring(0, message_debug.length() - 1);
		if (debug == true) {
			System.out.println(message_debug);
		}
		return input;
	}
	
	@Override
	public String toString() {
		return super.toString() + " [logged]";
	}

}
