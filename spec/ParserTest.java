import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void itReturnsStringFromArrayListContainingSubstring() {
        Parser parser = new Parser();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Not this one");
        strings.add("We want this");
        strings.add("Not this either");

        assertEquals("We want this", parser.getLineWith("want", strings));
    }

    @Test
    public void itReturnsAnEmptyStringIfNoMatch() {
        Parser parser = new Parser();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("foo");
        strings.add("bar");

        assertEquals("", parser.getLineWith("Hello world!", strings));
    }

    @Test
    public void itParsesRequestType() {
        Parser parser = new Parser();
        String requestLine = "GET /index.html HTTP/1.1";

        assertEquals("GET", parser.parseRequestType(requestLine));
    }

    @Test
    public void itParsesRequestedResource() {
        Parser parser = new Parser();
        String requestLine = "GET /index.html HTTP/1.1";

        assertEquals("/index.html", parser.parseRequestedResource(requestLine));
    }

    @Test
    public void itParsesHTTPVersion() {
        Parser parser = new Parser();
        String requestLine = "GET /index.html HTTP/1.1";

        assertEquals("HTTP/1.1", parser.parseHTTPVersion(requestLine));
    }

}