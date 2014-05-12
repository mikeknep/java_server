import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Request {
    private String method;
    private String resource;
    private String version;
    private HashMap<String, String> options = new HashMap<String, String>();

    public Request(String rawRequest) {
        String[] initialSplit = rawRequest.split("\n", 2);
        String firstLine = initialSplit[0];
        String options = initialSplit[1];

        setFirstLineFields(firstLine);
        setOptions(options);
    }

    private void setFirstLineFields(String firstLine) {
        String[] fields = firstLine.split(" ");
        setMethod(fields[0]);
        setResource(fields[1]);
        setVersion(fields[2]);
    }

    private void setOptions(String options) {
        String[] allOptions = options.split("\n");
        for(String option : allOptions) {
            String[] kvPair = option.split(": ", 2);
            String key = kvPair[0];
            String value = kvPair[1];
            setOption(key, value);
        }
    }

    private void setOption(String key, String value) {
        this.options.put(key, value);
    }
    public String getOption(String key) {
        return this.options.get(key);
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
