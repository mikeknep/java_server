import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Request {
    private String method;
    private String resource;
    private String version;
    private HashMap<String, String> headers = new HashMap<String, String>();

    public Request(String rawRequest) {
        String[] initialSplit = rawRequest.split("\n", 2);
        String requestLine = initialSplit[0];
        String headers = initialSplit[1];

        setRequestLineFields(requestLine);
        setHeaders(headers);
    }

    private void setRequestLineFields(String firstLine) {
        String[] fields = firstLine.split(" ");
        setMethod(fields[0]);
        setResource(fields[1]);
        setVersion(fields[2]);
    }

    private void setHeaders(String headers) {
        String[] allHeaders= headers.split("\n");
        for(String header : allHeaders) {
            String[] kvPair = header.split(": ", 2);
            String key = kvPair[0];
            String value = kvPair[1];
            setHeader(key, value);
        }
    }

    private void setHeader(String key, String value) {
        this.headers.put(key, value);
    }
    public String getHeader(String key) {
        return this.headers.get(key);
    }

    private void setMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return this.method;
    }

    private void setResource(String resource) {
        this.resource = resource;
    }
    public String getResource() {
        return this.resource;
    }

    private void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return this.version;
    }
}
