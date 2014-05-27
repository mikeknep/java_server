import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestTest {
    Request request;

    @Before
    public void setUpRequest() {
        String method = "GET";
        String resource = "/index.html";
        String version = "HTTP/1.1";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");

        request = new Request(method, resource, version, headers);
    }

    @Test
    public void itSetsItsMethod() {
        assertEquals("GET", request.getMethod());
    }

    @Test
    public void itReturnsItsResource() {
        assertEquals("/index.html", request.getResource());
    }

    @Test
    public void itReturnsItsHTTPVersion() {
        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void itSetsHeadersAndReturnsSingleHeader() {
        assertEquals("text/html", request.getHeader("Content-Type"));
    }

    @Test
    public void itReturnsNullForNonexistentHeader() {
        assertEquals(null, request.getHeader("nonsense"));
    }
}