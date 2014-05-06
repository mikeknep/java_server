import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void itReturnsStringFromArrayListContainingSubstring() {
        Parser parser = new Parser();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Not this one");
        strings.add("We want this");
        strings.add("Not this either");

        assertEquals("We want this", parser.getLineWith("want", strings));
    }

    @Test
    public void itReturnsAnEmptyStringIfNoMatch() {
        Parser parser = new Parser();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("foo");
        strings.add("bar");

        assertEquals("", parser.getLineWith("Hello world!", strings));
    }

}