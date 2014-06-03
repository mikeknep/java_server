package com.mikeknep.dahomey.responses;

import java.util.HashMap;

/**
 * Created by mrk on 6/3/14.
 */
public class ResponseFactory {
    public static Response buildResponse(String status, HashMap<String, String> headers, byte[] body) {
        return new Response("HTTP/1.1", status, body, headers);
    }
}
