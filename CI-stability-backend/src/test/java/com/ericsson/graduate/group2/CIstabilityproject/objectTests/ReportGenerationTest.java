package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.Build;
import com.ericsson.graduate.group2.CIstabilityproject.object.ReportGeneration;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReportGenerationTest {

    private final List<Double> numbers = new ArrayList<Double>();
    private final List<Build> testPasses = new ArrayList<Build>();
    private final ReportGeneration generator = new ReportGeneration();

    @Before
    public void setup() {
        numbers.add(5.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(7.0);
        numbers.add(10.0);

        Build passBuild = new Build(1649412559009l, true);
        Build failBuild = new Build(1649412559009l, false);

        testPasses.add(passBuild);
        testPasses.add(passBuild);
        testPasses.add(failBuild);
        testPasses.add(passBuild);
        testPasses.add(failBuild);
        testPasses.add(passBuild);
        testPasses.add(passBuild);
    }

    @Test
    public void testGetMedian() {
        assertEquals(generator.getMedian(numbers), 3.0, 0);
        numbers.remove(0);
        assertEquals(generator.getMedian(numbers), 5, 0);
    }

    @Test
    public void testGetFailRate() {
        assertEquals(generator.getFailRate(testPasses), 71.0, .5);
    }

    @Test
    public void testGetStandardDeviation() {
        assertEquals(generator.getStandardDeviation(numbers), 3.21, .4);
    }

    @Test
    public void testGetMean() {

    }
}
