package com.mikeknep.dahomey.requests;

import com.mikeknep.dahomey.utils.MockSocketConnection;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {

    @Test
    public void itReceivesAFullRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("GET / HTTP/1.1\r\nKey: Value\r\nAnotherKey: AnotherValue\r\n\r\nBody".getBytes());

        assertEquals("GET / HTTP/1.1\r\nKey: Value\r\nAnotherKey: AnotherValue\r\n\r\nBody", Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesARequestWithNoBody() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("GET / HTTP/1.1\r\nKey: Value\r\nAnotherKey: AnotherValue\r\n\r\n".getBytes());

        assertEquals("GET / HTTP/1.1\r\nKey: Value\r\nAnotherKey: AnotherValue\r\n\r\n", Listener.receiveRawRequest(mockConnection.getIn()));
    }
}