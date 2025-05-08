package p5.ejercicio3;

import java.util.function.*;
import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

public class NodeGraph<T extends Data<?>, S extends Data<?>> extends Node<T> {
    private StateGraph<S> graph;
    private Function<T, S> injector;
    private BiConsumer<S, T> extractor;
    
    public NodeGraph(String name, StateGraph<S> graph) {
        super(name, null);
        this.graph = graph;
    }
    
    @Override
    public void executeAction(T input) {
        S returnedinput = injector.apply(input);
        graph.run(returnedinput, false);
        extractor.accept(returnedinput, input);
    }
    
    public NodeGraph<T, S> withInjector(Function<T, S> action) {
        this.injector = action;
        return this;
    }
    
    public NodeGraph<T, S> withExtractor(BiConsumer<S, T> action) {
        this.extractor = action;
        return this;
    }
}