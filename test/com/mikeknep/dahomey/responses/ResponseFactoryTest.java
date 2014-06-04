package com.mikeknep.dahomey.responses;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponseFactoryTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }

    @Test
    public void itCreatesAResponse() {
        Response expectedResponse = new Response("200 OK", new HashMap<String, String>(), "foobar".getBytes());
        assertTrue(responsesAreEquivalent(expectedResponse, ResponseFactory.buildResponse("200 OK", new HashMap<String, String>(), "foobar".getBytes())));
    }
}