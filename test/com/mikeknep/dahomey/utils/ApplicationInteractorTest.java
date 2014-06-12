package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ApplicationInteractorTest {
    ApplicationInteractor badAppInteractor;
    ApplicationInteractor goodAppInteractor;
    @Before
    public void instantiateInteractors() throws Exception {
        Request request = new Request("GET", "/nothere.html", new HashMap<String, String>(), "");
        badAppInteractor = new ApplicationInteractor("public/", request, "nothere.jar");
        goodAppInteractor = new ApplicationInteractor("public/", request, "public/mock.jar");
        badAppInteractor.runApplication();
        goodAppInteractor.runApplication();
    }

    @Test
    public void itReturns500StatusFromBadApp() {
        assertEquals("500 Internal Server Error", badAppInteractor.getStatus());
    }

    @Test
    public void itReturnsStatusFromGoodApp() {
        assertEquals("404 Not Found", goodAppInteractor.getStatus());
    }

    @Test
    public void itReturnsBlankHeadersFromBadApp() {
        assertEquals(new HashMap<String, String>(), badAppInteractor.getHeaders());
    }

    @Test
    public void itReturnsHeadersFromGoodApp() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/plain");

        assertEquals(expectedHeaders, goodAppInteractor.getHeaders());
    }

    @Test
    public void itReturnsBlankBodyFromBadApp() {
        assertArrayEquals("500".getBytes(), badAppInteractor.getBody());
    }

    @Test
    public void itReturnsBodyFromGoodApp() {
        assertArrayEquals("404".getBytes(), goodAppInteractor.getBody());
    }
}