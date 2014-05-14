/**
 * Created by mrk on 5/14/14.
 */
public class ResponseBuilder {
    public static Response buildResponse(String directory, Request request) throws Exception {
        ResourceHandler handler = new ResourceHandler(directory, request.getResource());

        Response response = new Response();

        response.setVersion(request.getVersion());
        response.setStatus(generateStatus(directory, request));
        response.setBody(handler.getResourceString());

        return response;
    }

    public static String generateStatus(String directory, Request request) {
        if (ResourceLocator.resourceIsPresent(directory, request.getResource())) {
            return "200 OK";
        } else {
            return "404 Not Found";
        }
    }
}
