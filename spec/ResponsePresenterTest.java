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
        response.setBody("Hello world");
    }

    @Test
    public void itGeneratesAStatusLine() {
        assertEquals("HTTP/1.1 200 OK", ResponsePresenter.generateStatusLine(response));
    }

    @Test
    public void itPresentsAResponseAsASingleString() {
        assertEquals("HTTP/1.1 200 OK\n\nHello world", ResponsePresenter.generateFullResponse(response));
    }
}