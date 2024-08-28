package com.ericsson.graduate.group2.CIstabilityproject.object;

import java.util.ArrayList;
import java.util.List;

public class ChartBean {

    private List<String> dates;
    private List<String> failDates;
    private List<Double> failRate;
    private List<Double> sd;
    private List<Double> median;

    public ChartBean(List<String> dates, List<String> failDates) {
        this.dates = dates;
        int i = 0;
        for(String x: dates) {
            String[] ohno = x.split("-");
            String y = "";
            y += ohno[0] + "-" + ohno[1] + "-" + ohno[2];
            dates.set(dates.indexOf(x), y);
        }
        this.failDates = failDates;
        for(String x: failDates) {
            String[] ohno = x.split("-");
            String y = "";
            y += ohno[0] + "-" + ohno[1] + "-" + ohno[2];
            failDates.set(failDates.indexOf(x), y);
        }

        //trimLists();
    }


    public void trimLists() {
        if (dates.size() > 0 && failDates.size() > 0) {
            String startDate = dates.get(0);
            String failStart = failDates.get(0);

            List<String> newDates = new ArrayList<>();
            List<String> newFails = new ArrayList<>();
            List<Double> newRates = new ArrayList<>();
            List<Double> newSDs = new ArrayList<>();
            List<Double> newMeds = new ArrayList<>();

            int i = 1;
            if (failRate.size() > 0) {
                for (String x : dates) {
                    if (!x.equalsIgnoreCase(startDate)) {
                        newDates.add(startDate);
                        newRates.add(failRate.get(i - 1));
                        startDate = x;
                    }
                    i++;
                }
                newDates.add(startDate);
                newRates.add(failRate.get(failRate.size() - 1));
                dates = newDates;
                failDates = newFails;
                i = 1;
            }
        }

        if (sd.size() > 0) {
            for (String y : failDates) {
                if (!y.equalsIgnoreCase(failStart)) {
                    newFails.add(failStart);
                    newSDs.add(sd.get(i - 1));
                    newMeds.add(median.get(i - 1));
                    failStart = y;
                }
                i++;
            }

            newFails.add(failStart);
            newSDs.add(sd.get(sd.size() - 1));
            newMeds.add(median.get(median.size() - 1));

            sd.clear();
            median.clear();
            failRate.clear();

            for (double x : newSDs) {
                sd.add(x);
            }

            for (double x : newMeds) {
                median.add(x);
            }

            for (double x : newRates) {
                failRate.add(x);
            }
                /*sd = newSDs;
                median = newMeds;
                failRate = newRates;

                 */

        }
    }

    public List<Double> getValues() {
        return failRate;
    }

    public List<String> getDates() {
        return dates;
    }

    public List<Double> getSd() {
        return sd;
    }

    public List<Double> getMedian() {
        return median;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<Double> getFailRate() {
        return failRate;
    }

    public void setFailRate(List<Double> failRate) {
        this.failRate = failRate;
    }

    public void setSd(List<Double> sd) {
        this.sd = sd;

        if(sd.size() != failDates.size()) {
            while(failDates.size() != sd.size()) {
                failDates.remove(failDates.size() -1 );
            }
        }
    }

    public void setMedian(List<Double> median) {
        this.median = median;
        if(median.size() != failDates.size()) {
            while(failDates.size() != median.size()) {
                failDates.remove(failDates.size() -1 );
            }
        }
    }

    public List<String> getFailDates() {
        return failDates;
    }

    public void setFailDates(List<String> failDates) {
        this.failDates = failDates;
    }
}
