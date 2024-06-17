package student.examples;

import java.util.ArrayList;
import java.util.List;

public class Average {


    public int calcAverage(List<Integer> values){
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return (int) Math.round(sum / values.size());
    }
}
