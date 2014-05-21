import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DirectoryResponseBuilderTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getVersion().equals(actualResponse.getVersion()) &&
                expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }


    @Test
    public void itBuildsDirectoryResponse() {
        DirectoryResponseBuilder builder = new DirectoryResponseBuilder("spec", "/sample_files");
        String body = "<ul><li><a href=\"/sample_files/404.html\">404.html</a></li><li><a href=\"/sample_files/500.html\">500.html</a></li><li><a href=\"/sample_files/mock.gif\">mock.gif</a></li><li><a href=\"/sample_files/mock.html\">mock.html</a></li><li><a href=\"/sample_files/mock.jpg\">mock.jpg</a></li></ul>";
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("200 OK");
        expectedResponse.setBody(body.getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf(expectedResponse.getBody().length));
        expectedResponse.setHeader("Content-Type", "text/html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}