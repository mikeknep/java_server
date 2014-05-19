import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ResourceLocatorTest {
    @Test
    public void recognizesPresentResource() {
        assertTrue(ResourceLocator.resourceIsPresent("spec/sample_files", "/mock.html"));
    }

    @Test
    public void recognizesMissingResource() {
        assertFalse(ResourceLocator.resourceIsPresent("spec/sample_files", "/nonexistent.html"));
    }
}