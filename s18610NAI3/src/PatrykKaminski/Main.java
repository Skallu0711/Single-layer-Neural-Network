package PatrykKaminski;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Main extends Application {

    static Perceptron p1 = new Perceptron("fr", 0.9);
    static Perceptron p2 = new Perceptron("no", 0.3);
    static Perceptron p3 = new Perceptron("pl", 0.4);

    public static void main(String[] args) {
        File file = new File("Languages");

        for(int i = 0; i < 200; i++ ) {
            learnFromDirectory(file, p1);
            //System.out.println(p1.toString());
            learnFromDirectory(file, p2);
            //System.out.println(p2.toString());
            learnFromDirectory(file, p3);
            //System.out.println(p3.toString());
        }
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField textField = new TextField();
        textField.setPrefWidth(340);
        Button button = new Button("Check");
        button.setOnAction(actionEvent -> {
            if (p1.calculateNet(readFromString(textField.getText())) == 1) {
                System.out.println(p1.getLanguage());
            } else if (p2.calculateNet(readFromString(textField.getText())) == 1) {
                System.out.println(p2.getLanguage());
            } else if (p3.calculateNet(readFromString(textField.getText())) == 1) {
                System.out.println(p3.getLanguage());
            } else {
                System.out.println("brak dopasowań");
            }

        });
        HBox hbox = new HBox();
        hbox.getChildren().addAll(textField,button);
        Scene scene = new Scene(hbox, 400, 60);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();
    }

    static void learnFromDirectory(File folder, Perceptron perceptron) {
        File[] files = folder.listFiles();
        List<File> fileList = Arrays.asList(files);
        Collections.shuffle(fileList);
        for ( File f : fileList) {
            if (f.isDirectory()) {
                learnFromDirectory(f, perceptron);
            } else {
                String fileName = f.getName().substring(0, f.getName().length() - 5);
                perceptron.learn(readFromFile(f), fileName);
            }
        }
    }

    public static double[] readFromFile(File f) {
        double[] result = new double[27];
        double charCounter = 0;
        Map<Character, Integer> charMap = new HashMap<Character, Integer>() {
            {
                put('a', 0);
                put('b', 0);
                put('c', 0);
                put('d', 0);
                put('e', 0);
                put('f', 0);
                put('g', 0);
                put('h', 0);
                put('i', 0);
                put('j', 0);
                put('k', 0);
                put('l', 0);
                put('m', 0);
                put('n', 0);
                put('o', 0);
                put('p', 0);
                put('q', 0);
                put('r', 0);
                put('s', 0);
                put('t', 0);
                put('u', 0);
                put('v', 0);
                put('w', 0);
                put('x', 0);
                put('y', 0);
                put('z', 0);
            }
        };
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int c = 0;
            while ((c = br.read()) != -1) {
                char character = (char) c;
                if (charMap.containsKey(character)) {
                    int value = charMap.get(character);
                    charMap.put(character, value + 1);
                    charCounter += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter = 0;
        for (Map.Entry<Character,Integer> entry : charMap.entrySet()){
            //System.out.println(entry);
            result[counter] = entry.getValue() / charCounter;
            counter += 1;
        }
        result[26] = -1.0;
        return result;
    }

    public static double[] readFromString(String input){
        double[] result = new double[27];
        double charCounter = 0;
        Map<Character, Integer> charMap = new HashMap<Character, Integer>() {
            {
                put('a', 0);
                put('b', 0);
                put('c', 0);
                put('d', 0);
                put('e', 0);
                put('f', 0);
                put('g', 0);
                put('h', 0);
                put('i', 0);
                put('j', 0);
                put('k', 0);
                put('l', 0);
                put('m', 0);
                put('n', 0);
                put('o', 0);
                put('p', 0);
                put('q', 0);
                put('r', 0);
                put('s', 0);
                put('t', 0);
                put('u', 0);
                put('v', 0);
                put('w', 0);
                put('x', 0);
                put('y', 0);
                put('z', 0);
            }
        };
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++){
            if (charMap.containsKey(chars[i])) {
                int value = charMap.get(chars[i]);
                charMap.put(chars[i], value + 1);
                charCounter += 1;
            }
        }
        int counter = 0;
        for (Map.Entry<Character,Integer> entry : charMap.entrySet()){
            result[counter] = entry.getValue() / charCounter;
            counter += 1;
        }
        result[26] = -1.0;
        return result;
    }
}

