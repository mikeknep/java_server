import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by mrk on 5/14/14.
 */
public class Responder {
    public static void sendResponse(Response response, Socket socket) throws Exception {
        byte[] fullResponseByteArray = ResponsePresenter.generateFullResponseByteArray(response);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(fullResponseByteArray);
    }
}
