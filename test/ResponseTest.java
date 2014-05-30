import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponseTest {
    Response response;
    @Before
    public void setUpResponse() {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = "Hello world".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        headers.put("Content-Length", "11");

        response = new Response(version, status, body, headers);
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
    public void itReturnsBodyData() {
        assertArrayEquals("Hello world".getBytes(), response.getBody());
    }

    @Test
    public void itReturnsItsHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        headers.put("Content-Length", "11");

        assertEquals(headers, response.getHeaders());
    }
}