package com.mikeknep.dahomey.requests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mrk on 5/6/14.
 */
public class Listener {
    public static String receiveRawRequest(InputStream incoming) throws Exception {
        InputStreamReader isr = new InputStreamReader(incoming);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder builder = new StringBuilder();

        builder.append(br.readLine() + "\n");
        while (br.ready()) {
            builder.append(br.readLine() + "\n");
        }

        return builder.toString();
    }
}
