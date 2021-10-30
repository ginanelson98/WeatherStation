/*
Georgina Nelson: 16332886
CT5105: Assignment 2
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String args[]){

        // ************************************************************************************************************
        // Q1
        System.out.println("********************** Q1 **********************");

        Random rand = new Random();
        List<Measurement> measurements = new ArrayList<>();
        // System.out.println("WeatherStation1:");
        for(int i=0; i<9; i++){
            Measurement m = new Measurement(i, (1 + (10 - 1) * rand.nextDouble()));
            // System.out.println(m.toString());
            measurements.add(m);
        }
        WeatherStation w = new WeatherStation("Galway", measurements);

        int startTime = 1;
        int endTime = 7;

        System.out.println("Max temp in range [" + startTime + ", " + endTime + "] is " + w.maxTemperature(startTime,endTime));



        // ************************************************************************************************************
        // Q2
        System.out.println("********************** Q2 **********************");

        // create new weatherstation and add to list stations
        List<Measurement> measurements1 = new ArrayList<>();
        // System.out.println("WeatherStation2:");

        for(int i=0; i<6; i++){
            Measurement m = new Measurement(i, (1 + (10 - 1) * rand.nextDouble()));
            // System.out.println(m.toString());
            measurements1.add(m);
        }

        WeatherStation w1 = new WeatherStation("Dublin", measurements1);

        WeatherStation.addStation(w);
        WeatherStation.addStation(w1);

        // test countTemperatures with the below values
        double t1 = 3.0;
        double t2 = 5.5;
        double r = 1.6;

        List<TempCountPair> tempCounts = WeatherStation.countTemperature(t1, t2, r);
        System.out.println("Temperature counts where range="+r+" are as follows:\n"+tempCounts);
    }
}
