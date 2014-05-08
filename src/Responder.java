import java.io.*;

/**
 * Created by mrk on 5/7/14.
 */
public class Responder {

    public String formatResponse(String header, String body) {
        return header + "\n\n" + body;
    }

    public void respond(String response, OutputStream outputStream) {
        PrintWriter outWriter = new PrintWriter(outputStream, true);
        outWriter.println(response);
    }

    public File locateFile(String directory, String filename) {
        String fullPath = directory + filename;
        return new File(fullPath);
    }

    public String readFile(File file) throws Exception {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String str;
        while ((str = in.readLine()) != null) {
            builder.append(str);
        }
        in.close();
        return builder.toString();
    }
}
