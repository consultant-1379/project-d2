package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.ChartBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChartBeanTest {

    private List<String> testDates;
    private List<String> testFails;
    private List<Double> testRates;
    private List<Double> testSds;
    private List<Double> testMeds;

    private ChartBean testChartBean;

    @BeforeEach
    public void setup() {
        testDates = new ArrayList<>(Arrays.asList(
                "2020-04-05-00-00",
                "2021-07-12-11-11",
                "2022-11-25-08-02"
        ));

        testFails = new ArrayList<>(Arrays.asList(
                "2020-02-05-00-00",
                "2021-04-07-20-11",
                "2022-01-25-08-02"
        ));

        testRates = new ArrayList<Double>(Arrays.<Double>asList(
                45.3, 50.22, 60.0
        ));

        testSds = new ArrayList<Double>(Arrays.<Double>asList(
                3.38, 9.39, 2.22
        ));

        testMeds = new ArrayList<Double>(Arrays.<Double>asList(
                22.2, 33.3, 11.1
        ));

        testChartBean = new ChartBean(testDates, testFails);
        testChartBean.setFailRate(testRates);
        testChartBean.setSd(testSds);
        testChartBean.setMedian(testMeds);
    }

    @Test
    public void testGetValues() {
        assertEquals(testChartBean.getValues().get(1), 50.22, 0);
    }

    @Test
    public void testGetDates() {
        assertEquals(testChartBean.getDates().get(2), "2022-11-25");
    }

    @Test
    public void testGetFailDates() {
        assertEquals(testChartBean.getFailDates().get(0), "2020-02-05");
    }

    @Test
    public void testGetSds() {
        assertEquals(testChartBean.getSd().get(1), 9.39, 0);
    }

    @Test
    public void testGetMedians() {
        assertEquals(testChartBean.getMedian().get(2), 11.1, 0);
    }

    @Test
    public void testGetFailRates() {
        assertEquals(testChartBean.getFailRate().get(0), 45.3, 0);
    }

    @Test
    public void testSetDates() {
        List<String> newDate = new ArrayList<>(Arrays.asList(
                "01-01-01"
        ));
        testChartBean.setDates(newDate);
        assertEquals(testChartBean.getDates().size(), 1);
        assertEquals(testChartBean.getDates().get(0), "01-01-01");
    }
}
