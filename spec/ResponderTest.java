import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ResponderTest {

    @Test
    public void itFormatsCompleteResponse() {
        Responder responder = new Responder();

        assertEquals("HTTP/1.1 200 OK\n\nCan you see me?", responder.formatResponse("HTTP/1.1 200 OK", "Can you see me?"));
    }
}