import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Response {
    private String version;
    private String status;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body;

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
}
