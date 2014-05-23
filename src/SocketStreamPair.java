import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by mrk on 5/19/14.
 */
public class SocketStreamPair implements StreamPair {
    private Socket socket;
    private Date socketOpenTime;

    public SocketStreamPair(ServerSocket serverSocket) throws Exception {
        this.socket = serverSocket.accept();
        this.socketOpenTime = new Date();
    }

    public InputStream getIn() throws Exception {
        return this.socket.getInputStream();
    }

    public OutputStream getOut() throws Exception {
        return this.socket.getOutputStream();
    }

    public Date getSocketOpenTime() {
        return this.socketOpenTime;
    }

    public void close() throws Exception {
        this.socket.close();
    }
}
