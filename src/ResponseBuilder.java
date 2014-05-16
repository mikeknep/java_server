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
        String status = generateStatus(directory, request.getResource());
        response.setStatus(status);

        String resourceToSend = determineResourceToSend(status, request.getResource());
        response.setBodyData(generateBodyData(directory, resourceToSend));

        response.setHeader("Content-Length", String.valueOf(response.getBodyData().length));
        response.setHeader("Content-Type", determineContentType(directory, resourceToSend));

        return response;
    }

    public static String generateStatus(String directory, String resource) {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return "200 OK";
        } else {
            return "404 Not Found";
        }
    }

    public static String determineResourceToSend(String status, String resource) {
        if (status.equals("200 OK")) {
            return resource;
        } else {
            return "/404.html";
        }
    }

    public static byte[] generateBodyData(String directory, String resource) throws Exception {
        return Files.readAllBytes(Paths.get(directory + resource));
    }

    public static String determineContentType(String directory, String resource) throws Exception {
        return URLConnection.guessContentTypeFromName(directory + resource);
    }



    public static Response build500Response() {
        Response response = new Response();

        response.setVersion("HTTP/1.1");
        response.setStatus("500 Internal Server Error");
        String fiveHundred = "<!DOCTYPE html><html><head><title>Dahomey</title></head><body><h1>Now you've done it...</h1><p>You threw a 500. Brutal</p></html>";
        response.setBodyData(fiveHundred.getBytes());

        return response;
    }
}
