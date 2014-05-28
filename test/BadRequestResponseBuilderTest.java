import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class BadRequestResponseBuilderTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getVersion().equals(actualResponse.getVersion()) &&
                expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }

    @Test
    public void itBuildsResponseToBadRequest() throws Exception {
        Response expectedResponse = new Response("HTTP/1.1", "400 Bad Request", "".getBytes(), new HashMap<String, String>());
        BadRequestResponseBuilder builder = new BadRequestResponseBuilder();

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}