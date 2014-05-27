import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 5/21/14.
 */
public class ErrorResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private int errorCode;
    private Path errorResourcePath;

    public ErrorResponseBuilder(String directory, int errorCode) {
        this.rootDirectory = directory;
        this.errorCode = errorCode;
        this.errorResourcePath = Paths.get(rootDirectory + "/" + errorCode + ".html");
    }

    public Response buildResponse() {
        String version = "HTTP/1.1";
        String status = errorCode + " " + reasonPhrase();
        byte[] body = generateErrorBody();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", contentType());

        return new Response(version, status, body, headers);
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
