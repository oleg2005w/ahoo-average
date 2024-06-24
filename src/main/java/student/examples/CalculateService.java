package student.examples;

import java.util.List;

public class CalculateService {


    public int calculateAverage(List<Integer> values){
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i);
        }
        return (int) Math.round(sum / values.size());
    }
}
