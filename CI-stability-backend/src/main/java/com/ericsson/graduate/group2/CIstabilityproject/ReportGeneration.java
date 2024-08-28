package com.ericsson.graduate.group2.CIstabilityproject;

import com.ericsson.graduate.group2.CIstabilityproject.object.Build;

import java.util.List;

public class ReportGeneration {

    public double getMedian(List<Double> numbers) {
        double half = numbers.size() / 2;
        double res = 0;
        if(numbers.size() % 2 == 0) {
            res = numbers.get((int) half);
            res += numbers.get((int) half - 1);
            return (double) Math.abs(res/2);
        }else {
            return (double) Math.abs(numbers.get((int) half));
        }
    }

    public double getFailRate(List<Build> builds) {
        int success = 0;
        for(Build x : builds) {
            if(x.getBuildSuccess()) {success++;}
        }
        double left = (double) success;
        double right = (double) builds.size();
        double res = (left/right) * 100;
        return Math.round(res);
    }

    public double getStandardDeviation(List<Double> gaps) {
        /*
            1 - find the mean of the numbers
            2 - get the deviation of each number from mean
            3 - square each deviation
            4 - get the sum of the squares
            5 - get the variance (divide sum of squares by n-1 for sample or N for population)
            6 - square root the variance
         */

        double res = 0;
        double mean = getMean(gaps);
        double work = 0;
        for(double x : gaps) {
            work = mean - x;
            work *= work;
            res += work;
        }

        res /= gaps.size() - 1;
        return Math.sqrt(res);
    }

    public double getMean(List<Double> numbers){
        double res = 0.0;
        for(double x: numbers) {
            res += x;
        }
        return res / numbers.size();
    }

}
