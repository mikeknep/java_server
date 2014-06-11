package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.responses.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by mrk on 5/7/14.
 */
public class Logger {

    public static void logStartup(int port, String directory, String application) {
        System.out.println("Server initialized on port " + port + ", serving directory " + directory + " using application " + application);
    }

    public static void logBasic(Request request, Response response, Date timeOfRequest, String directory) {
        try {
            FileWriter fileWriter = new FileWriter(directory + "/logs", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.write(formatBasic(request, response, timeOfRequest));
            printWriter.close();
        } catch (IOException e) {}
        System.out.println(formatBasic(request, response, timeOfRequest));
    }

    private static String formatBasic(Request request, Response response, Date timeOfRequest) {
        return timeOfRequest.toString() + " '" + request.getMethod() + " " + request.getResource() + " HTTP/1.1' " + response.getStatus() + "\n";
    }
}
