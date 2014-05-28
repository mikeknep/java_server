import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ListenerTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    @Before
    public void setUpOutput() {
        System.setOut(new PrintStream(output));
    }

    @After
    public void cleanUpOutput() {
        System.setOut(null);
    }

    @Test
    public void itReceivesRawRequest() throws Exception {
        MockStreamPair mockStreamPair = new MockStreamPair("First line\nSecond line\nThird line".getBytes());

        assertEquals("First line\nSecond line\nThird line\n", Listener.receiveRawRequest(mockStreamPair.getIn()));
    }

    @Test
    public void itReceivesBadRawRequest() throws Exception {
        MockStreamPair mockStreamPair = new MockStreamPair("Incomplete   \n".getBytes());

        assertEquals("Incomplete   \n", Listener.receiveRawRequest(mockStreamPair.getIn()));
    }

    @Test
    public void itReceivesPhantomRequest() throws Exception {
        MockStreamPair mockStreamPair = new MockStreamPair("".getBytes());

        assertEquals("", Listener.receiveRawRequest(mockStreamPair.getIn()));
    }
}