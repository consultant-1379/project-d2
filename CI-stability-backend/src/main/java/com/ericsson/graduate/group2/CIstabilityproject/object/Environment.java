package com.ericsson.graduate.group2.CIstabilityproject.object;


import com.ericsson.graduate.group2.CIstabilityproject.ReportGeneration;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Environment {
    private final List<Build> builds;
    private ReportGeneration generator;
    private List<Double> gapsBetweenFailures;


    private List<String> dates;
    private List<String> failDates;


    public Environment(List<Build> builds) {
        this.builds = builds.stream().sorted(
                (x, y) -> x.getDate().compareTo(y.getDate())).collect(Collectors.toList());
        this.generator = new ReportGeneration();
        failDates = new ArrayList<>();
        gapsBetweenFailures = this.setGapsBetweenFailures();
        dates = this.setDates();
    }

    /*
    SO
        - Every build has a date and a time
        - For each gap we need to find the median at that point and the standard deviation at that point
        - THEN we must store the date at which that calculation was made (the most recent build) and save that
     */
    public List<String> setDates() {
        List<String> res = new ArrayList<String>();
        List<Build> builds = this.getBuilds();
        for (Build x : builds) {
            res.add(x.getDate().toString());
        }
        return res;
    }

    public List<String> getDates() {
        return dates;

    }

    public List<Build> getBuilds() {
        return builds;
    }

    public List<Double> setGapsBetweenFailures() {
        Build lastFail = null;
        boolean hasFailedOnce = false;
        boolean flipflop = false;
        CustomYear deduct = null;
        int days = 0;
        double remainder = 0.0;

        List<Double> result = new ArrayList<Double>();
        List<Build> dontmesswiththebuilds = new ArrayList<Build>();

        for(Build x: this.getBuilds()) {
            Build totallyDifferentBuild = new Build(new CustomYear(x.getDate().getYear(), x.getDate().getMonth(), x.getDate().getDay(), x.getDate().getHours(), x.getDate().getMinutes()), x.getBuildSuccess());
            dontmesswiththebuilds.add(totallyDifferentBuild);
        }

        int[] monthsWith30Days = {4, 6, 9, 11};
        for(Build x: dontmesswiththebuilds) {
            if(x.getBuildSuccess() == flipflop) {
                        if(hasFailedOnce) {
                            CustomYear countdown = new CustomYear(x.getDate().getYear(), x.getDate().getMonth(), x.getDate().getDay(), x.getDate().getHours(), x.getDate().getMinutes());
                            days += 0;
                            remainder = (countdown.getFractionOfDays() - lastFail.getDate().getFractionOfDays());
                            if(countdown.getMonth() == lastFail.getDate().getMonth() && countdown.getYear() == lastFail.getDate().getYear()) {
                                days = countdown.getDay() - lastFail.getDate().getDay();
                                days += remainder;
                                failDates.add(x.getDate().toString());
                                result.add((double) days);
                                continue;
                            }
                            boolean month1 = true;
                            while (countdown.getYear() != lastFail.getDate().getYear() || countdown.getMonth() != lastFail.getDate().getMonth()) {
                                if(!month1) {
                                    switch (countdown.getMonth()) {
                                        case 4:
                                        case 6:
                                        case 9:
                                        case 11:
                                            days += 30;
                                            break;
                                        case 2:
                                            days += 28;
                                            break;
                                        default:
                                            days += 31;
                                            break;
                                    }
                                } else {
                                    days += countdown.getDay();
                                    month1 = false;
                                }

                                //if()
                                if(countdown.getMonth() == 1) {
                                    countdown.setMonth(12);
                                    countdown.setYear(countdown.getYear() - 1);
                                } else {
                                    countdown.setMonth(countdown.getMonth() - 1);
                                }
                            }
                            switch (countdown.getMonth()) {
                                case 4:
                                case 6:
                                case 9:
                                case 11:
                                    days += (30 -lastFail.getDate().getDay());
                                    break;
                                case 2:
                                    days += (28 - lastFail.getDate().getDay() );
                                    break;
                                default:
                                    days += (31 - lastFail.getDate().getDay());
                                    break;
                            }
                            days = Math.abs(days);
                            days += remainder;
                            result.add((double) days);
                            failDates.add(x.getDate().toString());
                            days = 0;
                        }
                        hasFailedOnce = true;
                                lastFail = x;
                        if(flipflop) {
                            flipflop = false;
                        } else {
                            flipflop = true;
                        }
                    }
                }
                if(flipflop == true && result.size() > 0) {
                    result.remove(result.size() - 1);
                }
                return result;
            }

            public List<Double> getGapsBetweenFailures() {
        return gapsBetweenFailures;
    }



    public List<Double> getOrderedBFR() {
        List<Double> res = new ArrayList<>();
        List<Build> runThrough = new ArrayList<>();
        for(Build x : this.getBuilds()){
            runThrough.add(x);
            res.add(generator.getFailRate(runThrough));
        }
        return res;
    }

    public List<Double> getOrderedSD() {
        List<Double> res = new ArrayList<>();
        List<Double> runThrough = new ArrayList<>();
        for(Double x : this.getGapsBetweenFailures()) {
            runThrough.add(x);
            res.add(generator.getStandardDeviation(runThrough));
        }
        if(res.size() > 0) {
            res.remove(0);
        }
        return res;
    }

    public List<Double> getOrderedMedians() {
        List<Double> res = new ArrayList<>();
        List<Double> runThrough = new ArrayList<>();
        for(Double x : this.getGapsBetweenFailures()) {
            runThrough.add(x);
            res.add(generator.getMedian(runThrough));
        }
        if(res.size() > 0) {
            res.remove(0);
        }
        return res;
    }


    public double getBuildFailRate() {
        return generator.getFailRate(this.getBuilds());
    }

    public double getStandardDeviationOfBuildFailRecoveryTime() {
        return generator.getStandardDeviation(this.getGapsBetweenFailures());
    }

    public double getBuildFailRecoveryTimeMedian() {
        return generator.getMedian(this.getGapsBetweenFailures());
    }



    public List<String> getFailDates() {
        return failDates;
    }

}
