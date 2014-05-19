import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ResponseBuilderTest {
    Request goodRequest;
    Request badRequest;
    @Before
    public void setUpRequests() {
        goodRequest = new Request();
        goodRequest.setMethod("GET");
        goodRequest.setResource("/mock.html");
        goodRequest.setVersion("HTTP/1.1");

        badRequest = new Request();
        badRequest.setMethod("GET");
        badRequest.setResource("/nonexistent.html");
        badRequest.setVersion("HTTP/1.1");
    }

    public boolean responsesAreEquivalent(Response testResponse, Response builtResponse) {
        return (testResponse.getVersion().equals(builtResponse.getVersion()) &&
                testResponse.getStatus().equals(builtResponse.getStatus()) &&
                testResponse.getHeaders().equals(builtResponse.getHeaders()) &&
                Arrays.equals(testResponse.getBody(), builtResponse.getBody()));
    }


    @Test
    public void itBuilds200Response() throws Exception {
        Response testResponse = new Response();
        testResponse.setVersion("HTTP/1.1");
        testResponse.setStatus("200 OK");
        testResponse.setBody("Good morning, world!".getBytes());
        testResponse.setHeader("Content-Length", String.valueOf("Good morning, world!".getBytes().length));
        testResponse.setHeader("Content-Type", "text/html");

        Response builtResponse = ResponseBuilder.buildResponse("spec/sample_files", goodRequest);

        assertTrue(responsesAreEquivalent(testResponse, builtResponse));
    }

    @Test
    public void itBuilds404ResponseWithResource() throws Exception {
        Response testResponse = new Response();
        testResponse.setVersion("HTTP/1.1");
        testResponse.setStatus("404 Not Found");
        testResponse.setBody("Oh no! 404!".getBytes());
        testResponse.setHeader("Content-Length", String.valueOf("Oh no! 404!".getBytes().length));
        testResponse.setHeader("Content-Type", "text/html");

        Response builtResponse = ResponseBuilder.buildResponse("spec/sample_files", badRequest);

        assertTrue(responsesAreEquivalent(testResponse, builtResponse));
    }

    @Test
    public void itBuilds404ResponseWithoutResource() throws Exception {
        Response testResponse = new Response();
        testResponse.setVersion("HTTP/1.1");
        testResponse.setStatus("404 Not Found");
        testResponse.setBody("404".getBytes());
        testResponse.setHeader("Content-Length", String.valueOf("404".getBytes().length));
        testResponse.setHeader("Content-Type", "text/html");

        Response builtResponse = ResponseBuilder.buildResponse("spec", badRequest);

        assertTrue(responsesAreEquivalent(testResponse, builtResponse));
    }

    @Test
    public void itBuilds500ResponseWithResource() throws Exception {
        Response testResponse = new Response();
        testResponse.setVersion("HTTP/1.1");
        testResponse.setStatus("500 Internal Server Error");
        testResponse.setBody("Uh oh. 500!".getBytes());
        testResponse.setHeader("Content-Length", String.valueOf("Uh oh. 500!".getBytes().length));
        testResponse.setHeader("Content-Type", "text/html");

        Response builtResponse = ResponseBuilder.build500Response("spec/sample_files");

        assertTrue(responsesAreEquivalent(testResponse, builtResponse));
    }

    @Test
    public void itBuilds500ResponseWithoutResource() throws Exception {
        Response testResponse = new Response();
        testResponse.setVersion("HTTP/1.1");
        testResponse.setStatus("500 Internal Server Error");
        testResponse.setBody("500".getBytes());
        testResponse.setHeader("Content-Length", String.valueOf("500".getBytes().length));
        testResponse.setHeader("Content-Type", "text/html");

        Response builtResponse = ResponseBuilder.build500Response("spec");

        assertTrue(responsesAreEquivalent(testResponse, builtResponse));
    }
}