import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;

import static org.junit.Assert.*;

public class MainTest {
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
    public void itServes() throws Exception {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] args = {"-p", "2468"};
                try {
                    Main.main(args);
                }
                catch (Exception e) {}
            }
        });

        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new URL("http://localhost:2468/").openStream();
                }
                catch (Exception e) {}
            }
        });

        serverThread.start();
        Thread.sleep(100);
        clientThread.start();
        Thread.sleep(100);

        assertTrue(output.toString().contains("port 2468"));
        assertTrue(output.toString().contains("GET"));
    }
}