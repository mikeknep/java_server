import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class Listener {

    public Socket listen(int portNumber) throws Exception {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Logger logger = new Logger();
        logger.log("Listening on port " + portNumber);
        return serverSocket.accept();
    }

    public ArrayList<String> collect(InputStream incoming) throws Exception {
        InputStreamReader isr = new InputStreamReader(incoming);
        BufferedReader br = new BufferedReader(isr);

        ArrayList<String> requestCollection = new ArrayList<String>();
        String line;

        while ((line = br.readLine()) != null) {
            requestCollection.add(line);
        }

        return requestCollection;
    }
}
