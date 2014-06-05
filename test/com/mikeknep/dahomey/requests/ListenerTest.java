package com.mikeknep.dahomey.requests;

import com.mikeknep.dahomey.utils.MockSocketConnection;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {

    @Test
    public void itReceivesRawRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("First line\nSecond line\nThird line".getBytes());
        ArrayList<String> expectedRawRequest = new ArrayList<String>();
        expectedRawRequest.add("First line\nSecond line\nThird line\n");
        expectedRawRequest.add("");

        assertEquals(expectedRawRequest, Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesBadRawRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("Incomplete   \n".getBytes());
        ArrayList<String> expectedRawRequest = new ArrayList<String>();
        expectedRawRequest.add("Incomplete   \n");
        expectedRawRequest.add("");

        assertEquals(expectedRawRequest, Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesPhantomRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("".getBytes());
        ArrayList<String> expectedRawRequest = new ArrayList<String>();
        expectedRawRequest.add("");
        expectedRawRequest.add("");

        assertEquals(expectedRawRequest, Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesRequestWithBody() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("GET / HTTP/1.1\nContent-Length: 6\n\nfoobar".getBytes());
        ArrayList<String> expectedRawRequest = new ArrayList<String>();
        expectedRawRequest.add("GET / HTTP/1.1\nContent-Length: 6\n");
        expectedRawRequest.add("foobar");

        assertEquals(expectedRawRequest, Listener.receiveRawRequest(mockConnection.getIn()));
    }
}