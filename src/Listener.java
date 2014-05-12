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

    public Socket listen(ServerSocket serverSocket) throws Exception {
        return serverSocket.accept();
    }

    public ArrayList<String> collect(InputStream incoming) throws Exception {
        InputStreamReader isr = new InputStreamReader(incoming);
        BufferedReader br = new BufferedReader(isr);

        ArrayList<String> requestCollection = new ArrayList<String>();
        String line;
        Logger logger = new Logger();

        while ((line = br.readLine()) != null && !line.equals("")) {
            requestCollection.add(line);
            logger.log(line);
        }

        return requestCollection;
    }
}
