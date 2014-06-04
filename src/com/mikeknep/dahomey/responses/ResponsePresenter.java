package com.mikeknep.dahomey.responses;

import java.util.Map;

/**
 * Created by mrk on 5/13/14.
 */
public class ResponsePresenter {
    public static byte[] present(Response response) {
        byte[] status = formatStatusLine(response);
        byte[] headers = formatHeaders(response);
        byte[] newline = "\n".getBytes();
        byte[] body = response.getBody();

        int size = status.length + headers.length + newline.length + body.length;
        byte[] fullResponse = new byte[size];

        System.arraycopy(status, 0, fullResponse, 0, status.length);
        System.arraycopy(headers, 0, fullResponse, status.length, headers.length);
        System.arraycopy(newline, 0, fullResponse, status.length + headers.length, newline.length);
        System.arraycopy(body, 0, fullResponse, status.length + headers.length + newline.length, body.length);

        return fullResponse;
    }



    private static byte[] formatStatusLine(Response response) {
        return ("HTTP/1.1 " + response.getStatus() + "\n").getBytes();
    }

    private static byte[] formatHeaders(Response response) {
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, String> header : response.getHeaders().entrySet()) {
            builder.append(header.getKey() + ": " + header.getValue() + "\n");
        }

        return builder.toString().getBytes();
    }
}
