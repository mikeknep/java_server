/**
 * Created by mrk on 5/12/14.
 */
public class Response {
    private String version;
    private String status;
    private String body;

    public Response(String version, String status, String body) {
        setVersion(version);
        setStatus(status);
        setBody(body);
    }

    private void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return this.version;
    }

    private void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }

    private void setBody(String body) { this.body = body; }
    public String getBody() { return this.body; }
}
