package com.mikeknep.dahomey.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by mrk on 5/19/14.
 */
public class MockSocketConnection implements StreamPair {
    private InputStream inputStream;
    private OutputStream outputStream;

    public MockSocketConnection(byte[] data) {
        this.inputStream= new ByteArrayInputStream(data);
        this.outputStream = new ByteArrayOutputStream();
    }

    public InputStream getIn() {
        return inputStream;
    }

    public OutputStream getOut() {
        return outputStream;
    }
}
