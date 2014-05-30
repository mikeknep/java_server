import org.junit.Test;
import static org.junit.Assert.*;

public class ListenerTest {

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