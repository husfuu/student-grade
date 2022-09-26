package synrgy;


import com.github.freva.asciitable.AsciiTable;

import java.io.*;
import java.util.*;

public class File {
    private String targetPath;
    private String outPath1;
    private  String outPath2;
    public File(){
        String absolutePath = new java.io.File("").getAbsolutePath();
        targetPath = absolutePath + "/public/data/data_sekolah.csv";
        outPath1 = absolutePath +  "/public/output/freq_out.txt";
        outPath2 = absolutePath + "/public/output/stat_out.txt";
//        targetPath = absolutePath + "/public/data_sekolah_output.txt";
    }

    public String getTargetPath() {
        return targetPath;
    }

    public String getOutPath1(){
        return outPath1;
    }
    public String getOutPath2(){
        return outPath2;
    }
    public List<Double> getArrayList() throws FileNotFoundException {
        String line;
        List<List<String>> data = new ArrayList<>();
        List<Double> arrayData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(targetPath ));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath2));
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath1));

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
