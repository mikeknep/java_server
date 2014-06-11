package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        Process process = startApplication();
        sendRequestData(process);
        receiveResponseData(process);
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

    private Process startApplication() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", application, rootDirectory);
        builder.redirectErrorStream(true);
        return builder.start();
    }

    private void sendRequestData(Process process) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(process.getOutputStream());
        oos.writeObject(request.getMethod());
        oos.writeObject(request.getResource());
        oos.writeObject(request.getHeaders());
        oos.writeObject(request.getBody());
        oos.close();
    }

    private void receiveResponseData(Process process) {
        try {
            ObjectInputStream ois = new ObjectInputStream(process.getInputStream());
            setupFieldsFromProcess(ois);
        } catch (Exception e) {
            setup500();
        }
    }

    private void setupFieldsFromProcess(ObjectInputStream ois) throws Exception {
        this.status = (String) ois.readObject();
        this.headers = (HashMap<String, String>) ois.readObject();
        this.body = (byte[]) ois.readObject();
    }

    private void setup500() {
        this.status = "500 Internal Server Error";
        this.headers = new HashMap<String, String>();
        this.body = "500".getBytes();
    }
}
