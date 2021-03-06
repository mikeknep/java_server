package com.mikeknep.dahomey.requests;

import java.util.HashMap;

/**
 * Created by mrk on 5/12/14.
 */
public class Request {
    private String method;
    private String resource;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private String body;

    public Request(String method, String resource, HashMap<String, String> headers, String body) {
        this.method = method;
        this.resource = resource;
        this.headers = headers;
        this.body = body;
    }


    public String getMethod() {
        return this.method;
    }

    public String getResource() {
        return this.resource;
    }

    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public String getBody() {
        return this.body;
    }
}
