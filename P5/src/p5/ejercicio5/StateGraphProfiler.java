package p5.ejercicio5;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.*;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;

public class StateGraphProfiler<T extends Data<?>> extends Decorator<T>{
	private Decorator<T> sl;
	public Map<Long, T> history;
	
	public StateGraphProfiler(Decorator<T> sl) {
		super(sl.getGraph());
		this.sl = sl; 
		this.history = new LinkedHashMap<Long, T>();
	}

	@Override
	public Graph<T> addNode(String name, Consumer<T> action) {
		return sl.addNode(name, action);
	}

	@Override
	public Graph<T> setInitial(String name) {
		return sl.setInitial(name);
	}

	@Override
	public Graph<T> addConditionalEdge(String node_name1, String node_name2, Predicate<T> action) {
		return sl.addConditionalEdge(node_name1, node_name2, action);
	}

	@Override
	public String history() {
		String buffer = "[";
		for(Map.Entry<Long, T> t: this.history.entrySet()) {
			buffer += "[" + super.getName() + " with: " +t.getValue() + " " + t.getKey();
		}
		buffer = buffer.substring(0, buffer.length() - 1);
		buffer += "]";
		return buffer; 
	}

	@Override
	public T run(T input, boolean debug) {
		String message_debug = "";
		int step = 1; 
		message_debug += "Step " + step +" (" + super.getName() + ") - input: " + input + "\n";
		Node<T> node = null; 
		long startTime = 0;
		
		if(super.getInitialNode()!= null) {
			startTime = System.currentTimeMillis();
			super.getInitialNode().executeAction(input);
			this.history.put(System.currentTimeMillis()-startTime, input);
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
					startTime = System.currentTimeMillis();
					nodes.getFirst().executeAction(input);
					this.history.put(System.currentTimeMillis()-startTime, input);
					step++;
					message_debug += "Step " + step +" (" + super.getName() + ") - "+ nodes.getFirst().getName() +" executed: " + input + "\n";
				}
				if (node.equals(nodes.getFirst())){
					break;
				}
				node = nodes.getFirst();
			}else {
				startTime = System.currentTimeMillis();
				nodes.getFirst().executeAction(input);
				startTime = System.currentTimeMillis();
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
		return sl.toString()  + " [profiled]";
	}
}
