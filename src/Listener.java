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

    public static Socket listen(ServerSocket serverSocket) throws Exception {
        return serverSocket.accept();
    }

    public static String collectRawRequest(InputStream incoming) throws Exception {
        InputStreamReader isr = new InputStreamReader(incoming);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();

        String line;
        Logger logger = new Logger();

        while ((line = br.readLine()) != null && !line.equals("")) {
            builder.append(line + "\n");
            logger.log(line);
        }

        if (builder.toString().equals("")) {
            throw new PhantomRequestException();
        } else {
            return builder.toString();
        }
    }
}
