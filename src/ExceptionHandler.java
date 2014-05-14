/**
 * Created by mrk on 5/14/14.
 */
public class ExceptionHandler {
    public static void handle(Exception exception) {
        Logger logger = new Logger();

        if (exception instanceof PhantomRequestException) {
            logger.log("Phantom request caught. Ignoring...");
        } else {
            logger.log("Exception: " + exception);
        }
    }
}
