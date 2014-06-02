package com.mikeknep.dahomey.responses;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by mrk on 5/21/14.
 */
public class DirectoryResponseBuilder implements ResponseBuilder {
    private String rootDirectory;
    private Path resourcePath;

    public DirectoryResponseBuilder(String rootDirectory, String requestedResource) {
        this.rootDirectory = rootDirectory;
        this.resourcePath = Paths.get(rootDirectory + requestedResource);
    }

    public Response buildResponse() {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = generateBody();
        HashMap<String, String> headers = generateHeaders(body);

        return new Response(version, status, body, headers);
    }

    private byte[] generateBody() {
        StringBuilder builder = new StringBuilder();
        File[] files = resourcePath.toFile().listFiles();

        builder.append("<ul>");
        for (File file : files) {
            builder.append("<li>" + "<a href=\"/" + getRelativePath(file) + "\">" + file.getName() + "</a>" + "</li>");
        }
        builder.append("</ul>");

        return builder.toString().getBytes();
    }

    private HashMap<String, String> generateHeaders(byte[] body) {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "text/html");
        return headers;
    }

    private String getRelativePath(File file) {
        return file.getPath().split(rootDirectory)[1];
    }
}
