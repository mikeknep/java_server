import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponderTest {
    @Test
    public void itSendsAResponse() throws Exception {
        Response response = new Response("HTTP/1.1", "200 OK", "Hello".getBytes(), new HashMap<String, String>());

        MockStreamPair mockStreamPair = new MockStreamPair("Hello".getBytes());
        Responder.sendResponse(response, mockStreamPair.getOut());

        assertEquals("HTTP/1.1 200 OK\n\nHello", mockStreamPair.getOut().toString());
    }
}