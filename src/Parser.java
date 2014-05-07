import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class Parser {
    public String getLineWith(String lookup, ArrayList<String> strings) {
        String returnLine = "";

        for (String line : strings) {
            if (line.contains(lookup)) {
                returnLine += line;
            }
        }

        return returnLine;
    }

    public String parseRequestType(String request) {
        String[] words = request.split(" ");
        return words[0];
    }

    public String parseRequestedResource(String request) {
        String[] words = request.split(" ");
        return words[1];
    }

    public String parseHTTPVersion(String request) {
        String[] words = request.split(" ");
        return words[2];
    }
}
