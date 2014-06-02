package com.mikeknep.dahomey.requests;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestValidatorTest {
    @Test
    public void itPassesAValidRequest() {
        Request request = new Request("GET", "/mock.html", "HTTP/1.1", new HashMap<String, String>());

        assertFalse(RequestValidator.isInvalidRequest(request));
    }

    @Test
    public void itFailsAnInvalidRequest() {
        Request request = new Request("", "", "", new HashMap<String, String>());

        assertTrue(RequestValidator.isInvalidRequest(request));
    }
}