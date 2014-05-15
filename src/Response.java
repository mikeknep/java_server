import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Response {
    private String version;
    private String status;
    private String body;
    private byte[] bodyData;

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

    public void setBody(String body) { this.body = body; }
    public String getBody() { return this.body; }

    public void setBodyData(byte[] bodyData) { this.bodyData = bodyData; }
    public byte[] getBodyData() { return this.bodyData; }
}
