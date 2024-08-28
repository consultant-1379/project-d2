package com.ericsson.graduate.group2.CIstabilityproject.entityTests;

import com.ericsson.graduate.group2.CIstabilityproject.entities.BuildEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class BuildEntityTest {

    BuildEntity testEntity = new BuildEntity();

    @BeforeEach
    public void setup() {
        testEntity.setStatus("SUCCESS");
        testEntity.setBuild_id(1);
        testEntity.setJob_id(1);
        testEntity.setTimestamp("1649412559009");
    }

    @Test
    public void testGetBuildId() {
        assertEquals(testEntity.getBuild_id(), 1);
    }

    @Test
    public void testGetJobId() {
        assertEquals(testEntity.getJob_id(), 1);
    }

    @Test
    public void testGetStartTime() {
        assertEquals(testEntity.getTimestamp(), 1649412559009l);
    }

    @Test
    public void testGetStatus() {
        assertEquals(testEntity.getStatus(), "SUCCESS");
    }
}
