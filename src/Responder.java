import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mrk on 5/14/14.
 */
public class Responder {
    public static void sendResponse(Response response, Socket socket) throws Exception {
        String fullResponseString = ResponsePresenter.generateFullResponse(response);
        PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
        outWriter.println(fullResponseString);
    }
}
