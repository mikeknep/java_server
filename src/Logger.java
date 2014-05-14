/**
 * Created by mrk on 5/7/14.
 */
public class Logger {
    public void log(String message) {
        System.out.println(message);
    }

    public void startupMessage(int port, String directory) {
        System.out.println("Server initialized on port " + port + ", serving directory " + directory);
    }
}
