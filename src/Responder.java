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

    public File locateFile(String directory, String resource) {
        String filename = resource;
        if (requestedResourceIsRoot(resource)) {
            filename = appendIndexToRoot(resource);
        }
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



    private boolean requestedResourceIsRoot(String resource) {
        return resource.equals("/");
    }

    private String appendIndexToRoot(String resource) {
        StringBuilder builder = new StringBuilder();
        builder.append(resource);
        builder.append("index.html");
        return builder.toString();
    }
}
