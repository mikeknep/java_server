import java.util.Map;

/**
 * Created by mrk on 5/13/14.
 */
public class ResponsePresenter {
    public static byte[] generateFullResponseByteArray(Response response) {
        // determine size of full byte[] response and initialize
        int size = generateStatusLine(response).getBytes().length
                   + generateHeaders(response).getBytes().length
                   + "\n".getBytes().length
                   + response.getBodyData().length;
        byte[] responseByteArray = new byte[size];

        // add status line
        for (int i = 0; i < generateStatusLine(response).getBytes().length; i++) {
            responseByteArray[i] = generateStatusLine(response).getBytes()[i];
        }
        // add headers
        for (int i = 0; i < generateHeaders(response).getBytes().length; i++) {
            responseByteArray[generateStatusLine(response).getBytes().length + i] = generateHeaders(response).getBytes()[i];
        }
        // add blank line
        for (int i = 0; i < "\n".getBytes().length; i++) {
            responseByteArray[generateStatusLine(response).getBytes().length + generateHeaders(response).getBytes().length + i] = "\n".getBytes()[i];
        }
        // add body
        for (int i = 0; i < response.getBodyData().length; i++) {
            responseByteArray[generateStatusLine(response).getBytes().length + generateHeaders(response).getBytes().length + "\n".getBytes().length + i] = response.getBodyData()[i];
        }

        // return byte array
        return responseByteArray;
    }

    public static String generateStatusLine(Response response) {
        return (response.getVersion() + " " + response.getStatus() + "\n");
    }

    public static String generateHeaders(Response response) {
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> header : response.getHeaders().entrySet()) {
            builder.append(header.getKey() + ": " + header.getValue() + "\n");
        }

        return builder.toString();
    }
}
