package p5.ejercicio3;

import java.util.function.*;

import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

public class NodeGraph<T extends Data<?>> extends Node<T>{ //TODO: NodeGraph puede tener dos tipos abstractos
	StateGraph<? extends Data<?>> sg; 
	Consumer<T> injector; 
	BiConsumer<? extends Data<?>, T> extractor; 
	
	public NodeGraph(String name, StateGraph<? extends Data<?>> wfNumeric) {
		super(name, null);
		this.sg = wfNumeric; 
	}
	
	/*TODO ej3*/
//	@Override
//	public void executeAction(T input) {
//		T newInput;
//		this.injector.accept(input);
//		newInput = input;
//		sg.run(newInput, false);
//		this.extractor.accept(newInput, input);
//	}
	
	public NodeGraph<T> withInjector(Consumer<T> action) {
		this.injector = action; 
		return this;
	}
	
	public NodeGraph<T> withExtractor(BiConsumer<? extends Data<?>, T> action){
		this.extractor = action; 
		return null; 
	}
	
}
