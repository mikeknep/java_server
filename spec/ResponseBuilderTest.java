import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseBuilderTest {
    Request request;
    String directory;
    @Before
    public void setUpRequest() {
        request = new Request();
        request.setMethod("GET");
        request.setResource("/mock.html");
        request.setVersion("HTTP/1.1");
        directory = "spec";
    }

    @Test
    public void itGenerates200Status() {
        assertEquals("200 OK", ResponseBuilder.generateStatus(directory, request.getResource()));
    }

    @Test
    public void itGenerates404Status() {
        assertEquals("404 Not Found", ResponseBuilder.generateStatus("nonexistent/directory", request.getResource()));
    }

    @Test
    public void itBuildsResponse() throws Exception {
        Response response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody("Good morning, world!");

        assertEquals(response.getBody(), ResponseBuilder.buildResponse(directory, request).getBody());
    }

    @Test
    public void itGeneratesBodyDataFromPresentResource() throws Exception {
        assertArrayEquals("Good morning, world!".getBytes(), ResponseBuilder.generateBodyData("spec", "/mock.html"));
    }

    @Test
    public void itGeneratesBodyDataFrom404ForMissingResource() throws Exception {
        assertArrayEquals("Oh no! 404!".getBytes(), ResponseBuilder.generateBodyData("spec", "/404.html"));
    }

    @Test
    public void itBuilds500Response() throws Exception {
        assertEquals("500 Internal Server Error", ResponseBuilder.build500Response().getStatus());
    }
}