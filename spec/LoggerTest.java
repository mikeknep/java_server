import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LoggerTest {
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
    public void itLogsAMessageToSystemOut() {
        Logger.log("Hello world");

        assertEquals("Hello world\n", output.toString());
    }

    @Test
    public void itLogsAStartupMessage() {
        Logger.logStartup(8399, "/Users/mrk/Desktop");

        assertTrue(output.toString().contains("8399") && output.toString().contains("Desktop"));
    }

    @Test
    public void itLogsIgnoredException() {
        Logger.logException(new Exception(), "ignore");

        assertTrue(output.toString().contains("Ignored"));
    }

    @Test
    public void itLogs500Exception() {
        Logger.logException(new Exception(), "500");

        assertTrue(output.toString().contains("500"));
    }
}