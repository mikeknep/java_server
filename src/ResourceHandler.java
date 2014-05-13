import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by mrk on 5/13/14.
 */
public class ResourceHandler {
    File resource;
    String resourceString;
    String status;

    public ResourceHandler(String rootDirectory, String requestedResource) throws Exception {
        setResourceAndStatus(rootDirectory, requestedResource);
        readFile(this.resource);
    }

    private void setResourceAndStatus(String rootDirectory, String requestedResource) {
        if (ResourceLocator.resourceIsPresent(rootDirectory, requestedResource)) {
            this.resource = new File(rootDirectory + requestedResource);
            this.status = "200 OK";
        } else {
            this.resource = new File(rootDirectory + "/404.html");
            this.status = "404 Not Found";
        }
    }

    private void readFile(File resource) throws Exception {
        StringBuilder builder = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(resource));
        String str;
        while ((str = in.readLine()) != null) {
            builder.append(str);
        }
        in.close();
        this.resourceString = builder.toString();
    }


    public String getStatus() {
        return this.status;
    }

    public File getResource() {
        return this.resource;
    }

    public String getResourceString() {
        return this.resourceString;
    }
}
