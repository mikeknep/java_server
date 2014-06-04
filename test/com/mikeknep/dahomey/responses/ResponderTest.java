package com.mikeknep.dahomey.responses;

import com.mikeknep.dahomey.utils.MockSocketConnection;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ResponderTest {
    @Test
    public void itSendsAResponse() throws Exception {
        Response response = new Response("200 OK", new HashMap<String, String>(), "Hello".getBytes());

        MockSocketConnection mockConnection = new MockSocketConnection("Hello".getBytes());
        Responder.sendResponse(response, mockConnection.getOut());

        Assert.assertEquals("HTTP/1.1 200 OK\n\nHello", mockConnection.getOut().toString());
    }
}