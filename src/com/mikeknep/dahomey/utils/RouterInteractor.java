package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;

import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 * Created by mrk on 6/3/14.
 */
public class RouterInteractor {
    private String rootDirectory;
    private Request request;
    private String router;
    private String status;
    private HashMap<String, String> headers;
    private byte[] body;

    public RouterInteractor(String directory, Request request, String router) {
        this.rootDirectory = directory;
        this.request = request;
        this.router = router;
    }

    public void runRouter() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", rootDirectory + router, rootDirectory, request.getMethod(), request.getResource());
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
