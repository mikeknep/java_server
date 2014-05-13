import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
* Created by mrk on 5/7/14.
*/
public class Runner {
    Listener listener = new Listener();
    Responder responder = new Responder();
    Logger logger = new Logger();

    public static void main(String[] args) throws Exception
    {
        Runner runner = new Runner();
        runner.run();
    }

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(8399);
        logger.log("Server initialized on port " + serverSocket.getLocalPort());

        while (true) {
            Socket socket = listener.listen(serverSocket);

            String rawRequest = listener.collectRawRequest(socket.getInputStream());
            Request request = new Request(rawRequest);

            File resource = responder.locateFile("/Users/mrk/Desktop/dahomey", request.getResource());
            String body = responder.readFile(resource);

            Response response = new Response(request.getVersion(), "200 OK", body);
            String responseAsString = ResponsePresenter.present(response);
            responder.respond(responseAsString, socket.getOutputStream());

            socket.close();
        }
    }
}
