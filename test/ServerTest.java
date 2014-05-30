import org.junit.Test;

import java.io.*;
import java.net.*;

import static org.junit.Assert.*;

public class ServerTest {
    @Test
    public void itReceivesRequestAndSendsResponse() throws Exception {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket= new ServerSocket(2468);
                    Server server = new Server("test/sample_files", serverSocket);
                    server.run();
                }
                catch (Exception e) {}
            }
        });

        serverThread.start();
        Thread.sleep(100);
        Socket socket = new Socket("localhost", 2468);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println("GET / HTTP/1.1");
        writer.println("");
        writer.flush();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(isr);
        String firstLine = bufferedReader.readLine();
        socket.close();

        assertEquals("HTTP/1.1 200 OK", firstLine);
    }
}