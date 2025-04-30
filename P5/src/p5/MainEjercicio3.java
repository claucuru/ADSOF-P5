package p5;

public class MainEjercicio3{
	public static void main(String[] args) {
		StateGraph<NumericData> sg = buildWorflow();
		
		System.out.println(sg);
		
		NumericData input = new NumericData(2, 3);
		System.out.println("input = " + input);
		NumericData output = sg.run(input, true); //ejecución con debug
		System.out.println("result = " + output);
		      
	}
	
	private static StateGraph<StringData> buildWorflow(StateGraph<NumericData> wfNumeric){
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
