package synrgy;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Data {
    private List<Double> data;

    public Data(List<Double> data){
        this.data = data;
    }

    public List<Double> getData() {
        return data;
    }

    public double getMedian(){
        Collections.sort(data);
        int num = data.size();
        double res;
        if (num % 2 == 1){
            res = data.get( (num+1) / (2 - 1) );
        } else {
            res = data.get( ((num/2-1) + (num / 2 )) / 2 );
        }
        return res;
    }
    public double getMean(){
        double sum = 0;
        for (Double datum : data) {
            sum += datum;
        }
        return sum / data.size();
    }
    public double getMode(){
        double mode = 0;
        int maxCount = 0;
        for (int i = 0; i < data.size(); i++) {
            int count = 0;
            for (Double datum : data) {
                if (Objects.equals(data.get(i), datum)) {
                    count += 1;
                }
            }
            if (count > maxCount){
                maxCount = count;
                mode = data.get(i);
            }
        }
        return mode;
    }

    public HashMap<Double, Double> getFreq(){
        HashMap<Double, Double> result = new HashMap<Double, Double>();
        double max = 1;
        double temp = 0;

        for (int i = 0; i < data.size(); i++) {
            if (result.get(data.get(i)) != null){
                double count = result.get(data.get(i));
                count += 1;
                result.put(data.get(i), count);

                if (count > max){
                    max = count;
                    temp = data.get(i);
                }
            } else {
                result.put(data.get(i), 1.0);
            }
        }
        return result;
    }
}
