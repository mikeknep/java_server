import java.net.ServerSocket;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    private int port;
    private String directory;
    private ServerSocket serverSocket;

    public Server(int port, String directory) throws Exception {
        this.port = port;
        this.directory = directory;
        this.serverSocket = new ServerSocket(port);
        Logger.logStartup(port, directory);
    }

    public void run() throws Exception {
        while (true) {
            SocketStreamPair socketStreamPair = new SocketStreamPair(serverSocket);
            String rawRequest = Listener.receiveRawRequest(socketStreamPair.getIn());
            Request request = RequestBuilder.buildRequest(rawRequest);
            ResponseBuilder responseBuilder = Dispatcher.setResponseBuilder(directory, request);
            Response response = responseBuilder.buildResponse();
            Responder.sendResponse(response, socketStreamPair.getOut());
            Logger.logBasic(request, response, socketStreamPair.getSocketOpenTime());
            socketStreamPair.close();
        }
    }
}
