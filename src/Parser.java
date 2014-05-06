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
}
