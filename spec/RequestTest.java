import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {
    Request request;

    @Before
    public void setUpRequest() {
        request = new Request("GET /index.html HTTP/1.1\nContent-Type: text/html\nAccept-Ranges: 135");
    }


    @Test
    public void itReturnsItsMethod() {
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
    public void itReturnsAHeader() {
        assertEquals("text/html", request.getHeader("Content-Type"));
    }

    @Test
    public void itReturnsAnotherHeader() {
        assertEquals("135", request.getHeader("Accept-Ranges"));
    }

    @Test
    public void itReturnsNullForNonexistentHeader() {
        assertEquals(null, request.getHeader("nonsense"));
    }
}