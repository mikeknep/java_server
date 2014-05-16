import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Response {
    private String version;
    private String status;
    private byte[] body;
    private HashMap<String, String> headers = new HashMap<String, String>();

    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return this.version;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    public void setBody(byte[] body) { this.body = body; }
    public byte[] getBody() { return this.body; }

    public void setHeader(String key, String value) { this.headers.put(key, value); }
    public HashMap<String, String> getHeaders() { return this.headers; }
}
