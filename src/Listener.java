import java.net.ServerSocket;

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
}
