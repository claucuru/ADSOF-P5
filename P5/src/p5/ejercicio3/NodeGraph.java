package p5.ejercicio3;

import java.util.function.*;
import p5.ejercicio1.Data;
import p5.ejercicio1.Node;
import p5.ejercicio1.StateGraph;

/**
 * @author Claudia Cuevas Ruano
 * @author Lucia Espinosa Murillo
 * Clase que define un nodo que contiene un grafo. Hereda de Node
 */
public class NodeGraph<T extends Data<?>, S extends Data<?>> extends Node<T> {
    private StateGraph<S> graph;
    private Function<T, S> injector;
    private BiConsumer<S, T> extractor;
    
    /**
     * Constructor de la clase
     * @param name
     * @param graph 
     */
    public NodeGraph(String name, StateGraph<S> graph) {
        super(name, null);
        this.graph = graph;
    }
    
	/**
	 * Funcion que ejecuta la accion de un nodo
	 * @param input accion de un nodo
	 */
    @Override
    public void executeAction(T input) {
        S returnedinput = injector.apply(input);
        graph.run(returnedinput, false);
        extractor.accept(returnedinput, input);
    }
    
    /**
     * Funcion que indica como traducir el flujo del estado padre al nodo incluido
     * @param action
     * @return el nodo
     */
    public NodeGraph<T, S> withInjector(Function<T, S> action) {
        this.injector = action;
        return this;
    }
    
    /**
     * Refleja la salida del flujo incluido en el flujo del estado padre
     * @param action
     * @return el nodo
     */
    public NodeGraph<T, S> withExtractor(BiConsumer<S, T> action) {
        this.extractor = action;
        return this;
    }
}
