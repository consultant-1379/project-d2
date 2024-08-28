package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.Build;
import com.ericsson.graduate.group2.CIstabilityproject.object.CustomYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class TestBuild {

    private Build testBuild = new Build();
    private Build failTestBuild = new Build();
    private CustomYear year = new CustomYear();

    @BeforeEach
    public void setup() {
        year = new CustomYear(2020, 12, 25, 0, 0);
        testBuild = new Build(year, true);
        failTestBuild = new Build(year, false);
    }

    @Test
    public void testGetDate() {
        CustomYear testYear = new CustomYear(2020, 12, 25, 0, 0);
        assertEquals(testBuild.getDate().getDay(), testYear.getDay());
        assertEquals(testBuild.getDate().getMonth(), testYear.getMonth());
        assertEquals(testBuild.getDate().getYear(), testYear.getYear());
    }

    @Test
    public void testGetBuildSuccess() {
        assertEquals(testBuild.getBuildSuccess(), true);
    }

    @Test
    public void testConvertToCustomDate() {
        String data = "1649412559009";
        long longData = Long.parseLong(data);
        CustomYear testYear = testBuild.convertToCustomDate(longData);
        assertEquals(testYear.getDay(), 8);
        assertEquals(testYear.getMonth(), 4);
        assertEquals(testYear.getYear(), 2022);

    }

}
