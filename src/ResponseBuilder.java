import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/14/14.
 */
public class ResponseBuilder {
    public static Response buildResponse(String directory, Request request) throws Exception {
        ResourceHandler handler = new ResourceHandler(directory, request.getResource());

        Response response = new Response();

        response.setVersion(request.getVersion());
        String status = generateStatus(directory, request.getResource());
        response.setStatus(status);
        response.setBody(handler.getResourceString());

        String resourceToSend = determineResourceToSend(status, request.getResource());
        response.setBodyData(generateBodyData(directory, resourceToSend));

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
            return "404.html";
        }
    }

    public static byte[] generateBodyData(String directory, String resource) throws Exception {
        return Files.readAllBytes(Paths.get(directory + resource));
    }



    public static Response build500Response() {
        Response response = new Response();

        response.setVersion("HTTP/1.1");
        response.setStatus("500 Internal Server Error");
        response.setBody("Oh no! 500!");

        return response;
    }
}
