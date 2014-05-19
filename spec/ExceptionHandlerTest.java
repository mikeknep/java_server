import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ExceptionHandlerTest {
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
    public void itLogsAllExceptions() throws Exception {
        Exception exception = new Exception();
        MockStreamPair mockStreamPair = new MockStreamPair("Hello".getBytes());
        ExceptionHandler.handle(exception, mockStreamPair.getOut(), "spec/sample_files");

        assertTrue(output.toString().contains("Exception"));
    }

    @Test
    public void itIgnoresPhantomRequest() throws Exception {
        PhantomRequestException phantom = new PhantomRequestException();
        MockStreamPair mockStreamPair = new MockStreamPair("Hello".getBytes());
        ExceptionHandler.handle(phantom, mockStreamPair.getOut(), "spec/sample_files");

        assertFalse(mockStreamPair.getOut().toString().contains("500 Internal Server Error"));
    }

    @Test
    public void itSends500ResponseForSomeExceptions() throws Exception {
        Exception exception = new Exception();
        MockStreamPair mockStreamPair = new MockStreamPair("Hello".getBytes());
        ExceptionHandler.handle(exception, mockStreamPair.getOut(), "spec/sample_files");

        assertTrue(mockStreamPair.getOut().toString().contains("500 Internal Server Error"));
    }
}