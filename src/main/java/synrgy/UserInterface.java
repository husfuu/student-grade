package synrgy;


import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
    }

    public void header(){
        System.out.println("============================================");
        System.out.println("Student Grade Console App");
        System.out.println("============================================");
    }

    public void home(){
        header();
        System.out.println("Menu");
        System.out.println("1. Generate txt file for frequency");
        System.out.println("2. Generate txt file for mean, media, and mode");
        System.out.println("3. Generate both files");
        System.out.print("Choose: ");
        String input = scanner.nextLine();
         switch (input){
            case "1":
                try {
                    File file = new File();
                    List<Double> arrayData = file.getArrayList();
                    Data data = new Data(arrayData);
                    HashMap<Double, Double> freq = data.getFreq();
                    file.generatedFreqsTxt(freq);
                    success(file.getOutPath1());
                } catch (FileNotFoundException e){
                    fail();
                }
                break;
            case "2":
                try {
                    File file = new File();
                    List<Double> arrayData = file.getArrayList();
                    Data data = new Data(arrayData);
                    double mean = data.getMean();
                    double median = data.getMedian();
                    double mode = data.getMode();
                    file.generatedStatsTxt(mean, median, mode);
                    success(file.getOutPath2());
                }catch (FileNotFoundException e){
                    fail();
                }
                break;
            case "3":
                try{
                    File file = new File();
                    List<Double> arrayData = file.getArrayList();
                    Data data = new Data(arrayData);
                    HashMap<Double, Double> freq = data.getFreq();
                    file.generatedFreqsTxt(freq);
                    double mean = data.getMean();
                    double median = data.getMedian();
                    double mode = data.getMode();
                    file.generatedStatsTxt(mean, median, mode);
                    success(file.getOutPath1());
                } catch (FileNotFoundException e) {
                    fail();
                }
                break;
            case "0":
                scanner.close();
                break;
            default:
                alert();
                home();
                break;
        }
    }

    public void success(String path){
        String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        String ANSI_BLACK_TEXT = "\u001B[30m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK_TEXT + "File has been generated in " + path + ANSI_RESET);
        header();
        System.out.println("1. Home");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        String input = scanner.nextLine();
        switch (input){
            case "1":
                home();
                break;
            case "0":
                scanner.close();
                break;
            default:
                alert();
                success(path);
                break;
        }
    }

    public void fail(){
        String ANSI_RED_BACKGROUND = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";
        String warn = ANSI_RED_BACKGROUND + "fail to generate file ..." + ANSI_RESET;
        System.out.println(warn);
        header();
        System.out.println("1. Home");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        String input = scanner.nextLine();
        switch (input){
            case "1":
                home();
                break;
            case "0":
                scanner.close();
                break;
            default:
                fail();
                alert();
                break;
        }
    }

    public void alert(){
        String ANSI_BLACK_TEXT = "\u001B[30m";
        String ANSI_YELLOW_BG = "\u001B[43m";
        String ANSI_RESET = "\u001B[0m";
        System.out.println(ANSI_BLACK_TEXT + ANSI_YELLOW_BG + "Please input correctly!" + ANSI_RESET);
    }
}

