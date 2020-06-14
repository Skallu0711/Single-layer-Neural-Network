package PatrykKaminski;

import java.util.*;

public class Perceptron {

    private String language;
    private double[] weights;
    private Random ra = new Random();

    public Perceptron(String language, double bias){
        this.language = language;
        weights = ra.doubles(27,0,1).toArray();
        weights[26] = bias;
    }

    public void learn(double[] input, String textLanguage){
        double learningConstant = 0.4;

        double d = textLanguage.equals(language) ? 1 : 0;

        double y = calculateNet(input);
        for(int i = 0; i < input.length; i++){
            weights[i] = weights[i] + learningConstant * (d - y) * input[i];
        }
    }

    double calculateNet(double[] input){
        double net = 0;
        for (int i = 0; i < weights.length; i++) {
            net += weights[i] * input[i];
        }
        return net >= 0 ? 1 : 0;
    }

    public String getLanguage() {
        return language;
    }

//    @Override
//    public String toString() {
//        return "Perceptron{" +
//                "weights=" + Arrays.toString(weights) +
//                '}';
//    }
}

