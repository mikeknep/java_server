package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.responses.Response;

import java.util.Date;

/**
 * Created by mrk on 5/7/14.
 */
public class Logger {

    public static void logStartup(int port, String directory, String application) {
        System.out.println("Server initialized on port " + port + ", serving directory " + directory + " using application " + application);
    }

    public static void logBasic(Request request, Response response, Date timeOfRequest) {
        System.out.println(timeOfRequest.toString() + " '" + request.getMethod() + " " + request.getResource() + "' " + response.getStatus());
    }
}
