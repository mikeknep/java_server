import java.net.Socket;

/**
 * Created by mrk on 5/14/14.
 */
public class ExceptionHandler {
    public static void handle(Exception exception, Socket socket) throws Exception {
        Logger logger = new Logger();

        if (exception instanceof PhantomRequestException) {
            logger.log("Phantom request caught. Ignoring...");
        } else {
            Response response500 = ResponseBuilder.build500Response();
            Responder.sendResponse(response500, socket);
            logger.log("Exception: " + exception);
        }
    }
}
