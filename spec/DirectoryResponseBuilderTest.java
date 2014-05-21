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
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("200 OK");
        expectedResponse.setBody("404.html\n500.html\nmock.gif\nmock.html\nmock.jpg\n".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf(expectedResponse.getBody().length));
        expectedResponse.setHeader("Content-Type", "text/html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}