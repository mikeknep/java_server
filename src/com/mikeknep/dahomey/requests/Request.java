package com.mikeknep.dahomey.requests;

import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Request {
    private String method;
    private String resource;
    private String version;
    private HashMap<String, String> headers = new HashMap<String, String>();

    public Request(String method, String resource, String version, HashMap<String, String> headers) {
        this.method = method;
        this.resource = resource;
        this.version = version;
        this.headers = headers;
    }


    public String getMethod() {
        return this.method;
    }

    public String getResource() {
        return this.resource;
    }

    public String getVersion() {
        return this.version;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }
}
