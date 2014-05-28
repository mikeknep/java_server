import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 5/21/14.
 */
public class FileResponseBuilder implements ResponseBuilder {
    private Path resourcePath;

    public FileResponseBuilder(String rootDirectory, String requestedResource) {
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
    }

    public Response buildResponse() throws Exception {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = generateBody();
        HashMap<String, String> headers = generateHeaders(body);

        return new Response(version, status, body, headers);
    }

    private byte[] generateBody() throws Exception {
        return Files.readAllBytes(resourcePath);
    }

    private HashMap<String, String> generateHeaders(byte[] body) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", determineContentType());
        return headers;
    }

    private String determineContentType() {
        return URLConnection.guessContentTypeFromName(String.valueOf(resourcePath));
    }
}
