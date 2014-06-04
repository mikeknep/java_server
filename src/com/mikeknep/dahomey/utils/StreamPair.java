package com.mikeknep.dahomey.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by mrk on 5/19/14.
 */
public interface StreamPair {
    public InputStream getIn() throws Exception;
    public OutputStream getOut() throws Exception;
    public Date getSocketOpenTime();
    public void close() throws Exception;
}
