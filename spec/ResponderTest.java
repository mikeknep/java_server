import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ResponderTest {

    @Test
    public void itFormatsCompleteResponse() {
        Responder responder = new Responder();

        assertEquals("HTTP/1.1 200 OK\n\nCan you see me?", responder.formatResponse("HTTP/1.1 200 OK", "Can you see me?"));
    }

    @Test
    public void itLocatesAFile() {
        Responder responder = new Responder();
        String directory = "spec";
        String resource = "/mock.html";

        assertEquals(new File("spec/mock.html"), responder.locateFile(directory, resource));
    }

    @Test
    public void itReturnsIndexForRootRequest() {
        Responder responder = new Responder();
        String directory = "spec";
        String resource = "/";

        assertEquals(new File("spec/index.html"), responder.locateFile(directory, resource));
    }

    @Test
    public void itReadsAFile() throws Exception {
        Responder responder = new Responder();
        File file = new File("spec/mock.html");

        assertEquals("Good morning, world!", responder.readFile(file));
    }
}