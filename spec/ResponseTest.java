import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponseTest {
    Response response;
    @Before
    public void setUpResponse() {
        response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody("Hello world".getBytes());
        response.setHeader("Content-Type", "text/html");
    }

    @Test
    public void itReturnsHTTPVersion() {
        assertEquals("HTTP/1.1", response.getVersion());
    }

    @Test
    public void itReturnsItsStatus() {
        assertEquals("200 OK", response.getStatus());
    }

    @Test
    public void itReturnsBodyData() { assertArrayEquals("Hello world".getBytes(), response.getBody()); }

    @Test
    public void itReturnsItsHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");

        assertEquals(headers, response.getHeaders());
    }
}