import org.junit.Test;

import static org.junit.Assert.*;

public class DispatcherTest {

    @Test
    public void itSendsFileRequestsToFileResponseBuilder() throws Exception {
        Request fileRequest = RequestBuilder.buildRequest("GET /sample_files/mock.html HTTP/1.1\nHeader: header");

        assertEquals(FileResponseBuilder.class, Dispatcher.setResponseBuilder("spec", fileRequest).getClass());
    }

    @Test
    public void itSendsDirectoryRequestsToDirectoryResponseBuilder() throws Exception {
        Request directoryRequest = RequestBuilder.buildRequest("GET /sample_files/ HTTP/1.1\nHeader: header");

        assertEquals(DirectoryResponseBuilder.class, Dispatcher.setResponseBuilder("spec", directoryRequest).getClass());
    }

    @Test
    public void itSendsBadRequestsToErrorResponseBuilder() throws Exception {
        Request request404 = RequestBuilder.buildRequest("GET /sample_files/not_here.html HTTP/1.1\nHeader: header");

        assertEquals(ErrorResponseBuilder.class, Dispatcher.setResponseBuilder("spec", request404).getClass());
    }
}