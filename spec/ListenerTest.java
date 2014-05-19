import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void itReceivesRawRequest() throws Exception {
        MockStreamPair mockStreamPair = new MockStreamPair("First line\nSecond line\nThird line".getBytes());

        assertEquals("First line\nSecond line\nThird line\n", Listener.receiveRawRequest(mockStreamPair.getIn()));
    }

    @Test
    public void itThrowsPhantomRequestException() throws Exception {
        thrown.expect(PhantomRequestException.class);
        ByteArrayInputStream phantom = new ByteArrayInputStream("".getBytes());
        Listener.receiveRawRequest(phantom);
    }
}