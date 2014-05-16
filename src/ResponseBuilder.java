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

    public static String generateStatus(String directory, String resource) {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return "200 OK";
        } else {
            return "404 Not Found";
        }
    }

    public static byte[] generateBody(String directory, String resource) throws Exception {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return Files.readAllBytes(Paths.get(directory + resource));
        }
        else {
            return generateErrorBody(directory, "404");
        }
    }

    public static byte[] generateErrorBody(String directory, String error) throws Exception {
        String errorResource = "/" + error + ".html";
        if (ResourceLocator.resourceIsPresent(directory, errorResource)) {
            return Files.readAllBytes(Paths.get(directory + errorResource));
        } else {
            return error.getBytes();
        }
    }

    public static String determineContentType(String directory, String resource) throws Exception {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return URLConnection.guessContentTypeFromName(directory + resource);
        } else {
            return "text/html";
        }
    }



    public static Response build500Response(String directory) throws Exception {
        Response response = new Response();

        response.setVersion("HTTP/1.1");
        response.setStatus("500 Internal Server Error");
        response.setBody(generateErrorBody(directory, "500"));

        return response;
    }
}
