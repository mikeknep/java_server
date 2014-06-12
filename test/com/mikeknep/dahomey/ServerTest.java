package com.mikeknep.dahomey;

import com.mikeknep.dahomey.utils.MissingApplicationException;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Date;

import static org.junit.Assert.*;

public class ServerTest {

    @Test(expected = MissingApplicationException.class)
    public void itRequiresAnApplicationToBeInstantiated() throws Exception {
        Server server = new Server("public/", new ServerSocket(9876), null);
    }


    @Test
    public void itReceivesRequestAndSendsResponse() throws Exception {
        runServer(2468);
        Thread.sleep(100);

        Socket socket = new Socket("localhost", 2468);
        makeGETRequest(socket);
        String firstLine = readFirstLine(socket);
        socket.close();

        assertEquals("HTTP/1.1 200 OK", firstLine);
    }


    @Test
    public void itHandlesMultipleRequestsWithThreads() throws Exception {
        Thread slowClient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 9110);
                    Thread.sleep(3000);
                    makeGETRequest(socket);
                    socket.close();
                } catch (Exception e) {}
            }
        });

        runServer(9110);
        Thread.sleep(100);

        slowClient.start();
        Thread.sleep(100);

        Socket socket = new Socket("localhost", 9110);
        makeGETRequest(socket);
        String firstLine = readFirstLine(socket);
        socket.close();
        Date secondRequestCloseTime = new Date();

        assertEquals("HTTP/1.1 200 OK", firstLine);

        slowClient.join();
        Date firstRequestCloseTime = new Date();
        assertTrue(secondRequestCloseTime.before(firstRequestCloseTime));
    }



    private void runServer(final int port) {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    Server server = new Server("public/", serverSocket, "public/mock.jar");
                    server.run();
                }
                catch (Exception e) {}
            }
        });
        serverThread.start();
    }

    private void makeGETRequest(Socket socket) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("GET / HTTP/1.1\r");
        writer.println("\r");
        writer.flush();
    }

    private String readFirstLine(Socket socket) throws IOException {
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(isr);
        return bufferedReader.readLine();
    }
}