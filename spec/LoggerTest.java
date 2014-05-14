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
        Logger logger = new Logger();
        logger.log("Hello world");

        assertEquals("Hello world\n", output.toString());
    }

    @Test
    public void itLogsAStartupMessage() {
        Logger logger = new Logger();
        logger.startupMessage(8399, "/Users/mrk/Desktop");

        assertEquals("Server initialized on port 8399, serving directory /Users/mrk/Desktop\n", output.toString());
    }

    @Test
    public void itLogsIgnoredException() {
        Logger logger = new Logger();
        logger.logException(new Exception(), "ignore");

        assertEquals("Exception caught: java.lang.Exception. Ignored.\n", output.toString());
    }

    @Test
    public void itLogs500Exception() {
        Logger logger = new Logger();
        logger.logException(new Exception(), "500");

        assertEquals("Exception caught: java.lang.Exception. 500 response sent.\n", output.toString());
    }
}