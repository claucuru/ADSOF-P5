package p5.ejercicio3;

import java.util.*;

import p5.ejercicio1.Data;
import p5.ejercicio1.NumericData;

public class StringData extends Data<String> {
    private int times;

    /**
     * Constructor de la clase
     * @param word palabra
     * @param times tiempo
     */
    public StringData(String word, int times) {
        super(word, "");
        this.times = times;
        super.put("result", "");
    }

    /**
     * Funcion que crea un dato numérico apartir del tiempo 
     * @return numericData
     */
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

    /**
     * Funcion que decrementa el tiempo y añade una copia de word a result 
     * @return StringData
     */
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
