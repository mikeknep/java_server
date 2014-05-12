import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {

    @Test
    public void itCollectsARequest() throws Exception {
        Listener listener = new Listener();
        ByteArrayInputStream incoming = new ByteArrayInputStream("Hello world".getBytes());

        ArrayList<String> requestCollection = new ArrayList<String>();
        requestCollection.add("Hello world");

        assertEquals(requestCollection, listener.collect(incoming));
    }

    @Test
    public void itCollectsMultilineRequest() throws Exception {
        Listener listener = new Listener();
        ByteArrayInputStream incoming = new ByteArrayInputStream("First line\nSecond line".getBytes());

        ArrayList<String> requestCollection = new ArrayList<String>();
        requestCollection.add("First line");
        requestCollection.add("Second line");

        assertEquals(requestCollection, listener.collect(incoming));
    }

    @Test
    public void itCollectsRawRequest() throws Exception {
        Listener listener = new Listener();
        ByteArrayInputStream incoming = new ByteArrayInputStream("First line\nSecond line\nThird line".getBytes());

        assertEquals("First line\nSecond line\nThird line\n", listener.collectRawRequest(incoming));
    }
}