import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 5/27/14.
 */
public class MissingResourceResponseBuilder implements ResponseBuilder {
    private Path filepath;

    public MissingResourceResponseBuilder(String rootDirectory) {
        this.filepath = Paths.get(rootDirectory + "/404.html");
    }

    public Response buildResponse() {
        String version = "HTTP/1.1";
        String status = "404 Not Found";
        byte[] body = generateErrorBody();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", determineContentType());

        return new Response(version, status, body, headers);
    }

    private byte[] generateErrorBody() {
        try {
            return Files.readAllBytes(filepath);
        } catch (IOException e) {
            return "404".getBytes();
        }
    }

    private String determineContentType() {
        if (Files.exists(filepath)) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }
}
