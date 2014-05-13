import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by mrk on 5/13/14.
 */
public class ResourceLocator {
    public static boolean resourceIsPresent(String directory, String requestedResource) {
        Path path = Paths.get(directory + requestedResource);
        return Files.exists(path);
    }
}
