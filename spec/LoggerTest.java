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
}