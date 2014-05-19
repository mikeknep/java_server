import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mrk on 5/19/14.
 */
public class SocketStreamPair implements StreamPair {
    private Socket socket;

    public SocketStreamPair(ServerSocket serverSocket) throws Exception {
        this.socket = serverSocket.accept();
    }

    public InputStream getIn() throws Exception {
        return this.socket.getInputStream();
    }

    public OutputStream getOut() throws Exception {
        return this.socket.getOutputStream();
    }

    public void close() throws Exception {
        this.socket.close();
    }
}
