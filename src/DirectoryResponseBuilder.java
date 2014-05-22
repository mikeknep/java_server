import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/21/14.
 */
public class DirectoryResponseBuilder implements ResponseBuilder {
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

        builder.append("<ul>");
        for (File file : files) {
            builder.append("<li>" + "<a href=\"/" + getRelativePath(file) + "\">" + file.getName() + "</a>" + "</li>");
        }
        builder.append("</ul>");

        return builder.toString().getBytes();
    }

    private String getRelativePath(File file) {
        return file.getPath().split(rootDirectory)[1];
    }
}
