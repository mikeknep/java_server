import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class ByteReader {
    public String convertToText(byte[] bytes) {
        return new String(bytes);
    }

    public String generateString(ArrayList<byte[]> bytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte[] byteSeq : bytes) {
            stringBuilder.append(convertToText(byteSeq));
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
