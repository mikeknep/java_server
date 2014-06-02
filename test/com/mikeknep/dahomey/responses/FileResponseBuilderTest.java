package com.mikeknep.dahomey.responses;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.*;

public class FileResponseBuilderTest {
    public boolean responsesAreEquivalent(Response expectedResponse, Response actualResponse) {
        return (expectedResponse.getVersion().equals(actualResponse.getVersion()) &&
                expectedResponse.getStatus().equals(actualResponse.getStatus()) &&
                expectedResponse.getHeaders().equals(actualResponse.getHeaders()) &&
                Arrays.equals(expectedResponse.getBody(), actualResponse.getBody()));
    }


    @Test
    public void itBuildsHTMLFileResponse() throws Exception {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = "Good morning, world!".getBytes();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "text/html");
        Response expectedResponse = new Response(version, status, body, headers);

        FileResponseBuilder builder = new FileResponseBuilder("test/sample_files", "/mock.html");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuildsJPGFileResponse() throws Exception {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = Files.readAllBytes(Paths.get("test/sample_files/mock.jpg"));
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "image/jpeg");
        Response expectedResponse = new Response(version, status, body, headers);

        FileResponseBuilder builder = new FileResponseBuilder("test/sample_files", "/mock.jpg");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }

    @Test
    public void itBuildsGIFFileResponse() throws Exception {
        String version = "HTTP/1.1";
        String status = "200 OK";
        byte[] body = Files.readAllBytes(Paths.get("test/sample_files/mock.gif"));
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Length", String.valueOf(body.length));
        headers.put("Content-Type", "image/gif");
        Response expectedResponse = new Response(version, status, body, headers);

        FileResponseBuilder builder = new FileResponseBuilder("test/sample_files", "/mock.gif");

        assertTrue(responsesAreEquivalent(expectedResponse, builder.buildResponse()));
    }
}