package com.ericsson.graduate.group2.CIstabilityproject.objectTests;

import com.ericsson.graduate.group2.CIstabilityproject.object.CustomYear;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TestCustomYear {

    @Test
    public void testGetYear() {
        CustomYear test = new CustomYear(2022, 04, 06, 0 ,0);
        assertEquals(test.getYear(), 2022);
    }

    @Test
    public void testGetMonth() {
        CustomYear test = new CustomYear(2022, 04, 06, 0, 0);
        assertEquals(test.getMonth(), 04);
    }

    @Test
    public void testGetDay() {
        CustomYear test = new CustomYear(2022, 04, 06, 0, 0);
        assertEquals(test.getDay(), 06);
    }

    @Test
    public void testCompareTo() {
        CustomYear control = new CustomYear(2022, 04, 06, 0, 0);
        CustomYear earlyYear = new CustomYear(2020, 04, 06, 0, 0);
        CustomYear earlyMonth = new CustomYear(2022, 01, 04, 0, 0);
        CustomYear earlyDay = new CustomYear(2022, 04, 02, 0, 0);
        CustomYear lateYear = new CustomYear(2027, 8, 22, 0, 0);
        CustomYear lateMonth = new CustomYear(2022, 10, 4, 0, 0);
        CustomYear lateDay = new CustomYear(2022, 04, 22, 0, 0);
        CustomYear equal = new CustomYear(2022, 04, 06, 0, 0);

        // -1 = less than, +1 = more than, 0 = equal

        assertEquals(control.compareTo(earlyYear), 1);
        assertEquals(control.compareTo(earlyMonth), 1);
        assertEquals(control.compareTo(earlyDay), 1);
        assertEquals(control.compareTo(lateYear), -1);
        assertEquals(control.compareTo(lateMonth), -1);
        assertEquals(control.compareTo(lateDay), -1);
        assertEquals(control.compareTo(equal), 0);
    }

}
