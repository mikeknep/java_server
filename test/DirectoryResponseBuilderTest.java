import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

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
        String version = "HTTP/1.1";
        String status = "200 OK";
        String bodyString = "<ul><li><a href=\"//sample_files/404.html\">404.html</a></li><li><a href=\"//sample_files/500.html\">500.html</a></li><li><a href=\"//sample_files/mock.gif\">mock.gif</a></li><li><a href=\"//sample_files/mock.html\">mock.html</a></li><li><a href=\"//sample_files/mock.jpg\">mock.jpg</a></li></ul>";
        byte[] body = bodyString.getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "text/html");
        Response expectedResponse = new Response(version, status, body, headers);

        DirectoryResponseBuilder builder = new DirectoryResponseBuilder("spec", "/sample_files");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}