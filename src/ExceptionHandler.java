import java.io.OutputStream;

/**
 * Created by mrk on 5/14/14.
 */
public class ExceptionHandler {
    public static void handle(Exception exception, OutputStream outputStream, String directory) throws Exception {
        Logger logger = new Logger();

        if (exception instanceof PhantomRequestException) {
            logger.logException(exception, "ignore");
        } else {
            Response response500 = ResponseBuilder.build500Response(directory);
            Responder.sendResponse(response500, outputStream);
            logger.logException(exception, "500");
        }
    }
}
