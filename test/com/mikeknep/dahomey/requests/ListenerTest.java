package com.mikeknep.dahomey.requests;

import com.mikeknep.dahomey.utils.MockSocketConnection;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListenerTest {

    @Test
    public void itReceivesRawRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("First line\nSecond line\nThird line".getBytes());

        assertEquals("First line\nSecond line\nThird line\n", Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesBadRawRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("Incomplete   \n".getBytes());

        assertEquals("Incomplete   \n", Listener.receiveRawRequest(mockConnection.getIn()));
    }

    @Test
    public void itReceivesPhantomRequest() throws Exception {
        MockSocketConnection mockConnection = new MockSocketConnection("".getBytes());

        assertEquals("", Listener.receiveRawRequest(mockConnection.getIn()));
    }
}