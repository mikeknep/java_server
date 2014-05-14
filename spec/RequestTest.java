import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    Request request;

    @Before
    public void setUpRequest() {
        request = new Request();
    }

    @Test
    public void itSetsItsMethod() {
        request.setMethod("GET");

        assertEquals("GET", request.getMethod());
    }

    @Test
    public void itReturnsItsResource() {
        request.setResource("/index.html");

        assertEquals("/index.html", request.getResource());
    }

    @Test
    public void itReturnsItsHTTPVersion() {
        request.setVersion("HTTP/1.1");

        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void itSetsHeadersAndReturnsSingleHeader() {
        request.setHeader("Content-Type", "text/html");

        assertEquals("text/html", request.getHeader("Content-Type"));
    }

    @Test
    public void itReturnsNullForNonexistentHeader() {
        assertEquals(null, request.getHeader("nonsense"));
    }
}