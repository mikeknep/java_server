package com.mikeknep.dahomey.requests;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestBuilderTest {
    public boolean requestsAreEquivalent(Request expected, Request actual) {
        return (expected.getMethod().equals(actual.getMethod()) &&
                expected.getResource().equals(actual.getResource()) &&
                expected.getHeaders().equals(actual.getHeaders()) &&
                expected.getBody().equals(actual.getBody()));
    }

    @Test
    public void itBuildsAFullRequest() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        headers.put("Accept-Ranges", "135");
        Request expectedRequest = new Request("GET", "/index.html", headers, "body");
        String rawRequest = "GET /index.html HTTP/1.1\r\nContent-Type: text/html\r\nAccept-Ranges: 135\r\n\r\nbody";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }

    @Test
    public void itBuildsRequestWithNoHeaders() throws Exception {
        Request expectedRequest = new Request("GET", "/index.html", new HashMap<String, String>(), "body");
        String rawRequest = "GET /index.html HTTP/1.1\r\n\r\nbody";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }

    @Test
    public void itBuildsDeliberatelyInvalidRequestWhenPhantom() throws Exception {
        Request expectedRequest = new Request("", "", new HashMap<String, String>(), "");
        String rawRequest = "\r\n";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }

    @Test
    public void itBuildsDeliberatelyInvalidRequestWhenPartial() throws Exception {
        Request expectedRequest = new Request("", "", new HashMap<String, String>(), "");
        String rawRequest = "GET \r\n";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }
}