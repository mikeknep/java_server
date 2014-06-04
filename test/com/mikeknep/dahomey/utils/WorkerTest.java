package com.mikeknep.dahomey.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {
    @Test
    public void itAcceptsRequestAndSendsResponse() {
        MockSocketConnection mockConnection = new MockSocketConnection("GET / HTTP/1.1\n".getBytes());
        Worker worker = new Worker(mockConnection, "public/", "public/mock.jar");
        worker.run();

        assertTrue(mockConnection.getOut().toString().contains("200 OK"));
    }
}