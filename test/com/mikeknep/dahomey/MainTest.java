package com.mikeknep.dahomey;

import org.junit.Test;

import java.io.*;
import java.net.*;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void itServes() throws Exception {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] args = {"-p", "2468", "-a", "public/mock.jar"};
                try {
                    Main.main(args);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        serverThread.start();
        Thread.sleep(100);
        Socket socket = new Socket("localhost", 2468);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("GET / HTTP/1.1\r\n\r\n");
        writer.flush();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(isr);
        String firstLine = bufferedReader.readLine();
        socket.close();

        assertEquals("HTTP/1.1 200 OK", firstLine);
    }
}