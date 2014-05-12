import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {
    @Test
    public void itCollectsRawRequest() throws Exception {
        Listener listener = new Listener();
        ByteArrayInputStream incoming = new ByteArrayInputStream("First line\nSecond line\nThird line".getBytes());

        assertEquals("First line\nSecond line\nThird line\n", listener.collectRawRequest(incoming));
    }
}