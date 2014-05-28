import java.net.ServerSocket;

/**
 * Created by mrk on 5/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SettingsConfig config = new SettingsConfig(args);
        String rootDirectory = config.getDirectory();
        ServerSocket serverSocket = new ServerSocket(config.getPort());
        Server server = new Server(rootDirectory, serverSocket);
        server.run();
    }
}
