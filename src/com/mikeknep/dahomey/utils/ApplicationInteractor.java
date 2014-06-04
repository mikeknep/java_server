package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;

import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by mrk on 6/3/14.
 */
public class ApplicationInteractor {
    private String rootDirectory;
    private Request request;
    private String application;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public ApplicationInteractor(String directory, Request request, String application) {
        this.rootDirectory = directory;
        this.request = request;
        this.application = application;
    }

    public void runApplication() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", application, rootDirectory, request.getMethod(), request.getResource());
        builder.redirectErrorStream(true);
        Process process = builder.start();

        ObjectInputStream ois = new ObjectInputStream(process.getInputStream());
        this.status = (String) ois.readObject();
        this.headers = (HashMap<String, String>) ois.readObject();
        this.body = (byte[]) ois.readObject();

        process.destroy();
    }

    public String getStatus() {
        return this.status;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public byte[] getBody() {
        return this.body;
    }
}
