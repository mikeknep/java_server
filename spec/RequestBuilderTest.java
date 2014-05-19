import org.junit.Test;

import static org.junit.Assert.*;

public class RequestBuilderTest {
    @Test
    public void itBuildsARequest() throws Exception {
        String rawRequest = "GET /index.html HTTP/1.1\nContent-Type: text/html\nAccept-Ranges: 135";

        Request request = new Request();
        request.setMethod("GET");
        request.setResource("/index.html");
        request.setVersion("HTTP/1.1");
        request.setHeader("Content-Type", "text/html");
        request.setHeader("Accept-Ranges", "135");

        assertEquals(request.getMethod(), RequestBuilder.buildRequest(rawRequest).getMethod());
        assertEquals(request.getResource(), RequestBuilder.buildRequest(rawRequest).getResource());
        assertEquals(request.getVersion(), RequestBuilder.buildRequest(rawRequest).getVersion());
        assertEquals(request.getHeader("Content-Type"), RequestBuilder.buildRequest(rawRequest).getHeader("Content-Type"));
        assertEquals(request.getHeader("Accept-Ranges"), RequestBuilder.buildRequest(rawRequest).getHeader("Accept-Ranges"));
    }
}