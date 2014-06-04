package com.mikeknep.dahomey.responses;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * Created by mrk on 5/14/14.
 */
public class Responder {
    public static void sendResponse(Response response, OutputStream outputStream) throws Exception {
        byte[] fullResponseByteArray = ResponsePresenter.present(response);
        DataOutputStream out = new DataOutputStream(outputStream);
        out.write(fullResponseByteArray);
    }
}
