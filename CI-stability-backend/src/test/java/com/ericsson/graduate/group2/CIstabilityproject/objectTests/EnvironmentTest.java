package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.Build;
import com.ericsson.graduate.group2.CIstabilityproject.object.CustomYear;
import com.ericsson.graduate.group2.CIstabilityproject.object.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class EnvironmentTest {

    private List<Build> testBuilds = new ArrayList<Build>();

    @BeforeEach
    public void setup() {
        Build four = new Build(new CustomYear(2021, 12, 6, 0, 0), false);
        Build three = new Build(new CustomYear(2022, 1, 2, 0, 0), true);
        Build one = new Build(new CustomYear(2022, 1, 13, 0, 0), true);
        Build two = new Build(new CustomYear(2022, 1, 17, 0, 0), false);
        Build fifteenDays = new Build(new CustomYear(2022, 2, 1, 0, 0), true);

        //ORDER - 28, 15
        testBuilds.add(one);
        testBuilds.add(two);
        testBuilds.add(three);
        testBuilds.add(four);
        testBuilds.add(fifteenDays);

    }

    @Test
    public void testSetupOfEnvironment() {

        Environment test = new Environment(testBuilds);
        assertEquals(test.getBuilds().size(), 5);
        assertEquals(test.getBuilds().get(0).getDate().getYear(), 2021);
        assertEquals(test.getBuilds().get(1).getDate().getYear(), 2022);
        assertEquals(test.getBuilds().get(1).getDate().getDay(), 2);
        assertEquals(test.getBuilds().get(2).getDate().getDay(), 13);
        assertEquals(test.getBuilds().get(3).getDate().getDay(), 17);

        for (double day : test.getGapsBetweenFailures()) {
            System.out.println(day);
        }
    }
    /*private final List<Build> builds;
    private ReportGeneration generator;
    private List<Double> gapsBetweenFailures;

     */

    //27.0
    //15.0
    @Test
    public void testGetFailRate() {
        Environment test = new Environment(testBuilds);
        double failRate = test.getBuildFailRate();
        assertEquals(failRate, 60.0, 0);
    }

    @Test
    public void testGetStandardDeviation() {
        Environment test = new Environment(testBuilds);
        double sd = test.getStandardDeviationOfBuildFailRecoveryTime();
        assertEquals(8.4, sd, 0.1);
    }

    @Test
    public void testGetRecoveryTimeMedian() {
        Environment test = new Environment(testBuilds);
        double med = test.getBuildFailRecoveryTimeMedian();
        assertEquals(med, 21.0, 0);
    }
}
