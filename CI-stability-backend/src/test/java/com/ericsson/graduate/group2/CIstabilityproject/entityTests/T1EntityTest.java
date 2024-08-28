package com.ericsson.graduate.group2.CIstabilityproject.entityTests;

import com.ericsson.graduate.group2.CIstabilityproject.entities.T1Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class T1EntityTest {

    public T1Entity entity;
    @BeforeEach
    public void setup() {
        entity = new T1Entity();
        entity.setId(1);
        entity.setJob_name("test");
    }

    @Test
    public void testGetId() {
        assertEquals(entity.getId(), 1, 0);
    }

    @Test
    public void testGetJobName() {
        assertEquals(entity.getJob_name(), "test");
    }
}
