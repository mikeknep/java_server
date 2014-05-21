import java.io.OutputStream;

/**
 * Created by mrk on 5/14/14.
 */
public class ExceptionHandler {
    public static void handle(Exception exception, OutputStream outputStream, String directory) throws Exception {
        if (exception instanceof PhantomRequestException) {
            Logger.logException(exception, "ignore");
        } else {
            ErrorResponseBuilder builder = new ErrorResponseBuilder(directory, 500);
            Response response500 = builder.buildResponse();
            Responder.sendResponse(response500, outputStream);
            Logger.logException(exception, "500");
        }
    }
}
