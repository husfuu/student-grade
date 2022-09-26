package synrgy;


import com.github.freva.asciitable.AsciiTable;

import java.io.*;
import java.util.*;

public class File {
    private String filePath;
    private String targetPath;

    public File(){
        String absolutePath = new java.io.File("").getAbsolutePath();
        filePath = absolutePath + "/public/data_sekolah.csv";
        targetPath = absolutePath + "/public/data_sekolah_output.txt";
    }

    public File(String filePath){
        this.filePath = filePath;
    }

    public List<Double> getArrayList() throws FileNotFoundException {
        String line = "";
        List<List<String>> data = new ArrayList<>();
        List<Double> arrayData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                data.add(Arrays.asList(values));
            }

            for (List row:data) {
                for (int i = 0; i < row.size(); i++) {
                    if (i==0) {
                        continue;
                    }
                    arrayData.add(new Double(row.get(i).toString()));
                }
            }
        } catch (FileNotFoundException e){
            throw new FileNotFoundException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayData;
    }

    public void generatedStatsTxt(double mean, double median, double mode){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath));
            writer.write("Berikut adalah hasil statistic data: \n");
            String[] headers = {"Stats", "Value"};
            String[][] body = {
                    {"Mean", Double.toString(mean)},
                    {"Median", Double.toString(median)},
                    {"Mode", Double.toString(mode)}
            };
            writer.write(AsciiTable.getTable(headers, body));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generatedFreqsTxt(HashMap<Double, Double> freqData){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath));

            String[] headers = {"", "Value", "Frequency"};

            String[][] body = new String[freqData.size()][];

            int index = 0;
            for (Map.Entry<Double, Double> set: freqData.entrySet()) {
                String [] temp = new String[3];
                temp[0] = String.valueOf(index+1);
                temp[1] = String.valueOf(set.getKey());
                temp[2] = String.valueOf(set.getValue());

                // temp array add to body
                body[index] = temp;
                index += 1;
            }
            writer.write(AsciiTable.getTable(headers, body));
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
