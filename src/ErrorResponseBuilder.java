import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/21/14.
 */
public class ErrorResponseBuilder {
    private String rootDirectory;
    private int errorCode;
    private Path errorResourcePath;
    private Response response;

    public ErrorResponseBuilder(String directory, int errorCode) {
        this.rootDirectory = directory;
        this.errorCode = errorCode;
        this.errorResourcePath = Paths.get(rootDirectory + "/" + errorCode + ".html");
        this.response = new Response();
    }

    public Response buildResponse() {
        response.setVersion("HTTP/1.1");
        response.setStatus(errorCode + " " + reasonPhrase());
        response.setBody(generateErrorBody());
        response.setHeader("Content-Length", String.valueOf(this.response.getBody().length));
        response.setHeader("Content-Type", contentType());
        return response;
    }



    private String reasonPhrase() {
        switch (errorCode) {
            case 404:   return "Not Found";
            case 500:   return "Internal Server Error";
            default:    return "Internal Server Error";
        }
    }

    private byte[] generateErrorBody() {
        try {
            return Files.readAllBytes(errorResourcePath);
        } catch (IOException e) {
            return String.valueOf(errorCode).getBytes();
        }
    }

    private String contentType() {
        if (Files.exists(errorResourcePath)) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }
}
