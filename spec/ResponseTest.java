import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {
    Response response;
    @Before
    public void setUpResponse() {
        response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
    }

    @Test
    public void itReturnsHTTPVersion() {
        assertEquals("HTTP/1.1", response.getVersion());
    }

    @Test
    public void itReturnsItsStatus() {
        assertEquals("200 OK", response.getStatus());
    }
}