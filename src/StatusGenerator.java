/**
 * Created by mrk on 5/20/14.
 */
public class StatusGenerator {
    public static String generateStatus(String directory, String resource) {
        if (ResourceLocator.resourceIsPresent(directory, resource)) {
            return "200 OK";
        } else {
            return "404 Not Found";
        }
    }
}
