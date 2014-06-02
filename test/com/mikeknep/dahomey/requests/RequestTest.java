package com.mikeknep.dahomey.requests;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestTest {
    Request request;

    @Before
    public void setUpRequest() {
        String method = "GET";
        String resource = "/index.html";
        String version = "HTTP/1.1";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        headers.put("Accept-Ranges", "135");

        request = new Request(method, resource, version, headers);
    }

    @Test
    public void itSetsItsMethod() {
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void itReturnsItsResource() {
        assertEquals("/index.html", request.getResource());
    }

    @Test
    public void itReturnsItsHTTPVersion() {
        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void itReturnsAllHeaders() {
        HashMap<String, String> expectedHeaders = new HashMap<String, String>();
        expectedHeaders.put("Content-Type", "text/html");
        expectedHeaders.put("Accept-Ranges", "135");
        assertEquals(expectedHeaders, request.getHeaders());
    }

    @Test
    public void itReturnsSingleHeader() {
        assertEquals("text/html", request.getHeader("Content-Type"));
    }

    @Test
    public void itReturnsNullForNonexistentHeader() {
        assertEquals(null, request.getHeader("nonsense"));
    }
}