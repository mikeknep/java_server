import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

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
        String version = "HTTP/1.1";
        String status = "404 Not Found";
        byte[] body = "404".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", "3");
        headers.put("Content-Type", "text/plain");
        Response expectedResponse = new Response(version, status, body, headers);

        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec", 404);

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds404ResponseWith404File() {
        String version = "HTTP/1.1";
        String status = "404 Not Found";
        byte[] body = "Oh no! 404!".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "text/html");
        Response expectedResponse = new Response(version, status, body, headers);

        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec/sample_files", 404);

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds500ResponseWithout500File() {
        String version = "HTTP/1.1";
        String status = "500 Internal Server Error";
        byte[] body = "500".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", "3");
        headers.put("Content-Type", "text/plain");
        Response expectedResponse = new Response(version, status, body, headers);

        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec", 500);

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuilds500ResponseWith500File() {
        String version = "HTTP/1.1";
        String status = "500 Internal Server Error";
        byte[] body = "Uh oh. 500!".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", "11");
        headers.put("Content-Type", "text/html");
        Response expectedResponse = new Response(version, status, body, headers);

        ErrorResponseBuilder builder = new ErrorResponseBuilder("spec/sample_files", 500);

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}