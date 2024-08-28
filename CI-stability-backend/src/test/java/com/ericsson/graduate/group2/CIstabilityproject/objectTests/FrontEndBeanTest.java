package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.FrontEndBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FrontEndBeanTest {

    private List<String> jobs;
    private String startDate;
    private String endDate;

    private FrontEndBean bean;

    @BeforeEach
    public void setup() {
        jobs = new ArrayList<>(Arrays.asList(
                "job one",
                "job two",
                "job three"
        ));

        startDate = "21-02-2022";
        endDate = "22-04-2022";

        bean = new FrontEndBean(jobs, startDate, endDate);
    }

    @Test
    public void testGetJobs() {
        assertEquals(bean.getJobs().size(), 3, 0);
        assertEquals(bean.getJobs().get(0), "job one");
        assertEquals(bean.getJobs().get(1), "job two");
        assertEquals(bean.getJobs().get(2), "job three");
    }

    @Test
    public void testGetStartDate() {
        assertEquals(bean.getStartDate(), "21-02-2022");
    }

    @Test
    public void testGetEndDate() {
        assertEquals(bean.getEndDate(), "22-04-2022");
    }

    @Test
    public void testSetJobs() {
        List<String> newJobs = new ArrayList<>(Arrays.asList(
                "nya nya"
        ));
        bean.setJobs(newJobs);
        assertEquals(bean.getJobs().size(), 1);
        assertEquals(bean.getJobs().get(0), "nya nya");
    }

    @Test
    public void testSetStartDate() {
        bean.setStartDate("01-01-01");
        assertEquals(bean.getStartDate(), "01-01-01");
    }
    @Test
    public void testSetEndDate() {
        bean.setEndDate("01-01-01");
        assertEquals(bean.getEndDate(), "01-01-01");
    }
}
