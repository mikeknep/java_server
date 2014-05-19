import org.junit.Test;

import static org.junit.Assert.*;

public class ResponsePresenterTest {
    @Test
    public void itGeneratesFullResponseByteArray() {
        Response response = new Response();
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody("Hello world".getBytes());
        response.setHeader("Content-Length", "135");
        response.setHeader("Content-Type", "text/html");

        byte[] expectedFullResponseByteArray = "HTTP/1.1 200 OK\nContent-Length: 135\nContent-Type: text/html\n\nHello world".getBytes();

        assertArrayEquals(expectedFullResponseByteArray, ResponsePresenter.generateFullResponseByteArray(response));
    }
}