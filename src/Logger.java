/**
 * Created by mrk on 5/7/14.
 */
public class Logger {
    public static void log(String message) {
        System.out.println(message);
    }

    public static void logStartup(int port, String directory) {
        System.out.println("Server initialized on port " + port + ", serving directory " + directory);
    }

    public static void logException(Exception exception, String responseType) {
        String serverResponse;
        if (responseType.equals("ignore")) {
            serverResponse = "Ignored.";
        } else if (responseType.equals("500")) {
            serverResponse = "500 response sent.";
        } else {
            serverResponse = "";
        }

        System.out.println("Exception caught: " + exception + ". " + serverResponse);
    }
}
