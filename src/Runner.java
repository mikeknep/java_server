import java.io.File;
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
        String requestedResource = parser.parseRequestedResource(firstLineOfRequest);

        File resource = responder.locateFile("/Users/mrk/Desktop/dahomey", requestedResource);
        String body = responder.readFile(resource);

        String response = responder.formatResponse("HTTP/1.1 200 OK", body);
        responder.respond(response, socket.getOutputStream());

        socket.close();
    }
}
