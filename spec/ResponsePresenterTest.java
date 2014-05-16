import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponsePresenterTest {
    Response response;
    @Before
    public void setUpResponse() {
        response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody("Hello world".getBytes());
        response.setHeader("Content-Length", "135");
        response.setHeader("Content-Type", "text/html");
    }

    @Test
    public void itGeneratesAStatusLine() {
        assertEquals("HTTP/1.1 200 OK\n", ResponsePresenter.generateStatusLine(response));
    }

    @Test
    public void itGeneratesHeadersAsStrings() {
        assertEquals("Content-Length: 135\nContent-Type: text/html\n", ResponsePresenter.generateHeaders(response));
    }

    @Test
    public void itGeneratesFullResponseByteArray() {
        byte[] fullResponseByteArray = "HTTP/1.1 200 OK\nContent-Length: 135\nContent-Type: text/html\n\nHello world".getBytes();
        assertEquals(fullResponseByteArray, ResponsePresenter.generateFullResponseByteArray(response));
    }
}