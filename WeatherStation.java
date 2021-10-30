/*
Georgina Nelson: 16332886
CT5105: Assignment 2
 */

import java.util.*;
import java.util.stream.Collectors;

public class WeatherStation {
    public String city;
    public List<Measurement> measurements;
    public static List<WeatherStation> stations;

    public WeatherStation(String city, List<Measurement> measurements){
        this.city = city;
        this.measurements = measurements;
        stations = new ArrayList<>();
        stations.add(this);
    }

    public static void addStation(WeatherStation s){
        stations.add(s);
    }

    // Q1
    public double maxTemperature(int startTime, int endTime){
        // get max temp over period start-end
        double maxTemp = measurements.stream()
                .filter(m->m.getTime() >= startTime && m.getTime() <= endTime)
                .max(Comparator.comparing(Measurement::getTemperature))
                .orElseThrow(NoSuchElementException::new)
                .getTemperature();
        return maxTemp;
    }

    // Q2
    public static List<TempCountPair> countTemperature(double t1, double t2, double r)
    {
        List<TempCountPair> mapResult = new ArrayList<>();
        Map<Double, List<Integer>> shuffleResult = new LinkedHashMap<>();

        // MAP PHASE
        stations.stream()
                .flatMap(x -> x.measurements.parallelStream())
                .forEach(x -> {
                    if ((x.getTemperature() >= t1 - r) && (x.getTemperature() <= t1 + r)) {
                        mapResult.add(new TempCountPair(t1, 1)); }
                    if ((x.getTemperature() >= t2 - r) && (x.getTemperature() <= t2 + r)) {
                        mapResult.add(new TempCountPair(t2, 1)); }
                });

        // SHUFFLE PHASE : Group pairs by temp
        mapResult.parallelStream()
                .forEach(x -> {
                    List<Integer> values = shuffleResult.get(x.getTemp());
                    if(values == null) {
                        shuffleResult.put(x.getTemp(), new ArrayList<>(Arrays.asList(x.getCount())));
                    } else {
                        values.add(x.getCount());
                    }
                });


        // REDUCE PHASE
        return shuffleResult.entrySet().parallelStream()
                .map(x -> new TempCountPair(x.getKey(),
                                            x.getValue().parallelStream()
                                                        .mapToInt(Integer::intValue)
                                                        .sum()))
                .collect(Collectors.toList());
    }
}

// Temperature Count Key Value Pair Class
class TempCountPair {
    double temp;
    int count;

    public TempCountPair(double t, int c){
        temp = t;
        count = c;
    }

    public double getTemp() {
        return temp;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString()
    {
        // return "\nThere are "+count+ " occurrances of temperatures in specified range of temperature "+temp+"\n";
        return "< "+temp+" , "+count+" >";
    }
}
