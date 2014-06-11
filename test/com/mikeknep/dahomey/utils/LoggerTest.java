package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.responses.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.*;

public class LoggerTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Before
    public void setUpOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpOutput() {
        System.setOut(stdout);
    }


    @Test
    public void itLogsAStartupMessage() {
        Logger.logStartup(8399, "/some/dir/", "someapp.jar");

        assertTrue(output.toString().contains("8399"));
        assertTrue(output.toString().contains("/some/dir/"));
        assertTrue(output.toString().contains("someapp.jar"));
    }

    @Test
    public void itLogsRequestResponseTransaction() {
        Request request = new Request("GET", "/mock.html", null, "");
        Response response = new Response("200 OK", null, "Hello".getBytes());
        Date socketOpenTime = new Date();

        Logger.logBasic(request, response, socketOpenTime);

        assertEquals(socketOpenTime.toString() + " 'GET /mock.html HTTP/1.1' 200 OK\n", output.toString());
    }
}