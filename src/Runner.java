import java.net.Socket;
import java.util.ArrayList;

/**
* Created by mrk on 5/7/14.
*/
public class Runner {
    Listener listener = new Listener();
    Parser parser = new Parser();
    Responder responder = new Responder();

    public static void main(String[] args) throws Exception
    {
        Runner runner = new Runner();
        runner.run();
    }

    public void run() throws Exception {
        Socket socket = listener.listen(8399);
        ArrayList<String> requestCollection = listener.collect(socket.getInputStream());

        String firstLineOfRequest = requestCollection.get(0);

        String requestMethod = parser.parseRequestMethod(firstLineOfRequest);
        String requestedResource = parser.parseRequestedResource(firstLineOfRequest);
        String httpVersion = parser.parseHTTPVersion(firstLineOfRequest);

        String response = responder.formatResponse("HTTP/1.1 200 OK", "<h1>Hello world</h1><p>This rocks!</p>");
        responder.respond(response, socket.getOutputStream());

        socket.close();
    }
}
