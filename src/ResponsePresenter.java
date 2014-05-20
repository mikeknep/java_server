import java.util.Map;

/**
 * Created by mrk on 5/13/14.
 */
public class ResponsePresenter {
    public static byte[] generateFullResponseByteArray(Response response) {
        int size = statusSize(response) + headersSize(response) + newlineSize() + bodySize(response);
        byte[] responseByteArray = new byte[size];

        for (int i = 0; i < statusSize(response); i++) {
            responseByteArray[i] = statusBytes(response)[i];
        }
        for (int i = 0; i < headersSize(response); i++) {
            int offset = statusSize(response);
            responseByteArray[offset + i] = headersBytes(response)[i];
        }
        for (int i = 0; i < newlineSize(); i++) {
            int offset = statusSize(response) + headersSize(response);
            responseByteArray[offset + i] = newlineBytes()[i];
        }
        for (int i = 0; i < bodySize(response); i++) {
            int offset = statusSize(response) + headersSize(response) + newlineSize();
            responseByteArray[offset + i] = bodyBytes(response)[i];
        }

        return responseByteArray;
    }



    private static String generateStatusLine(Response response) {
        return (response.getVersion() + " " + response.getStatus() + "\n");
    }

    private static String generateHeaders(Response response) {
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> header : response.getHeaders().entrySet()) {
            builder.append(header.getKey() + ": " + header.getValue() + "\n");
        }

        return builder.toString();
    }

    private static byte[] statusBytes(Response response) {
        return generateStatusLine(response).getBytes();
    }

    private static byte[] headersBytes(Response response) {
        return generateHeaders(response).getBytes();
    }

    private static byte[] newlineBytes() {
        return "\n".getBytes();
    }

    private static byte[] bodyBytes(Response response) {
        return response.getBody();
    }

    private static int statusSize(Response response) {
        return statusBytes(response).length;
    }

    private static int headersSize(Response response) {
        return headersBytes(response).length;
    }

    private static int newlineSize() {
        return newlineBytes().length;
    }

    private static int bodySize(Response response) {
        return bodyBytes(response).length;
    }
}
