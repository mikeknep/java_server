import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {
    Parser parser = new Parser();
    String requestLine = "GET /index.html HTTP/1.1";

    @Test
    public void itReturnsNthElementFromRequest() {
        assertEquals("HTTP/1.1", parser.parseRequestLine(requestLine, 2));
    }

    @Test
    public void itParsesRequestMethod() {
        assertEquals("GET", parser.parseRequestMethod(requestLine));
    }

    @Test
    public void itParsesRequestedResource() {
        assertEquals("/index.html", parser.parseRequestedResource(requestLine));
    }

    @Test
    public void itParsesHTTPVersion() {
        assertEquals("HTTP/1.1", parser.parseHTTPVersion(requestLine));
    }

}