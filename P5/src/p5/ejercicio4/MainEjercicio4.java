package p5.ejercicio4;

import java.util.*;

public class MainEjercicio4 {
	public static void main(String[] args) {
		StreamingStateGraph<DoubleData> sg = buildWorkflow();
		
		System.out.println(sg);
		
		List
			.of(1, 5, 2, 4)
			.forEach( d-> { DoubleData wfInput = new DoubleData(d, 0);
							System.out.println("Workflow input = "+wfInput);
							sg.run(wfInput, true);
							});
		System.out.println("History=" +sg.history());
	}
	
	private static StreamingStateGraph<DoubleData> buildWorkflow(){
		StreamingStateGraph<DoubleData> sg = new StreamingStateGraph<DoubleData>("average", "Calculates the average of incoming data");
		sg.addNode("average",  input -> { if(sg.getHistory() == null) {
			  								input.put("op2", input.get("op1"));
		  								}else {
		  									double suma = input.get("op1");
		  									for(DoubleData t: sg.getHistory()) {
		  										suma += t.get("op1");
		  									}
		  								input.put("op2", (suma/(sg.getHistory().size()+1)));
		  								}
										});
		sg.setInitial("average"); 
		return sg; 
	}
}
