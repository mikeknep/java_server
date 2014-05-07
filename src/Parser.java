import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class Parser {

    public String parseRequestLine(String request, int index) {
        String[] components = request.split(" ");
        return components[index];
    }

    public String parseRequestMethod(String request) {
        return parseRequestLine(request, 0);
    }

    public String parseRequestedResource(String request) {
        return parseRequestLine(request, 1);
    }

    public String parseHTTPVersion(String request) {
        return parseRequestLine(request, 2);
    }

}
