import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RequestBuilderTest {
    public boolean requestsAreEquivalent(Request expected, Request actual) {
        return (expected.getMethod().equals(actual.getMethod()) &&
                expected.getResource().equals(actual.getResource()) &&
                expected.getVersion().equals(actual.getVersion()) &&
                expected.getHeaders().equals(actual.getHeaders()));
    }

    @Test
    public void itBuildsAFullRequest() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "text/html");
        headers.put("Accept-Ranges", "135");
        Request expectedRequest = new Request("GET", "/index.html", "HTTP/1.1", headers);

        String rawRequest = "GET /index.html HTTP/1.1\nContent-Type: text/html\nAccept-Ranges: 135";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }

    @Test
    public void itBuildsRequestWithNoHeaders() throws Exception {
        Request expectedRequest = new Request("GET", "/index.html", "HTTP/1.1", new HashMap<String, String>());
        String rawRequest = "GET /index.html HTTP/1.1\n";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }

    @Test
    public void itBuildsDeliberatelyInvalidRequest() throws Exception {
        Request expectedRequest = new Request("", "", "", new HashMap<String, String>());
        String rawRequest = "";

        assertTrue(requestsAreEquivalent(expectedRequest, RequestBuilder.buildRequest(rawRequest)));
    }
}