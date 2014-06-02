package com.mikeknep.dahomey.requests;

import java.util.HashMap;

/**
 * Created by mrk on 5/28/14.
 */
public class RequestValidator {
    public static boolean isInvalidRequest(Request request) {
        return (request.getMethod().equals("") &&
                request.getResource().equals("") &&
                request.getVersion().equals("") &&
                request.getHeaders().equals(new HashMap<String, String>()));
    }
}
