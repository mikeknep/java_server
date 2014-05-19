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