import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mrk on 5/6/14.
 */
public class Listener {
    int port;
    ServerSocket serverSocket;

    public void setPort(int portNumber) {
        this.port = portNumber;
    }

    public void createServerSocket() throws Exception {
        this.serverSocket = new ServerSocket(this.port);
    }

    public Socket listen(int portNumber) throws Exception {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        return serverSocket.accept();
    }
}
