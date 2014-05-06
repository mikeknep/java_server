import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ByteReaderTest {

    @Test
    public void itConvertsArrayListBytesToArrayListStrings() {
        ByteReader byteReader = new ByteReader();

        byte[] lineOne = "foo".getBytes();
        byte[] lineTwo = "bar".getBytes();

        ArrayList<byte[]> bytes = new ArrayList<byte[]>();
        bytes.add(lineOne);
        bytes.add(lineTwo);

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("foo");
        strings.add("bar");

        assertEquals(strings, byteReader.convertToText(bytes));
    }

}