package com.ksubaka;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Hari Rao on 07/01/17.
 */
public class APITest {

    @Test
    public void testGetApi() throws Exception {
        assertEquals(API.ITUNES,API.getApi("itunes"));

    }

    @Test
    public void testGetApiForNull() throws Exception {
        assertNull(API.getApi("RandomApi"));

    }

}