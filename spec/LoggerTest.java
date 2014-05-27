import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

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

    @Test
    public void itLogsRequestResponseTransaction() {
        Request request = new Request("GET", "/mock.html", "HTTP/1.1", null);

        Response response = new Response();
        response.setStatus("200 OK");

        Date socketOpenTime = new Date();

        Logger.logBasic(request, response, socketOpenTime);

        assertEquals(socketOpenTime.toString() + " 'GET /mock.html HTTP/1.1' 200 OK\n", output.toString());
    }
}