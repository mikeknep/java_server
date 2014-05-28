import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/21/14.
 */
public class Dispatcher {
    public static ResponseBuilder setResponseBuilder(String directory, Request request) {
        Path path = Paths.get(directory, request.getResource());
        if (RequestValidator.isInvalidRequest(request)) {
            return new BadRequestResponseBuilder();
        } else if (Files.isDirectory(path)) {
            return new DirectoryResponseBuilder(directory, request.getResource());
        } else if (Files.exists(path)) {
            return new FileResponseBuilder(directory, request.getResource());
        } else {
            return new ErrorResponseBuilder(directory, 404);
        }
    }
}
