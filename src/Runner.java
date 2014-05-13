import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
* Created by mrk on 5/7/14.
*/
public class Runner {
    Listener listener = new Listener();
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

            ResourceHandler handler = new ResourceHandler("/Users/mrk/Desktop/dahomey", request.getResource());
            Response response = new Response(request.getVersion(), handler.getStatus(), handler.getResourceString());

            String responseAsString = ResponsePresenter.present(response);


            // This needs to be wrapped in some object
            PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
            outWriter.println(responseAsString);


            socket.close();
        }
    }
}
