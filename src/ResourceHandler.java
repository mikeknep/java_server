import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by mrk on 5/13/14.
 */
public class ResourceHandler {
    File resource;
    String resourceString;

    public File getResource() {
        return this.resource;
    }
    public String getResourceString() {
        return this.resourceString;
    }


    public ResourceHandler(String directory, String requestedResource) throws Exception {
        setResource(directory, requestedResource);
        readFile(this.resource);
    }


    private void setResource(String rootDirectory, String requestedResource) {
        if (ResourceLocator.resourceIsPresent(rootDirectory, requestedResource)) {
            this.resource = new File(rootDirectory + requestedResource);
        } else {
            this.resource = new File(rootDirectory + "/404.html");
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
}