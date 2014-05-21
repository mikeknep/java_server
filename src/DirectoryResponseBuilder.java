import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/21/14.
 */
public class DirectoryResponseBuilder implements Builder {
    private String rootDirectory;
    private String requestedResource;
    private Path resourcePath;
    private Response response;

    public DirectoryResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.requestedResource = requestedResource;
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
        this.response = new Response();
    }

    public Response buildResponse() {
        response.setVersion("HTTP/1.1");
        response.setStatus("200 OK");
        response.setBody(generateBody());
        response.setHeader("Content-Length", String.valueOf(response.getBody().length));
        response.setHeader("Content-Type", "text/html");
        return response;
    }

    private byte[] generateBody() {
        StringBuilder builder = new StringBuilder();
        File[] files = resourcePath.toFile().listFiles();

        for (File file : files) {
            builder.append(file.getName() + "\n");
        }

        return builder.toString().getBytes();
    }
}
