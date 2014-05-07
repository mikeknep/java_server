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
}