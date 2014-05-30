import java.util.Date;

/**
 * Created by mrk on 5/7/14.
 */
public class Logger {

    public static void logStartup(int port, String directory) {
        System.out.println("Server initialized on port " + port + ", serving directory " + directory);
    }

    public static void logBasic(Request request, Response response, Date timeOfRequest) {
        System.out.println(timeOfRequest.toString() + " '" + request.getMethod() + " " + request.getResource() + " " + request.getVersion() + "' " + response.getStatus());
    }
}
