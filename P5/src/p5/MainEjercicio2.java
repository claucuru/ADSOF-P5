package p5;

public class MainEjercicio2{
	public static void main(String[] args) {
		StateGraph<NumericData> sg = buildWorflow();
		
		System.out.println(sg);
		
		NumericData input = new NumericData(2, 3);
		System.out.println("input = " + input);
		NumericData output = sg.run(input, true); //ejecución con debug
		System.out.println("result = " + output);
		      
	}
	
	private static StateGraph<NumericData> buildWorflow(){
		StateGraph<NumericData> sg = new StateGraph<>("math1", "Add two numbers, and square if even");
		sg.addNode("sum", (NumericData mo) -> mo.put("result", mo.get("op1")+mo.get("op2")))
		.addNode("square", (NumericData mo) -> mo.put("result", mo.get("result")*mo.get("result")));
		
		sg.addConditionalEdge("sum", "square", (NumericData mo) -> mo.put("result", mo.get("result")%2));
		
		sg.setInitial("sum");
		sg.setFinal("square");
		
		return sg; 
	}
}
