import org.junit.Test;

import java.net.ServerSocket;

import static org.junit.Assert.*;

public class ListenerTest {

    @Test
    public void itSetsAPort() {
        Listener listener = new Listener();
        listener.setPort(8399);

        assertEquals(8399, listener.port);
    }

    @Test
    public void itCreatesAServerSocketOnItsPort() throws Exception {
        Listener listener = new Listener();
        listener.setPort(8399);
        listener.createServerSocket();

        assertEquals(ServerSocket.class, listener.serverSocket.getClass());
    }
}