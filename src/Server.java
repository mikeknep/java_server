import java.net.ServerSocket;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    private int port;
    private String directory;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws Exception
    {
        SettingsConfig config = new SettingsConfig(args);
        Server server = new Server(config.getPort(), config.getDirectory());
        server.run();
    }

    public Server(int port, String directory) throws Exception {
        this.port = port;
        this.directory = directory;
        this.serverSocket = new ServerSocket(port);
        Logger.logStartup(port, directory);
    }

    public void run() throws Exception {
        while (true) {
            SocketStreamPair socketStreamPair = new SocketStreamPair(serverSocket);

            try {
                String rawRequest = Listener.receiveRawRequest(socketStreamPair.getIn());
                Request request = RequestBuilder.buildRequest(rawRequest);
                ResponseBuilder responseBuilder = Dispatcher.setResponseBuilder(directory, request);
                Response response = responseBuilder.buildResponse();
                Responder.sendResponse(response, socketStreamPair.getOut());
            }
            catch (Exception e) {
                ExceptionHandler.handle(e, socketStreamPair.getOut(), directory);
            }

            socketStreamPair.close();
        }
    }
}