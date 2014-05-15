import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ResourceHandlerTest {
    @Test
    public void itSetsResourceAsFileWhenPresent() throws Exception {
        ResourceHandler handler = new ResourceHandler("spec/sample_files", "/mock.html");
        assertEquals(new File("spec/sample_files/mock.html"), handler.getResource());
    }

    @Test
    public void itSetsResourceAs404WhenNotPresent() throws Exception {
        ResourceHandler handler = new ResourceHandler("spec/sample_files", "/nonexistent.html");
        assertEquals(new File("spec/sample_files/404.html"), handler.getResource());
    }

    @Test
    public void itConvertsResourceToString() throws Exception {
        ResourceHandler handler = new ResourceHandler("spec/sample_files", "/mock.html");
        assertEquals("Good morning, world!", handler.getResourceString());
    }
}