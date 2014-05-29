import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.*;

public class ServerTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Before
    public void setUpOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpOutput() {
        System.setOut(null);
    }


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

        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new URL("http://localhost:2468/mock.html").openStream();
                }
                catch (Exception e) {}
            }
        });

        serverThread.start();
        Thread.sleep(100);
        clientThread.start();
        Thread.sleep(100);

        assertTrue(output.toString().contains("200 OK"));
    }
}