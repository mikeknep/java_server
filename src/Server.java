import java.net.ServerSocket;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    Logger logger = new Logger();

    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        SettingsConfig config = new SettingsConfig(args);
        server.run(config.getPort(), config.getDirectory());
    }

    public void run(int port, String directory) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        logger.startupMessage(port, directory);

        while (true) {
            SocketStreamPair socketStreamPair = new SocketStreamPair(serverSocket);

            try {
                String rawRequest = Listener.collectRawRequest(socketStreamPair.getIn());
                Request request = RequestBuilder.buildRequest(rawRequest);
                Response response = ResponseBuilder.buildResponse(directory, request);
                Responder.sendResponse(response, socketStreamPair.getOut());
            }
            catch (Exception e) {
                ExceptionHandler.handle(e, socketStreamPair.getOut(), directory);
            }

            socketStreamPair.close();
        }
    }
}