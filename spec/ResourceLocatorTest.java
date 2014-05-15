import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ResourceLocatorTest {
    @Test
    public void recognizesPresentResourceWithRelativePath() {
        assertEquals(true, ResourceLocator.resourceIsPresent("spec", "/sample_files/mock.html"));
    }

    @Test
    public void recognizesMissingResourceWithRelativePath() {
        assertEquals(false, ResourceLocator.resourceIsPresent("spec", "/nonexistent.html"));
    }

    @Test
     public void recognizesPresentResourceWithAbsolutePath() {
        assertEquals(true, ResourceLocator.resourceIsPresent("/Users/mrk/8thlight/dahomey/spec", "/sample_files/mock.html"));
    }

    @Test
    public void recognizesMissingResourceWithAbsolutePath() {
        assertEquals(false, ResourceLocator.resourceIsPresent("/Users/mrk/8thlight/dahomey/spec", "/nonexistent.html"));
    }

    @Test
    public void understandsRelativeAndAbsolutePathsToSameFile() throws Exception {
        Path relative = Paths.get("spec/sample_files/mock.html");
        Path absolute = Paths.get("/Users/mrk/8thlight/dahomey/spec/sample_files/mock.html");
        assertEquals(true, Files.isSameFile(relative, absolute));
    }
}