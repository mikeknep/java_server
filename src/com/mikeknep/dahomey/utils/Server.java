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
    private String application;

    public Server(String directory, ServerSocket serverSocket, String application) throws Exception {
        this.directory = directory;
        this.serverSocket = serverSocket;
        this.application = application;
        if (this.application == null) {
            throw new MissingApplicationException();
        }
        Logger.logStartup(serverSocket.getLocalPort(), directory);
    }

    public void run() throws Exception {
        while (true) {
            SocketStreamPair socketStreamPair = new SocketStreamPair(serverSocket);
            String rawRequest = Listener.receiveRawRequest(socketStreamPair.getIn());
            Request request = RequestBuilder.buildRequest(rawRequest);
            ApplicationInteractor interactor = new ApplicationInteractor(directory, request, application);
            interactor.runRouter();
            Response response = ResponseFactory.buildResponse(interactor.getStatus(), interactor.getHeaders(), interactor.getBody());
            Responder.sendResponse(response, socketStreamPair.getOut());
            Logger.logBasic(request, response, socketStreamPair.getSocketOpenTime());
            socketStreamPair.close();
        }
    }
}
