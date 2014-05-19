import org.junit.Test;

import static org.junit.Assert.*;

public class ResponderTest {
    @Test
    public void itSendsAResponse() throws Exception {
        Response response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody("Hello".getBytes());

        MockStreamPair mockStreamPair = new MockStreamPair("Hello".getBytes());
        Responder.sendResponse(response, mockStreamPair.getOut());

        assertEquals("HTTP/1.1 200 OK\n\nHello", mockStreamPair.getOut().toString());
    }
}