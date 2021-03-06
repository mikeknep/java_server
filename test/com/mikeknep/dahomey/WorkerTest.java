package com.mikeknep.dahomey;

import com.mikeknep.dahomey.Worker;
import com.mikeknep.dahomey.utils.MockSocketConnection;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {
    @Test
    public void itAcceptsRequestAndSendsResponse() {
        MockSocketConnection mockConnection = new MockSocketConnection("GET / HTTP/1.1\r\n\r\n".getBytes());
        Worker worker = new Worker(mockConnection, "public/", "public/mock.jar");
        worker.run();

        assertTrue(mockConnection.getOut().toString().contains("200 OK"));
    }
}