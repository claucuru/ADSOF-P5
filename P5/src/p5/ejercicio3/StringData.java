package p5.ejercicio3;

import java.util.*;

import p5.ejercicio1.Data;
import p5.ejercicio1.NumericData;

public class StringData extends Data<String> {
    private int times;

    public StringData(String word, int times) {
        super(word, "");
        this.times = times;
        super.put("result", "");
    }

    public NumericData toNumericData() {
    	NumericData nd = new NumericData(this.times, 0);
        nd.put("op1", this.times);
        nd.put("op2", 0);
        return nd;
    }

    public StringData setTimes(int times) {
        this.times = times;
        return this;
    }

    public StringData replicate() {
        if (this.times > 0) {
            String word = this.get("op1");
            String currentResult = this.get("result");
            this.put("result", currentResult + (currentResult.isEmpty() ? "" : " ") + word);
            this.times--;
        }
        return this;
    }

    public int times() {
        return this.times;
    }

    @Override
    public String toString() {
    	return "word: " + this.get("op1") + ", times: " + times + ", result: " + this.get("result");
    }
}