package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.requests.RequestBuilder;
import com.mikeknep.dahomey.responses.BadRequestResponseBuilder;
import com.mikeknep.dahomey.responses.DirectoryResponseBuilder;
import com.mikeknep.dahomey.responses.FileResponseBuilder;
import com.mikeknep.dahomey.responses.MissingResourceResponseBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class DispatcherTest {

    @Test
    public void itSendsBadRequestsToBadRequestResponseBuilder() throws Exception {
        Request badRequest = RequestBuilder.buildRequest("");

        assertEquals(BadRequestResponseBuilder.class, Dispatcher.setResponseBuilder("test", badRequest).getClass());
    }

    @Test
    public void itSendsFileRequestsToFileResponseBuilder() throws Exception {
        Request fileRequest = RequestBuilder.buildRequest("GET /sample_files/mock.html HTTP/1.1\nHeader: header");

        assertEquals(FileResponseBuilder.class, Dispatcher.setResponseBuilder("test", fileRequest).getClass());
    }

    @Test
    public void itSendsDirectoryRequestsToDirectoryResponseBuilder() throws Exception {
        Request directoryRequest = RequestBuilder.buildRequest("GET /sample_files/ HTTP/1.1\nHeader: header");

        assertEquals(DirectoryResponseBuilder.class, Dispatcher.setResponseBuilder("test", directoryRequest).getClass());
    }

    @Test
    public void itSendsBadRequestsToMissingResourceResponseBuilder() throws Exception {
        Request request404 = RequestBuilder.buildRequest("GET /sample_files/not_here.html HTTP/1.1\nHeader: header");

        assertEquals(MissingResourceResponseBuilder.class, Dispatcher.setResponseBuilder("test", request404).getClass());
    }
}