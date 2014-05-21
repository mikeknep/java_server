import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/21/14.
 */
public class FileResponseBuilder implements Builder {
    private String rootDirectory;
    private String requestedResource;
    private Path resourcePath;
    private Response response;

    public FileResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
        this.response = new Response();
    }

    public Response buildResponse() throws Exception {
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody(generateBody());
        response.setHeader("Content-Length", String.valueOf(response.getBody().length));
        response.setHeader("Content-Type", determineContentType());
        return response;
    }

    private byte[] generateBody() throws Exception {
        return Files.readAllBytes(resourcePath);
    }

    private String determineContentType() {
        return URLConnection.guessContentTypeFromName(String.valueOf(resourcePath));
    }
}
