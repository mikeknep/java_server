import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Request {
    private String method;
    private String resource;
    private String version;
    private HashMap<String, String> headers = new HashMap<String, String>();


    public void setMethod(String method) {
        this.method = method;
    }
    public String getMethod() {
        return this.method;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getResource() {
        return this.resource;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return this.version;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }
    public String getHeader(String key) {
        return this.headers.get(key);
    }
}
