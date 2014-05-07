import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by mrk on 5/7/14.
 */
public class Responder {

    public String formatResponse(String header, String body) {
        return header + "\n\n" + body;
    }

    public void respond(String response, OutputStream outputStream) {
        PrintWriter outWriter = new PrintWriter(outputStream, true);
        outWriter.println(response);
    }
}
