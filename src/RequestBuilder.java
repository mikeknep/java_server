/**
 * Created by mrk on 5/14/14.
 */
public class RequestBuilder {
    public static Request buildRequest(String rawRequest) throws Exception {
        Request request = new Request();

        String requestLine = parseRequestLine(rawRequest);
        request.setMethod(parseRequestMethod(requestLine));
        request.setResource(parseRequestResource(requestLine));
        request.setVersion(parseRequestVersion(requestLine));

        String[] headers = parseRequestHeaders(rawRequest);
        for(String header : headers) {
            String[] kvPair = header.split(": ", 2);
            request.setHeader(kvPair[0], kvPair[1]);
        }

        return request;
    }

    private static String parseRequestLine(String rawRequest) {
        return rawRequest.split("\n", 2)[0];
    }

    private static String parseRequestMethod(String requestLine) {
        return requestLine.split(" ")[0];
    }

    private static String parseRequestResource(String requestLine) {
        return requestLine.split(" ")[1];
    }

    private static String parseRequestVersion(String requestLine) {
        return requestLine.split(" ")[2];
    }

    private static String[] parseRequestHeaders(String rawRequest) {
        String headers = rawRequest.split("\n", 2)[1];
        return headers.split("\n");
    }
}
