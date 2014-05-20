import org.junit.Test;

import static org.junit.Assert.*;

public class StatusGeneratorTest {
    @Test
    public void itGenerates200ForPresentResource() {
        String directory = "spec/sample_files";
        String resource = "/mock.html";

        assertEquals("200 OK", StatusGenerator.generateStatus(directory, resource));
    }

    @Test
    public void itGenerates200ForPresentDirectory() {
        String directory = "spec";
        String resource = "/sample_files";

        assertEquals("200 OK", StatusGenerator.generateStatus(directory, resource));
    }

    @Test
    public void itGenerates404ForMissingResource() {
        String directory = "spec/sample_files";
        String resource = "/nonexistent.html";

        assertEquals("404 Not Found", StatusGenerator.generateStatus(directory, resource));
    }

    @Test
    public void itGenerates404ForMissingDirectory() {
        String directory = "spec";
        String resource = "/nonexistent_directory";

        assertEquals("404 Not Found", StatusGenerator.generateStatus(directory, resource));
    }
}