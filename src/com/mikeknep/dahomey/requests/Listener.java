package com.mikeknep.dahomey.requests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class Listener {
    public static ArrayList<String> receiveRawRequest(InputStream incoming) throws Exception {
        InputStreamReader isr = new InputStreamReader(incoming);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder headBuilder = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null && !line.equals("")) {
            headBuilder.append(line + "\n");
        }

        String head = headBuilder.toString();

        StringBuilder bodyBuilder = new StringBuilder();
        if (head.contains("Content-Length")) {
            int contentLength = Integer.valueOf(head.split("Content-Length: ")[1].split("\n")[0]);
            for (int i = 0; i < contentLength; i++) {
                bodyBuilder.append((char) br.read());
            }
        }

        String body = bodyBuilder.toString();

        ArrayList<String> rawRequest = new ArrayList<String>();
        rawRequest.add(head);
        rawRequest.add(body);
        return rawRequest;
    }
}
