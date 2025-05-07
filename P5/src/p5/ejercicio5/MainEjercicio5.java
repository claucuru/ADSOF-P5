package p5.ejercicio5;

import p5.ejercicio1.NumericData;
import p5.ejercicio1.StateGraph;

public class MainEjercicio5 {
	public static void main(String[] args) {
		StateGraph<NumericData> g = new StateGraph<>("loop-down", "Get a number, and decrease if positive");
		StateGraphLogger<NumericData> lg = new StateGraphLogger<>(g, "traces.txt"); //guarda info en traces.txt
		StateGraphProfiler<NumericData> sg = new StateGraphProfiler<>(lg);
		
		sg.addNode("decrease", (NumericData mo) -> mo.put("op1", mo.get("op1")-1)) //decrementa op1
		  .addConditionalEdge("decrease", "decrease", (NumericData mo) -> mo.get("op1")>0) //decrementa hasta 0
		  .setInitial("decrease");
		
		NumericData input = new NumericData(3, 0);
		System.out.println(sg + "\ninput = " + input);
		NumericData output = sg.run(input, true);
		System.out.println("result = "+ output);
		System.out.println("history = "+ sg.history());
	}
}
