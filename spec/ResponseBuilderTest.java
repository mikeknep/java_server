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
        request.setResource("/sample_files/mock.html");
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
    public void itDeterminesContentTypeOfHTMLFile() throws Exception {
        assertEquals("text/html", ResponseBuilder.determineContentType("spec/sample_files", "/mock.html"));
    }

    @Test
    public void itDeterminesContentTypeOfGIF() throws Exception {
        assertEquals("image/gif", ResponseBuilder.determineContentType("spec/sample_files", "/mock.gif"));
    }

    @Test
    public void itDeterminesContentTypeOfJPEG() throws Exception {
        assertEquals("image/jpeg", ResponseBuilder.determineContentType("spec/sample_files", "/mock.jpg"));
    }

    @Test
    public void itReturnsResourceByteArrayWhenPresent() throws Exception {
        assertArrayEquals("Good morning, world!".getBytes(), ResponseBuilder.generateBody("spec/sample_files", "/mock.html"));
    }

    @Test
    public void itReturns404ByteArrayWhenResourceMissingBut404Present() throws Exception {
        assertArrayEquals("Oh no! 404!".getBytes(), ResponseBuilder.generateBody("spec/sample_files", "/not_here.html"));
    }

    @Test
    public void itReturnsUltraBasic404WhenResourceAnd404Missing() throws Exception {
        assertArrayEquals("404".getBytes(), ResponseBuilder.generateBody("spec", "/not_here.html"));
    }




    @Test
    public void itBuilds500Response() throws Exception {
        assertEquals("500 Internal Server Error", ResponseBuilder.build500Response("spec/sample_files").getStatus());
    }
}