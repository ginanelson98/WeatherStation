/*
Georgina Nelson: 16332886
CT5105: Assignment 2
 */

public class Measurement {
    public int time;
    public double temperature;

    public Measurement(int t, double temp){
        time = t;
        temperature = temp;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "time=" + time +
                ", temperature=" + temperature +
                '}';
    }
}
