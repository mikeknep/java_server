import java.net.ServerSocket;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    private String directory;
    private ServerSocket serverSocket;

    public Server(String directory, ServerSocket serverSocket) throws Exception {
        this.directory = directory;
        this.serverSocket = serverSocket;
        Logger.logStartup(serverSocket.getLocalPort(), directory);
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
