import java.util.HashMap;

/**
 * Created by mrk on 5/14/14.
 */
public class RequestBuilder {
    public static Request buildRequest(String rawRequest) throws Exception {
        try {
            String requestLine = parseRequestLine(rawRequest);
            String method = parseMethod(requestLine);
            String resource = parseResource(requestLine);
            String version = parseVersion(requestLine);
            HashMap<String, String> headers = parseHeaders(rawRequest);

            return new Request(method, resource, version, headers);
        } catch (Exception e) {
            return new Request("", "", "", new HashMap<String, String>());
        }
    }

    private static String parseRequestLine(String rawRequest) {
        return rawRequest.split("\n", 2)[0];
    }

    private static String parseMethod(String requestLine) {
        return requestLine.split(" ")[0];
    }

    private static String parseResource(String requestLine) {
        return requestLine.split(" ")[1];
    }

    private static String parseVersion(String requestLine) {
        return requestLine.split(" ")[2];
    }

    private static HashMap<String, String> parseHeaders(String rawRequest) {
        HashMap<String, String> headers = new HashMap<String, String>();
        try {
            String rawHeaders = rawRequest.split("\n", 2)[1];
            String[] allHeaders = rawHeaders.split("\n");

            for (String header : allHeaders) {
                String[] kvPair = header.split(": ", 2);
                headers.put(kvPair[0], kvPair[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        return headers;
    }
}
