package p5.ejercicio3;

import p5.ejercicio1.NumericData;
import p5.ejercicio1.StateGraph;

public class MainEjercicio3{
	public static void main(String[] args) {
		StateGraph<NumericData> sg = buildWorkflow1();
		StateGraph<StringData> sd = buildWorkflow(sg);
		
		System.out.println(sd);
		
		StringData input = new StringData("jamon", 2);
		System.out.println("input = " + input);
		StringData output = sd.run(input, true); //ejecución con debug
		System.out.println("result = " + output);
		      
	}
	
	private static StateGraph<NumericData> buildWorkflow1(){
		StateGraph<NumericData> sg = new StateGraph<>("math1", "Add two numbers, and square if even");
		sg.addNode("sum", (NumericData mo) -> mo.put("result", mo.get("op1")+mo.get("op2")))
		.addNode("square", (NumericData mo) -> mo.put("result", mo.get("result")*mo.get("result")));
		
		sg.addConditionalEdge("sum", "square", (NumericData mo) -> mo.get("result")%2 == 0);
		
		sg.setInitial("sum");
		sg.setFinal("square");
		
		return sg; 
	}
	
	private static StateGraph<StringData> buildWorkflow(StateGraph<NumericData> wfNumeric){
		StateGraph<StringData> sg = new StateGraph<>("replicate", "Replicates a given word");
		
		sg.addWfNode("calculate", wfNumeric)
			.withInjector((StringData sd) -> sd.toNumericData())
			.withExtractor((NumericData nd, StringData sd) -> sd.setTimes(nd.get("result")));
		sg.addNode("replicate", sd -> sd.replicate());
		sg.addEdge("calculate", "replicate")
			.addConditionalEdge("replicate", "replicate", sd -> sd.times()>0);
		
		sg.setInitial("calculate");

		
		return sg; 
	}
}
