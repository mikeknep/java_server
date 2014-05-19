import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/14/14.
 */
public class ResponseBuilder {
    public static Response buildResponse(String directory, Request request) throws Exception {
        Response response = new Response();

        response.setVersion(request.getVersion());
        response.setStatus(generateStatus(directory, request.getResource()));
        response.setBody(generateBody(directory, request.getResource()));
        response.setHeader("Content-Length", String.valueOf(response.getBody().length));
        response.setHeader("Content-Type", determineContentType(directory, request.getResource()));

        return response;
    }

    public static Response build500Response(String directory) throws Exception {
        Response response = new Response();

        response.setVersion("HTTP/1.1");
        response.setStatus("500 Internal Server Error");
        response.setBody(generateErrorBody(directory, "500"));
        response.setHeader("Content-Length", String.valueOf(response.getBody().length));
        response.setHeader("Content-Type", "text/html");

        return response;
    }


    private static String generateStatus(String directory, String resource) {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return "200 OK";
        } else {
            return "404 Not Found";
        }
    }

    private static byte[] generateBody(String directory, String resource) {
        try {
            return Files.readAllBytes(Paths.get(directory + resource));
        } catch (IOException e) {
            return generateErrorBody(directory, "404");
        }
    }

    private static byte[] generateErrorBody(String directory, String error) {
        try {
            return Files.readAllBytes(Paths.get(directory + "/" + error + ".html"));
        } catch (IOException e) {
            return error.getBytes();
        }
    }

    private static String determineContentType(String directory, String resource) throws Exception {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return URLConnection.guessContentTypeFromName(directory + resource);
        } else {
            return "text/html";
        }
    }
}
