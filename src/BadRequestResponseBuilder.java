import java.util.HashMap;

/**
 * Created by mrk on 5/27/14.
 */
public class BadRequestResponseBuilder implements ResponseBuilder {
    public Response buildResponse() throws Exception {
        String version = "HTTP/1.1";
        String status = "400 Bad Request";
        byte[] body = "".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();

        return new Response(version, status, body, headers);
    }
}
