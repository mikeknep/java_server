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
        SettingsConfig config = new SettingsConfig(args);
        runner.run(config.getPort(), config.getDirectory());
    }

    public void run(int port, String directory) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        logger.log("Server initialized on port " + port + ", serving directory " + directory);

        while (true) {
            Socket socket = listener.listen(serverSocket);

            try {
                String rawRequest = listener.collectRawRequest(socket.getInputStream());
                Request request = RequestBuilder.buildRequest(rawRequest);
                Response response = ResponseBuilder.buildResponse(directory, request);
                // Responder.sendResponse(response);



                // This will be the responsibility of the Responder
                String responseAsString = ResponsePresenter.present(response);
                PrintWriter outWriter = new PrintWriter(socket.getOutputStream(), true);
                outWriter.println(responseAsString);


                socket.close();
            }
            catch (PhantomRequestException phantom) {
                logger.log("Phantom request!");
                socket.close();
            }
        }
    }
}
