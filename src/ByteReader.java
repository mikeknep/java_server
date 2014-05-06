import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class ByteReader {
    public ArrayList<String> convertToText(ArrayList<byte[]> bytes) {
        ArrayList<String> strings = new ArrayList<String>();

        for (byte[] byteSeq : bytes) {
            strings.add(new String(byteSeq));
        }

        return strings;
    }
}
