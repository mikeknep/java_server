import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ErrorResponseBuilderTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getVersion().equals(actualResponse.getVersion()) &&
                expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }

    @Test
    public void itBuilds404ResponseWithout404File() {
        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec", 404);
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("404 Not Found");
        expectedResponse.setBody("404".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf("404".getBytes().length));
        expectedResponse.setHeader("Content-Type", "text/plain");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds404ResponseWith404File() {
        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec/sample_files", 404);
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("404 Not Found");
        expectedResponse.setBody("Oh no! 404!".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf("Oh no! 404!".getBytes().length));
        expectedResponse.setHeader("Content-Type", "text/html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds500ResponseWithout500File() {
        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec", 500);
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("500 Internal Server Error");
        expectedResponse.setBody("500".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf("500".getBytes().length));
        expectedResponse.setHeader("Content-Type", "text/plain");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds500ResponseWith500File() {
        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec/sample_files", 500);
        Response expectedResponse = new Response();
        expectedResponse.setVersion("HTTP/1.1");
        expectedResponse.setStatus("500 Internal Server Error");
        expectedResponse.setBody("Uh oh. 500!".getBytes());
        expectedResponse.setHeader("Content-Length", String.valueOf("Uh oh. 500!".getBytes().length));
        expectedResponse.setHeader("Content-Type", "text/html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}