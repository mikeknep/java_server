package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Listener;
import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.requests.RequestBuilder;
import com.mikeknep.dahomey.responses.Responder;
import com.mikeknep.dahomey.responses.Response;
import com.mikeknep.dahomey.responses.ResponseFactory;

import java.net.ServerSocket;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    private String directory;
    private ServerSocket serverSocket;
    private String router;

    public Server(String directory, ServerSocket serverSocket, String router) throws Exception {
        this.directory = directory;
        this.serverSocket = serverSocket;
        this.router = router;
        if (this.router == null) {
            throw new MissingRouterException();
        }
        Logger.logStartup(serverSocket.getLocalPort(), directory);
    }

    public void run() throws Exception {
        while (true) {
            SocketStreamPair socketStreamPair = new SocketStreamPair(serverSocket);
            String rawRequest = Listener.receiveRawRequest(socketStreamPair.getIn());
            Request request = RequestBuilder.buildRequest(rawRequest);
            RouterInteractor interactor = new RouterInteractor(directory, request, router);
            interactor.runRouter();
            Response response = ResponseFactory.buildResponse(interactor.getStatus(), interactor.getHeaders(), interactor.getBody());
            Responder.sendResponse(response, socketStreamPair.getOut());
            Logger.logBasic(request, response, socketStreamPair.getSocketOpenTime());
            socketStreamPair.close();
        }
    }
}
