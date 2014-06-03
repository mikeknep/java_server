package com.mikeknep.dahomey.responses;

import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Response {
    private String version;
    private String status;
    private byte[] body;
    private HashMap<String, String> headers = new HashMap<String, String>();

    public Response(String version, String status, byte[] body, HashMap<String, String> headers) {
        this.version = version;
        this.status = status;
        this.body = body;
        this.headers = headers;
    }

    public String getVersion() {
        return this.version;
    }

    public String getStatus() {
        return this.status;
    }

    public byte[] getBody() {
        return this.body;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }
}