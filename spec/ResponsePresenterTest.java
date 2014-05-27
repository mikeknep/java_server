import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class ResponsePresenterTest {
    @Test
    public void itPresentsFullResponseByteArray() {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = "Hello world".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", "135");
        headers.put("Content-Type", "text/html");
        Response response = new Response(version, status, body, headers);

        byte[] expectedFullResponseByteArray = "HTTP/1.1 200 OK\nContent-Length: 135\nContent-Type: text/html\n\nHello world".getBytes();

        assertArrayEquals(expectedFullResponseByteArray, ResponsePresenter.presentFullResponseByteArray(response));
    }
}