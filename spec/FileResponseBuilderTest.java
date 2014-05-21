import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileResponseBuilderTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getVersion().equals(actualResponse.getVersion()) &&
                expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }


    @Test
    public void itBuildsHTMLFileResponse() throws Exception {
        FileResponseBuilder builder = new FileResponseBuilder("spec/sample_files", "/mock.html");
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("200 OK");
        expectedResponse.setBody("Good morning, world!".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf("Good morning, world!".getBytes().length));
        expectedResponse.setHeader("Content-Type", "text/html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuildsJPGFileResponse() throws Exception {
        FileResponseBuilder builder = new FileResponseBuilder("spec/sample_files", "/mock.jpg");
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("200 OK");
        expectedResponse.setBody(Files.readAllBytes(Paths.get("spec/sample_files/mock.jpg")));
        expectedResponse.setHeader("Content-Length", String.valueOf(expectedResponse.getBody().length));
        expectedResponse.setHeader("Content-Type", "image/jpeg");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuildsGIFFileResponse() throws Exception {
        FileResponseBuilder builder = new FileResponseBuilder("spec/sample_files", "/mock.gif");
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("200 OK");
        expectedResponse.setBody(Files.readAllBytes(Paths.get("spec/sample_files/mock.gif")));
        expectedResponse.setHeader("Content-Length", String.valueOf(expectedResponse.getBody().length));
        expectedResponse.setHeader("Content-Type", "image/gif");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}