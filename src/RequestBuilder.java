import java.util.HashMap;

/**
 * Created by mrk on 5/14/14.
 */
public class RequestBuilder {
    public static Request buildRequest(String rawRequest) throws Exception {
        String requestLine = parseRequestLine(rawRequest);
        String method = parseRequestMethod(requestLine);
        String resource = parseRequestResource(requestLine);
        String version = parseRequestVersion(requestLine);
        HashMap<String, String> headers = parseRequestHeaders(rawRequest);

        return new Request(method, resource, version, headers);
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

    private static HashMap<String, String> parseRequestHeaders(String rawRequest) {
        String rawHeaders = rawRequest.split("\n", 2)[1];
        String[] allHeaders = rawHeaders.split("\n");
        HashMap<String, String> headers = new HashMap<String, String>();

        for(String header : allHeaders) {
            String[] kvPair = header.split(": ", 2);
            headers.put(kvPair[0], kvPair[1]);
        }

        return headers;
    }
}
