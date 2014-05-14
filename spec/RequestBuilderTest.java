import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestBuilderTest {
    String rawRequest;
    String requestLine;
    @Before
    public void setUpRawRequest() {
        rawRequest = "GET /index.html HTTP/1.1\nContent-Type: text/html\nAccept-Ranges: 135";
        requestLine = RequestBuilder.parseRequestLine(rawRequest);
    }

    @Test
    public void itParsesRequestLine() {
        assertEquals("GET /index.html HTTP/1.1", RequestBuilder.parseRequestLine(rawRequest));
    }

    @Test
    public void itParsesRequestMethod() {
        assertEquals("GET", RequestBuilder.parseRequestMethod(requestLine));
    }

    @Test
    public void itParsesRequestResource() {
        assertEquals("/index.html", RequestBuilder.parseRequestResource(requestLine));
    }

    @Test
    public void itParsesRequestVersion() {
        assertEquals("HTTP/1.1", RequestBuilder.parseRequestVersion(requestLine));
    }

    @Test
    public void itParsesRequestHeaders() {
        String[] headers = {"Content-Type: text/html", "Accept-Ranges: 135"};

        assertArrayEquals(headers, RequestBuilder.parseRequestHeaders(rawRequest));
    }

    @Test
    public void itBuildsARequest() {
        Request request = new Request();
        request.setMethod("GET");
        request.setResource("/index.html");
        request.setVersion("HTTP/1.1");
        request.setHeader("Content-Type", "text/html");
        request.setHeader("Accept-Ranges", "135");

        assertEquals(request.getVersion(), RequestBuilder.buildRequest(rawRequest).getVersion());
    }
}