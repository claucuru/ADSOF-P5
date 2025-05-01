package p5;

import java.util.function.*;

public class NodeGraph<T extends Data<?>> extends Node<T>{
	StateGraph<? extends Data<?>> sg; 
	Consumer<T> injector; 
	BiConsumer<? extends Data<?>, T> extractor; 
	
	public NodeGraph(String name, StateGraph<? extends Data<?>> wfNumeric) {
		super(name, null);
		this.sg = wfNumeric; 
	}
	
	@Override
	public void executeAction(T input) {
		this.injector.accept(input);
//		sg.run(input, false);
	}
	
	public NodeGraph<T> withInjector(Consumer<T> action) {
		this.injector = action; 
		return this;
	}
	
	public NodeGraph<T> withExtractor(BiConsumer<? extends Data<?>, T> action){
		this.extractor = action; 
		return null; 
	}
	
}
