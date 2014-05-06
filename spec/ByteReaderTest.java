import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ByteReaderTest {

    @Test
    public void itConvertsAByteStreamToText() {
        ByteReader byteReader = new ByteReader();
        byte[] helloWorld = "Hello world!".getBytes();

        assertEquals("Hello world!", byteReader.convertToText(helloWorld));
    }

    @Test
    public void itConvertsAnArrayListOfBytesToASingleString() {
        ByteReader byteReader = new ByteReader();

        byte[] lineOne = "foo".getBytes();
        byte[] lineTwo = "bar".getBytes();

        ArrayList<byte[]> bytes = new ArrayList<byte[]>();
        bytes.add(lineOne);
        bytes.add(lineTwo);

        assertEquals("foo\nbar\n", byteReader.generateString(bytes));
    }

}